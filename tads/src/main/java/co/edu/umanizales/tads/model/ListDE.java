package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public void addPet(Pet pet) {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp.getNext();

            }
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            temp.setPrevious(newNode);
        } else {
            this.head = new NodeDE(pet);
        }
        size++;
    }

    public void addPetToBeggining(Pet pet) {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getPrevious() != null) {
                temp.getPrevious();
            }
            NodeDE newNode = new NodeDE(pet);
            temp.setPrevious(newNode);
            temp.setNext(newNode);
            this.head = newNode;
        } else {
            this.head = new NodeDE(pet);
        }
        size++;
    }

    public void deletePet(String name) {
        NodeDE empt = null;
        NodeDE temp = this.head;
        NodeDE tempDE = this.head;
        NodeDE emptDE = null;

        while (temp != null && !temp.getData().getName().equals(name)) {
            empt = temp;
            temp = temp.getNext();
        }
        while (tempDE != null && !temp.getData().getName().equals(name)) {
            emptDE = tempDE;
            tempDE = temp.getPrevious();
        }

        if (temp == null && tempDE == null) {
            return;
        }

        if (empt == null) {
            this.head = temp.getNext();
        } else {
            empt.setNext(temp.getNext());

        }if(emptDE ==null) {
        this.head = tempDE.getPrevious();
        } else{
        emptDE.setPrevious(tempDE.getPrevious());
    }
        size--;
    }
    public void addInPos(Pet pet, int pos) {
        NodeDE temp = head;
        NodeDE newNode = new NodeDE(pet);
        if (pos < 0 || pos >= size)//to do a validation and add the kid in the last position
            addPet(pet);
        if (pos == 0) {
            addPetToBeggining(pet);
        } else {
            while (temp != null){
            temp.getPrevious();
        }
            for (int i = 0; temp.getNext() != null && i < pos - 1; i++) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            temp.setPrevious(newNode);
        }
        size++;
    }



}//end of list De-------------------------------------
