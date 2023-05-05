package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class KidDTO {
    @NotBlank
    @Size(max = 15)
    @NotNull
    private String identification;
    @NotBlank
    @Size(max = 30)
    @NotNull
    private String name;
    @Min(1)
    @Max(14)
    @NotNull
    private byte age;
    @javax.validation.constraints.Pattern(regexp = "^[MF]$",message = "El género debe ser 'M' o 'F'")
    @NotNull
    private char gender;
    private String codeLocation;
}//end of the kid DTO----------------------------------------