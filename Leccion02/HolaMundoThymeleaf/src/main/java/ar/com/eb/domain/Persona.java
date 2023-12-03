package ar.com.eb.domain;

import lombok.Data;

@Data
public class Persona {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}