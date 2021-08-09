package com.jonatan.digitalnotaryoffice.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class NotaryDTO {

    private Long id;

    @Size(max = 255, message="Limite de 255 caracteres")
    @NotBlank(message = "Nome do cartório não pode ser vazio")
    private String name;

    private Long refAddress;
    
    private List<Long> certificates = new ArrayList<>();
}
