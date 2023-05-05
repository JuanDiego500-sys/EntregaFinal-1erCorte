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
    @NotNull
    private String identification;
    @NotBlank
    @Size(max = 30)
    @NotNull
    private String name;
    @Min(1)
    @Max(14)
    @NotNull
    @NotNull
    private byte age;
    @Pattern(regexp = "^[MF]$",message = "El género debe ser 'M' o 'F'")
    @NotNull
    private char gender;
    @Valid
    @NotNull
    private Location location;

}
