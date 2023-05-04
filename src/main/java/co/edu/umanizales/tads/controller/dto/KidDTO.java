package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class KidDTO {
    @NotBlank
    @Size(max = 15)
    private String identification;
    @NotBlank
    @Size(max = 30)
    private String name;
    @Min(1)
    @Max(14)
    @NotNull
    private byte age;
    @javax.validation.constraints.Pattern(regexp = "^[MF]$",message = "El género debe ser 'M' o 'F'")
    private char gender;
    private String codeLocation;
}//end of the kid DTO----------------------------------------