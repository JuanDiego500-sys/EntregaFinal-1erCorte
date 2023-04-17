package co.edu.umanizales.manage_store.controller;

import co.edu.umanizales.manage_store.controller.dto.ResponseDTO;
import co.edu.umanizales.manage_store.model.Seller;
import co.edu.umanizales.manage_store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "seller")
public class SellerController {//beggining of the seller controller
    //creation of a private variable----------------------------------
    @Autowired
    private SellerService sellerService;

    //creation of responses to the user--------------------------------
    @GetMapping
    public ResponseEntity<ResponseDTO> getSellers() {
        if (!sellerService.getSellers().equals(0)) {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "los vendedores son" + sellerService.getSellers(), null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "No hay vendedores registrados", null), HttpStatus.OK);
        }
    }
    //method to create a seller and show that in the postman--------------------------

    @PostMapping
    public ResponseEntity<ResponseDTO> createSeller(@RequestBody Seller seller) {
        Seller findSeller = sellerService.getSellerById(seller.getCode());
        if (findSeller == null) {
            sellerService.addSeller(seller);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "vendedor agregado", null
            ), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    409, "ya existe un vendedor con ese código", null), HttpStatus.BAD_REQUEST);
        }
    }

    //method to search the seller by id-------------------------------------------------------
    @GetMapping(path = "/{code}")
    public ResponseEntity<ResponseDTO> getSellerById(@PathVariable String code) {
        Seller findSeller = sellerService.getSellerById(code);
        if (findSeller == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No existe un vendedor con ese código", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    200, findSeller, null
            ), HttpStatus.OK);
        }
    }

}//end of the store controller--------------------------------------------------------------
