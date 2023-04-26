package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {

    private ListDE pets;

    public ListDEService() {
        pets = new ListDE();
    }

    public NodeDE getPets(){return pets.getHead();
    }
    public void addPet(Pet pet){pets.addPet(pet);}
    public int getCounPetsByLocationCode(String code){return pets.getCounPetsByLocationCode(code);}
    public int getCountPetsByLocationCodeAndMale(String code){return pets.getCountPetsByLocationCodeAndMale(code);}
    public int getCountPetsByLocationCodeAndFemale(String code){return pets.getCountPetsByLocationCodeAndFemale(code);}


}//end of listDE service--------------------------------------------
