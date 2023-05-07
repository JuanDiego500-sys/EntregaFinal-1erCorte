package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PetDTO {
    @Positive
    @NotNull
    private byte age;
    @NotBlank
    @Size(max =30)
    private String name;
    @NotBlank
    @Size(max=30)
    private String type;
    @NotBlank
    @Size(max = 30)
    private String race;
    private String codeLocation;
    @Pattern(regexp = "^[MF]$", message = "El género debe ser 'M' o 'F'")
    private char gender;
    @Size(min = 6, max =15 )
    @NotBlank
    private String ownerPhone;
}//end of the petDTO------------------------------------
