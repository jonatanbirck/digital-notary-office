package com.jonatan.digitalnotaryoffice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CertificateState {
    ACTIVE("Ativo"),
    INACTIVE("Inativo");

    private final String displayValue;
}
