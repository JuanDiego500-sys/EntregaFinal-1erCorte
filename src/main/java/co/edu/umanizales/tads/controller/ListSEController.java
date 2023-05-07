package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.exception.RequestException;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    //to get all the list se--------------------------------------------------------------------
    @GetMapping(path = "/get_list")
    public ResponseEntity<ResponseDTO> getKids() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getKids(), null), HttpStatus.OK);
    }

    //to delete a kid with the id---------------------------------------------------------------
    @GetMapping(path = "/delete_kid/{id}")
    public ResponseEntity<ResponseDTO> deleteKid(@PathVariable String id) throws ListSEException {
        try {
            listSEService.deleteKid(id);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Niño eliminado", null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //to order the list by gender---------------------------------------------------------------
    @GetMapping(path = "/order_by_gender")
    public ResponseEntity<ResponseDTO> orderByGender() throws ListSEException{
        try {
            listSEService.orderByGender();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Niños ordenados", null), HttpStatus.OK);
        }catch(ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //to exchange the edges of the list----------------------------------------------------------
    @GetMapping(path = "/exchange_list_se")
    public ResponseEntity<ResponseDTO> exchangeExtremes() throws ListSEException {
        try {
            listSEService.exchangeExtremes();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "se han intercambiado los extremos", null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //method to invert the list------------------------------------------------------------------
    @GetMapping(path = "/invert_list")
    public ResponseEntity<ResponseDTO> invertList() throws ListSEException {
        try {
            listSEService.invertList();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "lista invertida", null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //method to put the kids to the beginning and the girls to the end--------------------------------
    @GetMapping(path = "/kids_to_beginning")
    public ResponseEntity<ResponseDTO> putKidsToBeginning() throws ListSEException{
        try {
            listSEService.putKidsToBeginning();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "niños agregados al inicio y niñas agregadas al final", null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //method to delete kids by age--------------------------------------------------------------------
    @GetMapping(path = "/delete_kid_by_age/{age}")
    public ResponseEntity<ResponseDTO> losePositions(@PathVariable byte age) throws ListSEException {
        try {
            listSEService.deleteKidByAge(age);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Niños con esa edad eliminados", null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //method to get the average age of the kids--------------------------------------------------------
    @GetMapping(path = "/get_average_age")
    public ResponseEntity<ResponseDTO> get_average_age() {
        try {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "el promedio de edad de los niños es: " + listSEService.getAverageAge(), null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //method to send the kids to the end by a char-------------------------------------------------------
    @PostMapping(path = "/send_kids_to_end_by_char")
    public ResponseEntity<ResponseDTO> sendKidsToEndByChar(@RequestBody Map<String, Object> requestData)throws ListSEException{
        try {
            String userString = (String) requestData.get("user");
            char user = userString.toLowerCase().charAt(0);
            listSEService.sendKidsToEndByChar(user);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "ordenados", null), HttpStatus.OK);
        }catch(ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //method to lose positions-------------------------------------------------------------------------
    @PostMapping(path = "/lose_positions")
    public ResponseEntity<ResponseDTO> losePositions(@RequestBody Map<String, Object> requestBody) throws ListSEException {
        try {
            String id = (String) requestBody.get("id");
            Integer lose = (Integer) requestBody.get("lose");
            listSEService.losePositions(id, lose);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "posiciones re ordenadas", null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //method to earn positions-----------------------------------------------------------------------
    @PostMapping(path = "/earn_positions")
    public ResponseEntity<ResponseDTO> earnPositions(@RequestBody Map<String, Object> requestBody)throws ListSEException {
        try {
            String id = (String) requestBody.get("id");
            Integer earn = (Integer) requestBody.get("earn");
            listSEService.earnPositions(id, earn);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "posiciones re ordenadas", null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/generate_report_by_age")
    public ResponseEntity<ResponseDTO>  generateReportByAge()throws  ListSEException{
        try {
            return new ResponseEntity<>(new ResponseDTO(200, listSEService.generateReportByAge(), null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> addKid( @RequestBody @Valid KidDTO kidDTO) throws ListSEException {
        try {
            Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
                if (location == null) {
                    return new ResponseEntity<>(new ResponseDTO(
                            404, "La ubicación no existe",
                            null), HttpStatus.OK);
                }
                listSEService.add(
                        new Kid(kidDTO.getIdentification(),
                                kidDTO.getName(), kidDTO.getAge(),
                                kidDTO.getGender(), location));
                return new ResponseEntity<>(new ResponseDTO(
                        200, "Se ha adicionado el petacón",
                        null), HttpStatus.OK);
        } catch (ListSEException e) {
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(path = "/kidsbylocations/{age}")
    public ResponseEntity<ResponseDTO> getKidsByLocation(@PathVariable byte age){
        try {
            List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
            for (Location loc : locationService.getLocations()) {
                if (listSEService.getKids().getData().getAge() > age) {
                    int count = listSEService.getCountKidsByLocationCode(loc.getCode());
                    int male = listSEService.getCountKidsByLocationCodeAndMale(loc.getCode());
                    int female = listSEService.getCountKidsByLocationCodeAndFemale(loc.getCode());
                    if (count > 0) {
                        kidsByLocationDTOList.add(new KidsByLocationDTO(loc, female, male, count));
                    }
                }
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200, kidsByLocationDTOList,
                    null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/kids_by_department/{age}")
    public ResponseEntity<ResponseDTO> getKidsByDepartment(@PathVariable byte age){
        try {
            List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
            for (Location loc : locationService.getLocationsByCodeSize(5)) {
                if (listSEService.getKids().getData().getAge() > age) {
                    int count = listSEService.getCountKidsByLocationCode(loc.getCode());
                    int male = listSEService.getCountKidsByLocationCodeAndMale(loc.getCode());
                    int female = listSEService.getCountKidsByLocationCodeAndFemale(loc.getCode());
                    if (count > 0) {
                        kidsByLocationDTOList.add(new KidsByLocationDTO(loc, female, male, count));
                    }
                }
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200, kidsByLocationDTOList,
                    null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/kids_by_city/{age}")
    public ResponseEntity<ResponseDTO> getKidsByCity(@PathVariable byte age)throws ListSEException{
        try {
            List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
            for (Location loc : locationService.getLocationsByCodeSize(8)) {
                if (listSEService.getKids().getData().getAge() > age) {
                    int count = listSEService.getCountKidsByLocationCode(loc.getCode());
                    int male = listSEService.getCountKidsByLocationCodeAndMale(loc.getCode());
                    int female = listSEService.getCountKidsByLocationCodeAndFemale(loc.getCode());
                    if (count > 0) {
                        kidsByLocationDTOList.add(new KidsByLocationDTO(loc, female, male, count));
                    }
                }
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200, kidsByLocationDTOList,
                    null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }












}//end of list se controller-----------------------------------------------------------------------------------
