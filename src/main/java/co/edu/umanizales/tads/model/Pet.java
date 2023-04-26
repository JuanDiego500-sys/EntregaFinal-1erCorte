package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    private byte age;
    private String name;
    private String type;
    private String race;
    private Location location;
    private char gender;
    private String ownerPhone;
}//end of pet------------------------------------------
