package co.edu.umanizales.tads.controller.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data

public class KidDTO {
    @NotBlank
    @Size(max = 15,min = 1,message = "digite maximo 15  y minimo 1")
    @NotNull(message = "debe llenar este campo")
    private String identification;
    @NotBlank
    @Size(max = 30,min = 1,message = "digite maximo 30 caracteres y minimo 1")
    @NotNull(message = "debe llenar este campo")
    private String name;
    @Min(value = 1,message = "debe tener un valor valido para el campo de la edad")
    @Max(value = 14,message = "debe tener un valor valido para el campo de la edad")
    @NotNull(message = "debe llenar este campo")
    private byte age;
    @Pattern(regexp = "^[MF]$",message = "El género debe ser 'M' o 'F'")
    @NotNull
    private char gender;
    @NotBlank(message = "debe llenar el campo de el código de la locación")
    @NotNull(message = "debe llenar el campo de el código de la locación")
    private String codeLocation;

}//end of the kid DTO----------------------------------------