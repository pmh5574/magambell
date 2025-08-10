package com.magambell.server.user.adapter.out.persistence;

import com.magambell.server.common.annotation.Adapter;
import com.magambell.server.user.app.port.in.dto.UserEmailDTO;
import com.magambell.server.user.app.port.out.UserEmailQueryPort;
import com.magambell.server.user.domain.enums.VerificationStatus;
import com.magambell.server.user.domain.repository.UserEmailRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class UserEmailQueryAdapter implements UserEmailQueryPort {

    private final UserEmailRepository userEmailRepository;

    @Override
    public void deleteEmail(final String email) {
        userEmailRepository.deleteByEmail(email);
    }

    @Override
    public Optional<UserEmailDTO> findRegisterUserEmail(final String email,
                                                        final VerificationStatus verificationStatus) {
        return userEmailRepository.findByEmailAndVerificationStatus(email, verificationStatus);
    }

    @Override
    public Long saveUserEmail(final String email, final String authCode, final VerificationStatus verificationStatus) {
        UserEmailDTO userEmailDTO = new UserEmailDTO(email, authCode, verificationStatus);
        return userEmailRepository.save(userEmailDTO.toUserEmail())
                .getId();
    }
}
