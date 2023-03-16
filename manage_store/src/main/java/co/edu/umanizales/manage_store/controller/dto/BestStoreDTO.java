package co.edu.umanizales.manage_store.controller.dto;

import co.edu.umanizales.manage_store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestStoreDTO {
    //definition of the objects and variables------------------------------------------
    private Store store;
    private int quantity;
}//end of best store DTO---------------------------------------------------------------
