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
    private int size;

    public ListDEService() {
        pets = new ListDE();
    }

    public NodeDE getPets(){return pets.getHead();
    }
    public void addPet(Pet pet){pets.addPet(pet);}


}//end of listDE service--------------------------------------------
