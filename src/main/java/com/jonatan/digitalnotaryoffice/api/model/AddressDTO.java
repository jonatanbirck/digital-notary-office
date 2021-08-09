package com.jonatan.digitalnotaryoffice.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id;

    private Integer cep;

    @Size(max = 255, message="Limite de 255 caracteres")
    private String complement;   

    @Size(max = 255, message="Limite de 255 caracteres")
    @NotBlank(message = "Nome da rua não pode ser vazio")
    private String street;
    
    @Size(max = 255, message="Limite de 255 caracteres")
    @NotBlank(message = "Nome do bairro não pode ser vazio")
    private String district;

    @Size(max = 255, message="Limite de 255 caracteres")
    @NotBlank(message = "Nome da cidade não pode ser vazio")
    private String city;

    @Size(max = 255, message="Limite de 255 caracteres")
    @NotBlank(message = "Nome do estado não pode ser vazio")
    private String state;

    @Size(max = 2, message = "UF não pode ter mais de 2 caracteres")
    @NotBlank(message = "UF não pode ser vazio")
    private String uf;
}
