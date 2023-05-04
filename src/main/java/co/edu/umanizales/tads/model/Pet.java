package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class Pet {
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
    @Valid
    private Location location;
    @javax.validation.constraints.Pattern(regexp = "^[MF]$", message = "El género debe ser 'M' o 'F'")
    private char gender;
    @Size(min = 6, max =15 )
    @NotBlank
    private String ownerPhone;
}//end of pet------------------------------------------
