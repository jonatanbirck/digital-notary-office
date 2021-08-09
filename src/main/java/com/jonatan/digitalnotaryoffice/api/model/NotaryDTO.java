package com.jonatan.digitalnotaryoffice.api.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class NotaryDTO {

    private Long id;
    private String name;
    private Long refAddress;
    private List<Long> certificates = new ArrayList<>();
}
