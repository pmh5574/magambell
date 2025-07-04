package com.magambell.server.payment.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayType {
    CARD("카드"),
    EASY_PAY("간편 결제");

    private final String text;
}
