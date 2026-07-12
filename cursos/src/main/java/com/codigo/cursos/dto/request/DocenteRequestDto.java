package com.codigo.cursos.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocenteRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 60, message = "El nombre debe tener entre 2 o 60 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 60, message = "El apellido debe tener entre 2 o 60 caracteres")
    private String apellido;

    @NotBlank(message = "El nombre es obligatorio")
    private String especialidad;

    @Email(message = "El email debe tener el formato correcto")
    @NotBlank(message = "El email es obligatorio")
    private String email;
}
