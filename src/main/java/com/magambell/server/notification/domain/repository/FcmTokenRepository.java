package com.magambell.server.notification.domain.repository;

import com.magambell.server.notification.domain.model.FcmToken;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long>, FcmTokenRepositoryCustom {
    boolean existsByUserIdAndStoreId(Long userId, Long storeId);

    void deleteByUserIdAndStoreIsNull(Long userId);

    List<FcmToken> findByUserId(Long userId);

    Optional<FcmToken> findByStoreIdAndUserId(Long storeId, Long userId);
}
