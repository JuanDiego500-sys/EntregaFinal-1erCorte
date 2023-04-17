package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.model.Seller;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class SellerService {
    //creation of list seller-------------------------
    private List<Seller> sellers;

    //creator method of the list sellers---------------
    public SellerService() {
        this.sellers = new ArrayList<>();
    }

    //method to add a new seller in the list sellers--------------------------
    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    //method to search the seller into the list sellers-----------------------
    public Seller getSellerById(String code) {
        for (Seller seller : sellers) {
            if (seller.getCode().equalsIgnoreCase(code)) {
                return seller;
            }
        }
        return null;

    }


}//end of the seller service--------------------------------------------------
