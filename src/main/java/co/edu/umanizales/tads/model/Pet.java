package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Pet {
    @NotBlank
    @NotEmpty
    @Positive
    private byte age;
    @NotBlank
    @NotEmpty
    @Size(min=2, max = 50)
    private String name;
    @NotBlank
    @NotEmpty
    @Size(min=2, max = 50)
    private String type;
    @NotBlank
    @NotEmpty
    @Size(min=2, max = 50)
    private String race;
    @Valid
    @NotBlank
    private Location location;
    @NotBlank
    @NotEmpty
    private Gender gender;
    @Size(min = 6, max =15 )
    @NotEmpty
    @NotBlank
    private String ownerPhone;
}//end of pet------------------------------------------
