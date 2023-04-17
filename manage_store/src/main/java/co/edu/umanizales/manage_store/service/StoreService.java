package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.model.Store;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class StoreService {// beggining of store service----------
    //creation of list stores------------------------------------------
    private List<Store> stores;

    //creator method of the list-------------------------------------
    public StoreService() {

        this.stores = new ArrayList<>();

    }

    //method to add a new store---------------------------------------
    public void addStore(Store store) {
        stores.add(store);
    }

    //method to search the store-------------------------------------
    public Store getStoreById(String code) {
        for (Store store : stores) {
            if (store.getCode().equals(code)) {
                return store;
            }
        }
        return null;
    }

}//end of Store service
