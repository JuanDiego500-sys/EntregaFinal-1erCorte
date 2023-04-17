package co.edu.umanizales.manage_store.controller;


import co.edu.umanizales.manage_store.controller.dto.ResponseDTO;
import co.edu.umanizales.manage_store.controller.dto.SaleDTO;
import co.edu.umanizales.manage_store.model.Sale;
import co.edu.umanizales.manage_store.model.Seller;
import co.edu.umanizales.manage_store.model.Store;
import co.edu.umanizales.manage_store.service.SaleService;
import co.edu.umanizales.manage_store.service.SellerService;
import co.edu.umanizales.manage_store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "sale")
public class SaleController {
    //configuration of the connection with the others services------------------------------------------------------
    @Autowired
    private SaleService saleService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StoreService storeService;

    //method to show the sales that you have------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<ResponseDTO> getSales() {
        if (!saleService.getSales().equals(0)) {

            return new ResponseEntity<>(
                    new ResponseDTO(200, "las ventas totales son:" +
                            saleService.getSales(),
                            null),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new ResponseDTO(409,
                            "no hay ventas registradas",
                            null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    //method to create a sale and show that in the postman --------------------------------------------------------
    @PostMapping
    public ResponseEntity<ResponseDTO> createSale(@RequestBody
                                                  SaleDTO saleDTO) {
        Seller findSeller = sellerService.getSellerById(saleDTO.getSellerId());
        if (findSeller == null) {
            return new ResponseEntity<>(new ResponseDTO(409,
                    "El vendedor ingresado no existe", null),
                    HttpStatus.BAD_REQUEST);
        }
        Store findStore = storeService.getStoreById(saleDTO.getStoreId());
        if (findStore == null) {
            return new ResponseEntity<>(new ResponseDTO(409,
                    "La tienda ingresada no existe", null),
                    HttpStatus.BAD_REQUEST);
        }
        saleService.addSale(new Sale(findStore, findSeller,
                saleDTO.getQuantity()));
        return new ResponseEntity<>(new ResponseDTO(200,
                "Venta adicionada", null),
                HttpStatus.OK);
    }

    //method to use the total sales and show that into the web------------------------------------------------------
    @GetMapping(path = "/total")
    public ResponseEntity<ResponseDTO> getTotalSales() {
        if (saleService.getTotalSales() != 0) {
            return new ResponseEntity<>(new ResponseDTO(200, "Las ventas totales son: " + saleService.getTotalSales(),
                    null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    409, "No hay ventas registradas", null), HttpStatus.BAD_REQUEST);
        }

    }

    //method to find the total sales of seller by his id code------------------------------------------------
    @GetMapping(path = "/total/{code}")
    public ResponseEntity<ResponseDTO> getTotalSalesBySeller(@PathVariable String code) {
        if (saleService.getTotalSalesBySeller(code) != 0) {
            return new ResponseEntity<>(new ResponseDTO(200, "las ventas totales para el vendedor " + code + " son: " +
                    saleService.getTotalSalesBySeller(code),
                    null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(409, "El vendedor no tiene ventas",
                    null), HttpStatus.BAD_REQUEST);
        }

    }

    //method to find the bestseller and show it in the postman--------------------------------------------------
    @GetMapping(path = "/bestseller")
    public ResponseEntity<ResponseDTO> getBestSeller() {
        if (!sellerService.getSellers().equals(0)) {
            return new ResponseEntity<>(new ResponseDTO(200,
                    "El mejor vendedor es: " + saleService.getBestSeller(sellerService.getSellers()), null)
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(409, "No hay vendedores registrados", null)
                    , HttpStatus.BAD_REQUEST);
        }

    }

    //method to show the calculating of the average sales by store------------------------------------------
    @GetMapping(path = "/averagesalesbystore")//all in
    public ResponseEntity<ResponseDTO> getAverageSalesByStore() {
        if (storeService.getStores().size() > 0) {
            return new ResponseEntity<>(new ResponseDTO(200
                    , "El promedio de ventas por sucursal es: " + (saleService.getTotalSales() / (float) storeService.getStores().size()),
                    null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(409
                    , "No hay tiendas registradas, no se puede calcular el promedio",
                    null), HttpStatus.BAD_REQUEST);
        }

    }

    //method to show the calculating of the total sales by store--------------------------------------------
    @GetMapping(path = "/totalStore/{codeStore}")
    public ResponseEntity<ResponseDTO> getTotalSalesByStore(@PathVariable String codeStore) {
        if (saleService.getTotalSalesByStore(codeStore) != 0) {
            return new ResponseEntity<>(new ResponseDTO(200,
                    "El total de ventas de la sucursal #" + codeStore + " es:" + saleService.getTotalSalesByStore(codeStore),
                    null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(409, "No hay ventas registradas en esta tienda",
                    null), HttpStatus.BAD_REQUEST);
        }

    }

    //method to get the average sales by seller----------------------------------------------------------
    @GetMapping(path = "/averagesalesbyseller")
    public ResponseEntity<ResponseDTO> getAverageSalesBySeller() {
        if (sellerService.getSellers().size() != 0) {
            return new ResponseEntity<>(new ResponseDTO(200
                    , "el promedio de ventas por vendedor es: " + saleService.getTotalSales() /
                    (float) sellerService.getSellers().size(), null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(409
                    , "no hay vendedores registrados, no se puede calcular", null), HttpStatus.BAD_REQUEST);
        }

    }

    //method to show the best store----------------------------------------------------------------------
    @GetMapping(path = "/beststore")
    public ResponseEntity<ResponseDTO> getBestStore() {
        if (!saleService.getBestStore(storeService.getStores()).equals(0)) {
            return new ResponseEntity<>(new ResponseDTO(200,
                    "La mejor tienda es: " + saleService.getBestStore(storeService.getStores()),
                    null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(409, "No hay ventas registradas",
                    null), HttpStatus.OK);
        }

    }
    @GetMapping(path = "/bettersellers/{min}")
    public ResponseEntity<ResponseDTO> getBetterSellers(@PathVariable int min) {
        if (!sellerService.getSellers().equals(0)) {
            return new ResponseEntity<>(new ResponseDTO(200,
                    saleService.getBetterSellers(sellerService.getSellers(),min),
                    null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(409, "no hay vendedores",
                    null), HttpStatus.BAD_REQUEST);
        }

    }
    //path of the better stores----------------------------------------------------------------
    @GetMapping(path = "/better_stores/{min}")
    public ResponseEntity<ResponseDTO> getBetterStores(@PathVariable int min) {
        if (!storeService.getStores().equals(0)) {
            return new ResponseEntity<>(new ResponseDTO(200,
                    saleService.getBetterStores(storeService.getStores(),min),
                    null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(409, "no hay tiendas",
                    null), HttpStatus.BAD_REQUEST);
        }

    }

}//end of sale controller------------------------------------------------------------------------------

