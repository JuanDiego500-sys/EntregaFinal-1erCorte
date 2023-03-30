package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {
    private ListSE kids;
    //constructor methods-------------------------------------------------------------------
    public ListSEService() {
        kids = new ListSE();
        kids.add(new Kid("1","juan",(byte)13,'M'));
        kids.add(new Kid("2","ana",(byte)14,'F'));
        kids.add(new Kid("3","juana",(byte)15,'F'));
        kids.add(new Kid("4","carlos",(byte)16,'M'));
        kids.add(new Kid("5","mariana",(byte)17,'F'));
        kids.addInPos(new Kid("7","andres",(byte)18,'M'),3);
        kids.losePositions("3",2);
        kids.earnPositions("1",3);
    }
    //all the calls of the methods------------------------------------------------------------
    public void deleteKid(String id){
        kids.deleteKid(id);
    }
    public void orderByGender() {
        kids.orderByGender();
    }
    public void losePositions(String id, int lose) {
        kids.losePositions(id,lose);
    }
    public void exchangeExtremes() {
        kids.exchangeExtremes();
    }
    public void invertList(){kids.invertList();}
    public void putKidsToBeginning(){kids.putKidsToBeginning();}
    public void deleteKidByAge(byte age){kids.deleteByAge(age);}

    public Node getKids()
    {
        return kids.getHead();
    }
    public double getAverageAge(){return kids.getAverageAge();}
}
