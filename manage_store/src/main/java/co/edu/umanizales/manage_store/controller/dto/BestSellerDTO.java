package co.edu.umanizales.manage_store.controller.dto;

import co.edu.umanizales.manage_store.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestSellerDTO {
    //defintion of the objects and variables-----------------------
    private Seller seller;
    private int quantity;
}//end of best seller dto------------------------------------------
