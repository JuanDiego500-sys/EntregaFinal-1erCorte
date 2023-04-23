package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private int size;
    public void addPet(Pet pet){
        if (this.head != null){
            NodeDE temp = this.head;
            while (temp.getNext() != null){
                temp.getNext();

            }
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            temp.setPrevious(newNode);
        }else{
            this.head = new NodeDE(pet);
        }
        size++;
    }
    public void addPetToBeggining(Pet pet){
        if (this.head!=null){
            NodeDE temp = this.head;
            while (temp.getPrevious() != null){
                temp.getPrevious();
            }
            NodeDE newNode = new NodeDE(pet);
            temp.setPrevious(newNode);
            temp.setNext(newNode);
            this.head = newNode;
        }else{
            this.head = new NodeDE(pet);
        }
        size++;
    }


}//end of list De-------------------------------------
