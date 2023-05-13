package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.service.ListDECircleService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/list_de_circle")
public class ListDECircleController {
    private ListDECircleService listDECircleService;
    private LocationService locationService;

}
