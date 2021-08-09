package com.jonatan.digitalnotaryoffice.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.jonatan.digitalnotaryoffice.domain.entity.CertificateState;

import lombok.Data;

@Data
public class CertificateDTO {
    
    private Long id;

    @Size(max = 255, message="Limite de 255 caracteres")
    @NotBlank(message = "Nome da certidão não pode ser vazio")
    private String name;

    private String description;

    @Size(max = 255, message="Limite de 255 caracteres")
    @NotBlank(message = "Versão da certidão não pode ser vazio")
    private String version = "1.0";
    
    @NotBlank(message = "Estado da certidão não pode ser vazio")
    private String state = CertificateState.ACTIVE.getDisplayValue();

}
