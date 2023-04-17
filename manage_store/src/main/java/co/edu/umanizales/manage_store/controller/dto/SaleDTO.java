package co.edu.umanizales.manage_store.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleDTO {
    //creation of the objects and variables------------------------
    private String sellerId;
    private String storeId;
    private int quantity;
}//end of saleDTO-------------------------------------------------
