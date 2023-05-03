package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.PetByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path= "list_de")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;

    @GetMapping(path = "/get_list")
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getPets(), null), HttpStatus.OK);
    }
    @GetMapping(path = "/delete_pet/{id}")
    public ResponseEntity<ResponseDTO> deletePet(@PathVariable String name) {
        listDEService.deletePet(name);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Mascota eliminada", null), HttpStatus.OK);
    }

    @GetMapping(path = "/add_pet_to_beginning")
    public ResponseEntity<ResponseDTO> addPetToBeginning(Pet pet) {
        listDEService.addPetToBeginning(pet);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Mascota agregada al inicio", null), HttpStatus.OK);
    }
    @GetMapping(path = "/add_pet_in_pos/{pos}")
    public ResponseEntity<ResponseDTO> addPetInPos(Pet pet,@PathVariable int pos) {
        listDEService.addInPos(pet,pos);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Mascota agregada en posición", null), HttpStatus.OK);
    }


    @GetMapping(path = "/pets_by_locations/{age}")
    public ResponseEntity<ResponseDTO> getKidsByLocation(@PathVariable byte age){
        List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            if (listDEService.getPets().getData().getAge() > age) {
                int count = listDEService.getCounPetsByLocationCode(loc.getCode());
                int male = listDEService.getCountPetsByLocationCodeAndMale(loc.getCode());
                int female = listDEService.getCountPetsByLocationCodeAndFemale(loc.getCode());
                if (count > 0) {
                    petByLocationDTOS.add(new PetByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,petByLocationDTOS,
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/pets_by_department/{age}")
    public ResponseEntity<ResponseDTO> getKidsByDepartment(@PathVariable byte age){
        List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
        for(Location loc: locationService.getLocationsByCodeSize(5)){
            if (listDEService.getPets().getData().getAge() > age) {
                int count = listDEService.getCounPetsByLocationCode(loc.getCode());
                int male = listDEService.getCountPetsByLocationCodeAndMale(loc.getCode());
                int female = listDEService.getCountPetsByLocationCodeAndFemale(loc.getCode());
                if (count > 0) {
                    petByLocationDTOS.add(new PetByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,petByLocationDTOS,
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/pets_by_city/{age}")
    public ResponseEntity<ResponseDTO> getKidsByCity(@PathVariable byte age){
        List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
        for(Location loc: locationService.getLocationsByCodeSize(8)){
            if (listDEService.getPets().getData().getAge() > age) {
                int count = listDEService.getCounPetsByLocationCode(loc.getCode());
                int male = listDEService.getCountPetsByLocationCodeAndMale(loc.getCode());
                int female = listDEService.getCountPetsByLocationCodeAndFemale(loc.getCode());
                if (count > 0) {
                    petByLocationDTOS.add(new PetByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,petByLocationDTOS,
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/get_average_age")
    public ResponseEntity<ResponseDTO>getAverageAge(){
        listDEService.getAverageAge();
        return new ResponseEntity<>(new ResponseDTO(
                200, "el promedio de edad de las mascotas es: " + listDEService.getAverageAge(), null), HttpStatus.OK);
    }
    @PostMapping(path = "/send_pets_to_end_by_char")
    public ResponseEntity<ResponseDTO> sendPetsToEndByChar(@RequestBody Map<String, Object> requestData) {
        String userString = (String) requestData.get("user");
        char user = userString.toLowerCase().charAt(0);
        listDEService.sendPetsToEndByChar(user);
        return new ResponseEntity<>(new ResponseDTO(
                200, "mascotas ordenadas" , null), HttpStatus.OK);
    }
    @GetMapping(path = "/order_by_gender")
    public ResponseEntity<ResponseDTO> orderByGender() {
        listDEService.orderByGender();
        return new ResponseEntity<>(new ResponseDTO(
                200, "mascotas ordenadas", null), HttpStatus.OK);
    }
    @PostMapping(path = "/lose_positions")
    public ResponseEntity<ResponseDTO> losePositions(@RequestBody Map<String, Object> requestBody) {
        String id = (String) requestBody.get("id");
        Integer lose = (Integer) requestBody.get("lose");
        listDEService.losePositions(id,lose);
        return new ResponseEntity<>(new ResponseDTO(
                200, "posiciones reordenadas", null), HttpStatus.OK);
    }
    @GetMapping(path = "/exchange_list_de")
    public ResponseEntity<ResponseDTO> exchangeExtremes() {
        listDEService.exchangeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200, "se han intercambiado los extremos", null), HttpStatus.OK);
    }
    @GetMapping(path = "/invert_list")
    public ResponseEntity<ResponseDTO> invertList() {
        listDEService.invertList();
        return new ResponseEntity<>(new ResponseDTO(
                200, "lista invertida", null), HttpStatus.OK);
    }
    @GetMapping(path = "/male_to_beginning")
    public ResponseEntity<ResponseDTO> putKidsToBeginning() {
        listDEService.putPetsMaleToBeginning();
        return new ResponseEntity<>(new ResponseDTO(
                200, "machos agregados al inicio y feminas al final", null), HttpStatus.OK);
    }
    @GetMapping(path = "/delete_pet_by_age/{age}")
    public ResponseEntity<ResponseDTO> losePositions(@PathVariable byte age) {
        listDEService.deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(
                200, "mascotas con esa edad eliminadas", null), HttpStatus.OK);
    }
    @PostMapping(path = "/earn_positions")
    public ResponseEntity<ResponseDTO> earnPositions(@RequestBody Map<String, Object> requestBody) {
        String id = (String) requestBody.get("id");
        Integer earn = (Integer) requestBody.get("earn");
        listDEService.earnPositions(id, earn);
        return new ResponseEntity<>(new ResponseDTO(
                200, "posiciones re ordenadas", null), HttpStatus.OK);
    }
    @GetMapping(path = "/generate_report_by_age")
    public ResponseEntity<ResponseDTO>  generateReportByAge(){
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.generateReportByAge(), null), HttpStatus.OK);
    }
}//end of ListDE controller----------------------------------------
