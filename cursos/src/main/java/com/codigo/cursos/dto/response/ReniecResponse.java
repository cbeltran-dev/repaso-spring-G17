package com.codigo.cursos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReniecResponse {


    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("first_last_name")
    private String firstLastName;
    @JsonProperty("second_last_name")
    private String secondLastName;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("document_number")
    private String documentNumber;

}
