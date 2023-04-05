package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Data
public class ListSEService {
    private ListSE kids;
    //constructor methods-------------------------------------------------------------------
    public ListSEService() {
        kids = new ListSE();
        kids.add(new Kid("1","juan",(byte)13,'M',"Manizales"));
        kids.add(new Kid("2","ana",(byte)1,'F',"bogota"));
        kids.add(new Kid("3","juana",(byte)2,'F',"bogota"));
        kids.add(new Kid("4","carlos",(byte)7,'M',"Armenia"));
        kids.add(new Kid("5","mariana",(byte)9,'F',"pereira"));
        kids.addInPos(new Kid("7","andres",(byte)15,'M',"manizales"),3);
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
    public void earnPositions(String id, int earn){kids.earnPositions(id,earn);}
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
    public void sendKidsToEndByChar(char user){kids.sendKidsToEndByChar(user);}
    public double getAverageAge(){return kids.getAverageAge();}
    public Map<String, Integer> reportByCity(){
        return kids.reportByCity();

    }
    public String generateReportByAge(){
        return kids.generateReportByAge();
    }
}
