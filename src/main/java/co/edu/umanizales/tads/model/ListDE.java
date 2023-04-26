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

    public void addPetToBeginning(Pet pet) {
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
            addPetToBeginning(pet);
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
    public void orderByGender() {
        ListDE listDE1 = new ListDE();

        int sum = 0;
        NodeDE temp = head;
        NodeDE empt = head;
        if (head == null) {
            System.out.println("Lo siento, no hay datos");
        } else {
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listDE1.addPetToBeginning(temp.getData());

                }
                temp = temp.getNext();
            }while (empt !=null){
                if (empt.getData().getGender() == 'F'){
                    listDE1.addPetToBeginning(empt.getData());
                }
                empt = empt.getPrevious();
            }
            temp = head;
            empt = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listDE1.addInPos(temp.getData(), sum);
                    temp = temp.getNext();
                    sum = sum + 2;
                } else {
                    temp = temp.getNext();

                }

            }sum =0;
            while (empt!=null){
                if (empt.getData().getGender() == 'M'){
                    listDE1.addInPos(empt.getData(),sum);
                    empt = empt.getPrevious();
                    sum = sum + 2;
                }else{
                    empt = empt.getPrevious();
                }
            }
            this.head = listDE1.getHead();
        }
    }


    public int getCounPetsByLocationCode(String code){
        int count =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            NodeDE empt= this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }while (empt !=null){
                if (empt.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                empt = empt.getPrevious();
            }
        }
        return count;
    }

    public int getCountPetsByLocationCodeAndMale(String code){
        int male =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            NodeDE empt = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)&&temp.getData().getGender() == 'M'){
                    male++;
                }
                temp = temp.getNext();
            }while (empt != null){
                if (empt.getData().getLocation().getCode().equals(code) && temp.getData().getGender() == 'M'){
                    male++;
                }
                empt = empt.getPrevious();
            }
        }
        return male;
    }
    public int getCountPetsByLocationCodeAndFemale(String code){
        int female =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            NodeDE empt= this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)&&temp.getData().getGender() == 'F'){
                    female++;
                }
                temp = temp.getNext();
            }while (empt!=null){
                if (empt.getData().getLocation().getCode().equals(code) && temp.getData().getGender() == 'F'){
                    female++;
                }
                empt= empt.getPrevious();
            }
        }
        return female;
    }



}//end of list De-------------------------------------
