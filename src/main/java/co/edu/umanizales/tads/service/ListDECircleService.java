package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.ListDECircle;
import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Data
public class ListDECircleService {
    private ListDECircle pets;

    public ListDECircleService() {
        pets = new ListDECircle();
    }
    public NodeDE getPets (){
        return pets.getHead();
    }
    public void addPetToEnd(Pet pet){
        pets.addPetToEnd(pet);
    }
    public void addPetToBeginning(Pet pet){
        pets.addPetToBeginning(pet);
    }
    public void addInPos(Pet pet,int pos){
        pets.addInPos(pet,pos);
    }
    public void cleanPet(char direction){
        pets.cleanPet(direction);
    }
    public ArrayList<Pet> showList(){
        return pets.showList();
    }
}
