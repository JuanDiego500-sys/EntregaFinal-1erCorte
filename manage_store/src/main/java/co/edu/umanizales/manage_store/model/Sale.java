package co.edu.umanizales.manage_store.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sale {//beginning of sale
    //-----------definition of a variables------------------------
    private Store store;
    private Seller seller;
    private int quantity;

}// end of sale
