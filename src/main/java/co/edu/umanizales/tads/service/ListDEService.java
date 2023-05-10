package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;
import co.edu.umanizales.tads.exception.ListDEException;

@Service
@Data
public class ListDEService {

    private ListDE pets;

    public ListDEService() {
        pets = new ListDE();
    }

    public NodeDE getPets() {
        return pets.getHead();
    }

    public String putToString() {
        return pets.toListString();
    }

    public void addPet(Pet pet) throws ListDEException {
        pets.addPet(pet);
    }

    public void addPetToBeginning(Pet pet) {
        pets.addPetToBeginning(pet);
    }

    public void deletePet(String id) throws ListDEException {
        pets.deletePet(id);
    }

    public void addInPos(Pet pet, int pos) {
        pets.addInPos(pet, pos);
    }

    public int getCounPetsByLocationCode(String code) throws ListDEException {
        return pets.getCounPetsByLocationCode(code);
    }

    public int getCountPetsByLocationCodeAndMale(String code) throws ListDEException {
        return pets.getCountPetsByLocationCodeAndMale(code);
    }

    public int getCountPetsByLocationCodeAndFemale(String code) throws ListDEException {
        return pets.getCountPetsByLocationCodeAndFemale(code);
    }

    public void orderByGender() throws ListDEException {
        pets.orderByGender();
    }

    public void losePositions(String phone, int lose) throws ListDEException {
        pets.losePositions(phone, lose);
    }

    public void exchangeExtremes() throws ListDEException {
        pets.exchangeExtremes();
    }

    public void invertList() throws ListDEException {
        pets.invertList();
    }

    public void putPetsMaleToBeginning() throws ListDEException {
        pets.putPetsToBeginning();
    }

    public void deleteByAge(byte age) throws ListDEException {
        pets.deleteByAge(age);
    }

    public double getAverageAge() throws ListDEException {
        return pets.getAverageAge();
    }

    public void earnPositions(String phone, int earn) throws ListDEException {
        pets.earnPositions(phone, earn);
    }

    public void sendPetsToEndByChar(char user) throws ListDEException {
        pets.sendPetsToEndByChar(user);
    }

    public String generateReportByAge() throws ListDEException {
        return pets.generateReportByAge();
    }

    public int verifyId(PetDTO petDTO) throws ListDEException {
        return pets.verifyId(petDTO);
    }

    public void deleteKamikazePet(String id) throws ListDEException {
        pets.deleteKamikazePet(id);
    }


}//end of listDE service--------------------------------------------
