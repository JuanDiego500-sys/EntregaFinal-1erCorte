package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor


public class Kid {

    @NotBlank
    @Size(max = 15,min = 1,message = "digite maximo 15  y minimo 1")
    @NotNull(message = "debe llenar este campo")
    private String identification;
    @NotBlank
    @Size(max = 30,min = 1,message = "digite maximo 30 caracteres y minimo 1")
    @NotNull(message = "debe llenar este campo")
    private String name;
    @Min(1)
    @Max(14)
    @NotNull(message = "debe llenar este campo")
    private byte age;
    @Pattern(regexp = "^[MF]$",message = "El género debe ser 'M' o 'F'")
    @NotNull
    private char gender;
    @Valid
    @NotNull(message = "debe llenar este campo")
    private Location location;

}