package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor


public class Kid {

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
    @Pattern(regexp = "^[MF]$",message = "El género debe ser 'M' o 'F'")
    private char gender;
    @Valid
    @NotNull
    private Location location;

}
