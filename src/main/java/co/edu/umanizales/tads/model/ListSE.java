package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class ListSE {
    private Node head;
    private int size;

    /*
    Algoritmo de adicionar al final
    Entrada
        un niño
    si hay datos
    si
        llamo a un ayudante y le digo que se posicione en la cabeza
        mientras en el brazo exista algo
            pasese al siguiente
        va estar ubicado en el ùltimo

        meto al niño en un costal (nuevo costal)
        y le digo al ultimo que tome el nuevo costal
    no
        metemos el niño en el costal y ese costal es la cabeza
     */
    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
        size++;
    }

    /* Adicionar al inicio
    si hay datos
    si
        meto al niño en un costal (nuevocostal)
        le digo a nuevo costal que tome con su brazo a la cabeza
        cabeza es igual a nuevo costal
    no
        meto el niño en un costal y lo asigno a la cabez
     */
    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
        size++;
    }//end of the addtostart------------------------------------------------------------


    /*
        the method to add a kid into a specific position:
        1)ask the position
        2)do the for to traverse into the list and search the position of the other kid
        3)When it finds the pos , add the kid in that space.
         */
    //method to add a new node and kid in the pos that the user says---------------------
    public void addInPos(Kid kid, int pos) {
        Node temp = head;
        Node newNode = new Node(kid);
        int listLength = getLength();
        if (pos < 0 || pos >= listLength)//to do a validation and add the kid in the last position
            add(kid);
        if (pos == 0) {
            newNode.setNext(head);//to actualize the head
            head = newNode;

        } else {
            for (int i = 0; temp.getNext() != null && i < pos - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
        size++;
    }

    //method to delete a kid receiving his id
    public void deleteKid(String id) {
        Node empt = null;
        Node temp = head;

        while (temp != null && !temp.getData().getIdentification().equals(id)) {
            empt = temp;
            temp = temp.getNext();
        }

        if (temp == null) {
            return;
        }

        if (empt == null) {
            head = temp.getNext();
        } else {
            empt.setNext(temp.getNext());
        }
        size--;
    }

    //method to order by gender-------------------------------------------------------------------------------------
    public void orderByGender() {
        ListSE listSE1 = new ListSE();

        int sum = 0;
        Node temp = head;
        if (head == null) {
            System.out.println("Lo siento, no hay datos");
        } else {
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listSE1.addToStart(temp.getData());

                }
                temp = temp.getNext();
            }
            temp = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listSE1.addInPos(temp.getData(), sum);
                    temp = temp.getNext();
                    sum = sum + 2;
                } else {
                    temp = temp.getNext();

                }
            }
            this.head = listSE1.getHead();
        }
    }

    //method to lose positions-------------------------------------------------------------------------------------
    public void losePositions(String id, int lose) {
        Node temp = head;
        int sum = 0;
        ListSE listSE1 = new ListSE();
        if (head != null) {
            while (temp != null  ) {
                if (!temp.getData().getIdentification().equals(id)) {
                    listSE1.add(temp.getData());
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
            }
        }
        sum = lose + getPosById(id);
        listSE1.addInPosValidations(getKidById(id), sum);
        this.head = listSE1.getHead();
    }
    //method to add in pos when you need validations--------------------------------------------------------------------
    public void addInPosValidations(Kid kid, int pos2) {
        Node  temp = head;
        Node newNode = new Node(kid);
        int listLength = getLength();
        if (pos2 < 0 || pos2 >= listLength)//to do a validation and add the kid in the last position
            add(kid);
        if (pos2 == 0) {
            newNode.setNext(head);//to actualize the head
            head = newNode;

        } else {
            for (int i = 0; temp.getNext() != null && i < pos2 - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
    }

    //method to get the pos of the data by an id-----------------------------------------------------------------------
    public int getPosById(String id) {
        Node temp = head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                acum = acum + 1;
                temp = temp.getNext();

            }


        }
        return acum;
    }

    //method to get the data by an id and return that data into a kid-------------------------------------------------
    public Kid getKidById(String id) {
        Node temp = head;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                temp = temp.getNext();
            }
        }
        Kid kid = new Kid(temp.getData().getIdentification(), temp.getData().getName(),
                temp.getData().getAge(), temp.getData().getGender());
        return kid;
    }


    //method to get the length of the list-------------------------------------------------------
    public int getLength() {
        int total = 0;
        Node actu = head;
        while (actu != null) {
            total++;
            actu = actu.getNext();
        }
        return total;
    }
    //method to exchange the extremes of the list, the head to the last and vice versa--------------
    public void exchangeExtremes(){
        if ( this. head != null && this.head.getNext() != null){
            Node temp = this.head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }//temp is in the last data
            Kid copy = this.head.getData();//creation of a copy
            this.head.setData(temp.getData());//to use the data of the temp that is in the last data
            temp.setData(copy);//the head data into the last

        }

    }
    //method to invert the list se-----------------------------------------------------------------------------
    public void invertList(){
        Node temp = this.head;
        ListSE listSE2 = new ListSE();
        if(this.head != null){
            while (temp !=null){
                listSE2.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head= listSE2.getHead();
        }
    }
    //method to put the kids at the beginning and the girls at the end------------------------------------------
    public void putKidsToBeginning(){
        Node temp = this.head;
        ListSE listSE1 = new ListSE();
        if(this.head != null){
            while (temp !=null){
                if (temp.getData().getGender() == 'M'){
                    listSE1.addToStart(temp.getData());

                }else if(temp.getData().getGender() == 'F'){
                    listSE1.add(temp.getData());
                }
                temp=temp.getNext();
            }
            this.head = listSE1.getHead();
        }
    }
    //method to delete a kid with a specified age-----------------------------------------------
    public void deleteByAge(byte age){
        Node temp = this.head;
        ListSE listSE1 = new ListSE();
        if (this.head != null){
            while (temp != null){
                if (temp.getData().getAge() != age){
                    listSE1.addToStart(temp.getData());
                }
                temp= temp.getNext();
            }
            this.head= listSE1.getHead();
        }
    }
    //method to get the average age of the kids-----------------------------------------------
    public double getAverageAge(){
        double averageAge = 0;
        Node temp = this.head;
        if (this.head != null){
            while (temp != null){
                averageAge = averageAge + temp.getData().getAge();
                temp = temp.getNext();
            }
            averageAge = averageAge / getLength();
            return averageAge;
        }
        return averageAge;
    }
    //method to earn positions------------------------------------------------
    public void earnPositions(String id, int earn) {
        Node temp = head;
        int sum = 0;
        ListSE listSE1 = new ListSE();
        if (head != null) {
            while (temp != null  ) {
                if (!temp.getData().getIdentification().equals(id)) {
                    listSE1.add(temp.getData());
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
            }
        }
        sum = earn - getPosById(id);
        listSE1.addInPosValidations(getKidById(id), sum);
        this.head = listSE1.getHead();
    }



}//end of list se---------------------------------------------------------------------------------

