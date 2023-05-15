package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class Pet {
    @Positive(message = "La edad debe ser un número positivo")
    @NotNull(message = "la edad debe tener un valor valido")
    private byte age;
    @NotBlank(message = "el nombre no puede ser vacío")
    @Size(max =30,message = "el tamaño máximo del nombre es de 30 caracteres")
    private String name;
    @NotBlank(message = "el tipo de mascota no puede quedar en vacío")
    @Size(max=30,message = "Número de caracteres máximo es igual a 30")
    private String type;
    @NotBlank(message = "la raza de la mascota no puede quedar vacío")
    @Size(max = 30,message = "El número máximo de caracteres es de 30")
    private String race;

    @Pattern(regexp = "^[MF]$", message = "El género debe ser 'M' o 'F'")
    private char gender;
    @NotBlank
    @Size(max = 15,min = 1,message = "digite maximo 15 caracteres  y minimo 1 caracter")
    @NotNull(message = "debe llenar este campo")
    private String identification;
    @Valid
    private Location location;
    @Pattern(regexp = "^[ID]$", message = "El género debe ser 'I' o 'D'")
    private boolean shower;
}//end of pet------------------------------------------
