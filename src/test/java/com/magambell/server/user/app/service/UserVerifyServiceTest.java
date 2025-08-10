package com.magambell.server.user.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.magambell.server.auth.domain.ProviderType;
import com.magambell.server.common.enums.ErrorCode;
import com.magambell.server.common.exception.DuplicateException;
import com.magambell.server.common.exception.NotEqualException;
import com.magambell.server.common.exception.NotFoundException;
import com.magambell.server.user.app.dto.OAuthUserInfo;
import com.magambell.server.user.app.port.in.AwsEmailServiceInputPort;
import com.magambell.server.user.app.port.in.dto.UserDTO;
import com.magambell.server.user.app.port.in.dto.UserEmailDTO;
import com.magambell.server.user.app.port.in.request.UserSocialVerifyServiceRequest;
import com.magambell.server.user.app.port.in.request.VerifyEmailAuthCodeServiceRequest;
import com.magambell.server.user.app.port.in.request.VerifyEmailDuplicateServiceRequest;
import com.magambell.server.user.app.port.in.request.VerifyEmailSendServiceRequest;
import com.magambell.server.user.app.port.out.OAuthClient;
import com.magambell.server.user.app.port.out.UserEmailQueryPort;
import com.magambell.server.user.app.port.out.UserQueryPort;
import com.magambell.server.user.domain.enums.UserRole;
import com.magambell.server.user.domain.enums.VerificationStatus;
import com.magambell.server.user.domain.model.UserEmail;
import com.magambell.server.user.domain.repository.UserEmailRepository;
import com.magambell.server.user.domain.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserVerifyServiceTest {

    @Autowired
    private UserVerifyService userVerifyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEmailRepository userEmailRepository;

    @MockBean
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    private UserQueryPort userQueryPort;

    @Autowired
    private UserEmailQueryPort userEmailQueryPort;

    @Autowired
    private AwsEmailServiceInputPort awsEmailServiceInputPort;

    @AfterEach
    public void tearDown() {
        userRepository.deleteAllInBatch();
        userEmailRepository.deleteAllInBatch();
    }

    @DisplayName("회원가입시 이메일이 중복이 아닐시 성공한다.")
    @Test
    void emailRegisterDuplicate() {
        // given
        VerifyEmailDuplicateServiceRequest request = new VerifyEmailDuplicateServiceRequest("test@test.com");

        // when // then
        assertThatNoException().isThrownBy(() -> userVerifyService.emailRegisterDuplicate(request));
    }

    @DisplayName("회원가입시 이메일이 중복일때 오류가 발생한다.")
    @Test
    void emailRegisterDuplicateWithException() {
        // given
        String email = "test@test.com";
        UserDTO userDTO = new UserDTO(email, "qwer1234", "test", "01012341234", UserRole.CUSTOMER);
        userRepository.save(userDTO.toUser());

        VerifyEmailDuplicateServiceRequest request = new VerifyEmailDuplicateServiceRequest(email);

        // when // then
        assertThatThrownBy(() -> userVerifyService.emailRegisterDuplicate(request))
                .isInstanceOf(DuplicateException.class)
                .hasMessage(ErrorCode.DUPLICATE_EMAIL.getMessage());
    }

    @DisplayName("회원가입시 이메일과 인증번호가 맞으면 성공한다.")
    @Test
    void emailRegisterAuthCodeCheck() {
        // given
        String authCode = "testCode";
        String email = "test@test.com";
        UserEmailDTO userEmailDTO = new UserEmailDTO(email, authCode, VerificationStatus.REGISTER);
        userEmailRepository.save(userEmailDTO.toUserEmail());

        VerifyEmailAuthCodeServiceRequest request = new VerifyEmailAuthCodeServiceRequest(email, authCode);

        // when // then
        assertThatNoException().isThrownBy(() -> userVerifyService.emailRegisterAuthCodeCheck(request));
    }

    @DisplayName("회원가입시 인증번호가 다르면 예외가 발생한다.")
    @Test
    void registerWithNotEqualAuthCode() {
        // given
        String authCode = "testCode";
        String email = "test@test.com";
        UserEmailDTO userEmailDTO = new UserEmailDTO(email, "testCode2", VerificationStatus.REGISTER);
        userEmailRepository.save(userEmailDTO.toUserEmail());

        VerifyEmailAuthCodeServiceRequest request = new VerifyEmailAuthCodeServiceRequest(email, authCode);

        // when // then
        assertThatThrownBy(() -> userVerifyService.emailRegisterAuthCodeCheck(request))
                .isInstanceOf(NotEqualException.class)
                .hasMessage(ErrorCode.USER_EMAIL_AUTH_CODE_NOT_EQUALS.getMessage());
    }

    @DisplayName("회원가입시 인증번호가 존재하지 않으면 예외가 발생한다.")
    @Test
    void emailRegisterWithNotFoundAuthCode() {
        // given
        String authCode = "testCode";
        String email = "test@test.com";
        VerifyEmailAuthCodeServiceRequest request = new VerifyEmailAuthCodeServiceRequest(email, authCode);

        // when // then
        assertThatThrownBy(() -> userVerifyService.emailRegisterAuthCodeCheck(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(ErrorCode.USER_EMAIL_NOT_FOUND.getMessage());
    }

    @DisplayName("회원가입시 인증 이메일 전송")
    @Test
    void emailRegisterSend() {
        // given
        String authCode = "testCode";
        String email = "test@test.com";
        VerifyEmailSendServiceRequest request = new VerifyEmailSendServiceRequest(email);

        // when // then
        assertThatNoException().isThrownBy(() -> userVerifyService.emailRegisterSend(request));

        UserEmail userEmail = userEmailRepository.findAll().get(0);
        assertThat(userEmail.getEmail()).isEqualTo("test@test.com");
    }

    @DisplayName("소셜 회원 가입한 계정이 아니면 false")
    @Test
    void verifySocialUser() {
        // given
        OAuthUserInfo userInfo = new OAuthUserInfo("testId", "test@test.com", ProviderType.KAKAO);
        UserSocialVerifyServiceRequest request = new UserSocialVerifyServiceRequest(ProviderType.KAKAO, "testCode");

        // when
        OAuthClient kakaoOAuthClient = mock(OAuthClient.class);
        when(kakaoOAuthClient.getProviderType()).thenReturn(ProviderType.KAKAO);
        when(kakaoOAuthClient.findUserBySocialId(anyString())).thenReturn(Optional.of(userInfo));

        List<OAuthClient> kakaoOAuthClients = Collections.singletonList(kakaoOAuthClient);
        UserVerifyService testService = new UserVerifyService(
                userQueryPort,
                userEmailQueryPort,
                awsEmailServiceInputPort,
                kakaoOAuthClients
        );

        boolean checked = testService.verifySocialUser(request);

        // then
        assertThat(checked).isFalse();
    }
}