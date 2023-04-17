package co.edu.umanizales.manage_store.controller;

import co.edu.umanizales.manage_store.controller.dto.ResponseDTO;
import co.edu.umanizales.manage_store.model.Store;
import co.edu.umanizales.manage_store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "store")
public class StoreController {//begginig of the store controller
    //------------------creation of a private variable-----------------------
    @Autowired
    private StoreService storeService;

    //--------------creation of responses to the user-----------------------------
    @GetMapping
    public ResponseEntity<ResponseDTO> getStores() {
        if (!storeService.getStores().equals(0)) {
            return new ResponseEntity<>(new ResponseDTO(
                    200, storeService.getStores(), null
            ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    409, "No hay tiendas registradas", null
            ), HttpStatus.BAD_REQUEST);
        }

    }

    //method to create the store and show that into the postman-------------------------------------
    @PostMapping
    public ResponseEntity<ResponseDTO> createStore(@RequestBody Store store) {
        Store findStore = storeService.getStoreById(store.getCode());
        if (findStore == null) {
            storeService.addStore(store);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Tienda agregada", null
            ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    409, "Ya existe una tienda con ese código", null
            ), HttpStatus.BAD_REQUEST);
        }
    }

    //method to search a store by id and show that response into the postman-----------------------------
    @GetMapping(path = "/{code}")
    public ResponseEntity<ResponseDTO> getStoreById(@PathVariable String code) {
        Store findStore = storeService.getStoreById(code);
        if (findStore == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No existe una tienda con ese código", null
            ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    200, findStore, null
            ), HttpStatus.OK);
        }
    }

}// end of the storeController----------------------------------------------------------------------------
