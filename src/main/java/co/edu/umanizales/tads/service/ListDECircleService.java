package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.ListDECircle;
import co.edu.umanizales.tads.model.NodeDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDECircleService {
    private ListDECircle pets;

    public ListDECircleService() {
        pets = new ListDECircle();
    }
    public NodeDE getPets (){
        return pets.getHead();
    }
}
