package com.magambell.server.notification.app.port.out.dto;

public record FcmTokenDTO(
        Long fcmTokenId,
        String token,
        Long userId,
        String nickName,
        String storeName
) {
}
