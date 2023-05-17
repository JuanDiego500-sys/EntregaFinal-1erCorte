package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.PetByLocationDTO;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.exception.RequestException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/list_de")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;

    @GetMapping(path = "/get_list")
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.showList(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@Valid @RequestBody PetDTO petDTO) throws ListDEException {
        try {
            Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.BAD_REQUEST);
            }
            listDEService.addPet(
                    new Pet(petDTO.getAge(),
                            petDTO.getName(), petDTO.getType(), petDTO.getRace(),
                            petDTO.getGender(), petDTO.getIdentification(), location,petDTO.isShower()));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);

        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/delete_pet/{id}")
    public ResponseEntity<ResponseDTO> deletePet(@PathVariable String id) throws ListDEException {
        try {
            listDEService.deletePet(id);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Mascota eliminada", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add_pet_to_beginning")
    public ResponseEntity<ResponseDTO> addPetToBeginning(@Valid @RequestBody PetDTO petDTO) throws ListDEException {
        try{
            Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.OK);
            }
            listDEService.addPetToBeginning(
                    new Pet(petDTO.getAge(),
                            petDTO.getName(), petDTO.getType(), petDTO.getRace(),
                            petDTO.getGender(), petDTO.getIdentification(), location,petDTO.isShower()));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);
        } catch (ListDEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(path = "/add_pet_in_pos/{pos}")
    public ResponseEntity<ResponseDTO> addPetInPos(@Valid @RequestBody PetDTO petDTO,@Min (0)@PathVariable int pos) {
        try {
            Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.OK);
            }
            listDEService.addInPos(
                    new Pet(petDTO.getAge(),
                            petDTO.getName(), petDTO.getType(), petDTO.getRace(),
                            petDTO.getGender(), petDTO.getIdentification(), location, petDTO.isShower()), pos);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);
        }catch(ListDEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping(path = "/pets_by_locations/{age}")
    public ResponseEntity<ResponseDTO> getKidsByLocation(@PathVariable byte age) throws ListDEException {
        try {
            List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
            for (Location loc : locationService.getLocations()) {
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
                    200, petByLocationDTOS,
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/pets_by_department/{age}")
    public ResponseEntity<ResponseDTO> getKidsByDepartment(@PathVariable byte age) throws ListDEException {
        try {
            List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
            for (Location loc : locationService.getLocationsByCodeSize(5)) {
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
                    200, petByLocationDTOS,
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/pets_by_city/{age}")
    public ResponseEntity<ResponseDTO> getKidsByCity(@PathVariable byte age) throws ListDEException {
        try {
            List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
            for (Location loc : locationService.getLocationsByCodeSize(8)) {
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
                    200, petByLocationDTOS,
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/get_average_age")
    public ResponseEntity<ResponseDTO> getAverageAge() throws ListDEException {
        try {
            listDEService.getAverageAge();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "el promedio de edad de las mascotas es: " + listDEService.getAverageAge(), null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/send_pets_to_end_by_char")
    public ResponseEntity<ResponseDTO> sendPetsToEndByChar(@RequestBody Map<String, Object> requestData) throws ListDEException {
        try {
            String userString = (String) requestData.get("user");
            char user = userString.toLowerCase().charAt(0);
            listDEService.sendPetsToEndByChar(user);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "mascotas ordenadas", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/order_by_gender")
    public ResponseEntity<ResponseDTO> orderByGender() throws ListDEException {
        try {
            listDEService.orderByGender();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "mascotas ordenadas", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/lose_positions")
    public ResponseEntity<ResponseDTO> losePositions(@RequestBody Map<String, Object> requestBody) throws ListDEException {
        try {
            String id = (String) requestBody.get("id");
            Integer lose = (Integer) requestBody.get("lose");
            listDEService.losePositions(id, lose);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "posiciones reordenadas", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/exchange_list_de")
    public ResponseEntity<ResponseDTO> exchangeExtremes() throws ListDEException {
        try {
            listDEService.exchangeExtremes();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "se han intercambiado los extremos", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/invert_list")
    public ResponseEntity<ResponseDTO> invertList() throws ListDEException {
        try {
            listDEService.invertList();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "lista invertida", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/male_to_beginning")
            public ResponseEntity<ResponseDTO> putKidsToBeginning() throws ListDEException {
        try {
            listDEService.putPetsMaleToBeginning();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "machos agregados al inicio y feminas al final", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/delete_pet_by_age/{age}")
    public ResponseEntity<ResponseDTO> losePositions(@PathVariable byte age) throws ListDEException {
        try {
            listDEService.deleteByAge(age);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "mascotas con esa edad eliminadas", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/earn_positions")
    public ResponseEntity<ResponseDTO> earnPositions(@RequestBody Map<String, Object> requestBody) throws ListDEException {
        try {
            String id = (String) requestBody.get("id");
            Integer earn = (Integer) requestBody.get("earn");
            listDEService.earnPositions(id, earn);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "posiciones re ordenadas", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/generate_report_by_age")
    public ResponseEntity<ResponseDTO> generateReportByAge() throws ListDEException {
        try {
            return new ResponseEntity<>(new ResponseDTO(200, listDEService.generateReportByAge(), null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/delete_pet_kamikaze/{id}")
    public ResponseEntity<ResponseDTO> deletePetKamikaze(@PathVariable String id) throws ListDEException {
        try {
            listDEService.deleteKamikazePet(id);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Mascota eliminada", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}//end of ListDE controller----------------------------------------
