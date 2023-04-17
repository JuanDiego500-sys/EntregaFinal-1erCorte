package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.controller.dto.BestSellerDTO;
import co.edu.umanizales.manage_store.controller.dto.BestStoreDTO;
import co.edu.umanizales.manage_store.controller.dto.BetterSellersDTO;
import co.edu.umanizales.manage_store.controller.dto.BetterStoresDTO;
import co.edu.umanizales.manage_store.model.Sale;
import co.edu.umanizales.manage_store.model.Seller;
import co.edu.umanizales.manage_store.model.Store;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class SaleService {
    //creation of lists------------------------------------------------------------
    private List<Sale> sales;
    private List<BetterSellersDTO>betters;
    private List<BetterStoresDTO>betterStoresDTOS;
    //constructors to the lists-------------------------------------------------
    public SaleService() {
        this.sales = new ArrayList<>();
        this.betters = new ArrayList<>();
        this.betterStoresDTOS = new ArrayList<>();
    }
    //add to the lists-------------------------------------------------------------------
    public void addSlr(BetterSellersDTO betterSellersDTO) {
        this.betters.add(betterSellersDTO);
    }
    public void addStr(BetterStoresDTO betterStoresDTO){
        this.betterStoresDTOS.add(betterStoresDTO);
    }

    public void addSale(Sale sale) {
        this.sales.add(sale);
    }

    //method to find the total Sales of the sellers-------------------------------------
    public int getTotalSales() {
        int sum = 0;
        for (Sale sale : sales) {
            sum = sum + sale.getQuantity();
        }
        return sum;
    }

    //this method returns the total sales of a seller--------------------------------------
    public int getTotalSalesBySeller(String codeSeller) {
        int sum = 0;
        for (Sale sale : sales) {
            if (sale.getSeller().getCode().equals(codeSeller)) {
                sum = sum + sale.getQuantity();
            }

        }
        return sum;
    }

    //method to find the best seller----------------------------------------------------
    public BestSellerDTO getBestSeller(List<Seller> sellers) {
        BestSellerDTO bestSellerDTO = new BestSellerDTO(new Seller(), 0);
        for (Seller seller : sellers) {
            int quant = getTotalSalesBySeller(seller.getCode());
            if (quant >= bestSellerDTO.getQuantity()) {
                bestSellerDTO = new BestSellerDTO(seller, quant);
            }
        }
        return bestSellerDTO;
    }

    //method to get total sales by store-------------------------------------------------
    public int getTotalSalesByStore(String codeStore) {
        int sum = 0;
        for (Sale sale : sales) {
            if (sale.getStore().getCode().equals(codeStore)) {
                sum = sum + sale.getQuantity();
            }

        }
        return sum;
    }

    //method to calculate the best store-----------------------------------------------
    public BestStoreDTO getBestStore(List<Store> stores) {
        BestStoreDTO bestStoreDTO = new BestStoreDTO(new Store(), 0);
        for (Store store : stores) {
            int quant = getTotalSalesByStore(store.getCode());
            if (quant >= bestStoreDTO.getQuantity()) {
                bestStoreDTO = new BestStoreDTO(store, quant);
            }

        }
        return bestStoreDTO;
    }

    //method to get the betters sellers into the company


    public List<BetterSellersDTO> getBetterSellers(List<Seller> sellers, int min) {

        for (Seller seller : sellers) {
            if (getTotalSalesBySeller(seller.getCode()) > min) {
                addSlr(new BetterSellersDTO(seller));

            }
        }
        return betters;
    }
    //method to get the better stores of the company----------------------------------
    public List<BetterStoresDTO> getBetterStores(List<Store>stores, int min){
        for (Store store : stores){
            if (getTotalSalesByStore(store.getCode()) > min){
                addStr(new BetterStoresDTO(store));
            }
        }
        return betterStoresDTOS;
    }
}//end of sale service------------------------------------------------------------------
