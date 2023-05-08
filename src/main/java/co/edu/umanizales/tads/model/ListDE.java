package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public void addPet(Pet pet) throws ListDEException {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                if(temp.getData().getIdentification().equals(pet.getIdentification())){
                    throw new ListSEException("400","Ya existe una mascota con ese codigo");
                }
                temp = temp.getNext();
            }
            if(temp.getData().getIdentification().equals(pet.getIdentification())){
                throw new ListSEException("400","Ya existe una mascota con ese codigo");
            }
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            temp.setPrevious(temp);
        } else {
            this.head = new NodeDE(pet);
        }
        size++;
    }

    public void addPetToBeginning(Pet pet) {
        NodeDE newNode = new NodeDE(pet);
        if (this.head != null) {
          this.head.setPrevious(newNode);
        }
        this.head = newNode;
        size++;
    }
    public void deletePet(String id) throws ListDEException{
        NodeDE empt = null;
        NodeDE temp = head;

        while (temp != null && !temp.getData().getIdentification().equals(id)) {
            empt = temp;
            temp = temp.getNext();
        }

        if (temp == null) {
            throw new ListDEException("400","La mascota a eliminar no existe");
        }

        if (empt == null) {
            head = temp.getNext();
        } else {
            empt.setNext(temp.getNext());
        }if (temp.getNext() !=null){
            temp.getNext().setPrevious(empt);
        }
        size--;
    }
    public void addInPos(Pet pet, int pos2) {
        NodeDE temp = head;
        NodeDE newNode = new NodeDE(pet);
        if (this.head==null){
            addPet(pet);
            return;
        }

        if (pos2 >= size)//to do a validation and add the kid in the last position
            addPet(pet);
        if (pos2 == 0) {
            addPetToBeginning(pet);

        }
            for (int i = 0; temp.getNext() != null && i < pos2 - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);

            if (newNode.getNext() !=null){
                newNode.getNext().setPrevious(newNode);
            }
            newNode.setPrevious(temp);
            size++;

    }

    public int getCounPetsByLocationCode(String code)throws ListDEException{
        int count =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }else{
            throw new ListDEException("400","No hay mascotas en la lista");
        }
        return count;
    }

    public int getCountPetsByLocationCodeAndMale(String code)throws ListDEException{
        int male =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)&&temp.getData().getGender() == 'M'){
                    male++;
                }
                temp = temp.getNext();
            }
        }else{
            throw new ListDEException("400","No hay mascotas en la lista");
        }
        return male;
    }
    public int getCountPetsByLocationCodeAndFemale(String code)throws ListDEException{
        int female =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)&&temp.getData().getGender() == 'F'){
                    female++;
                }
                temp = temp.getNext();
            }
        }else{
            throw new ListDEException("400","No hay mascotas en la lista");
        }
        return female;
    }
    //from here,  I have to review all the next methods , and prove each one--------------------------------------------
    public void orderByGender() throws ListDEException{
        ListDE listDE1 = new ListDE();
        int sum = 0;
        NodeDE temp = head;
        if (head == null) {
            throw new ListDEException("400","No hay mascotas en la lista");
        } else {
            boolean hasFemale = false;
            boolean hasMale = false;
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listDE1.addPetToBeginning(temp.getData());
                    hasFemale = true;
                } else {
                    hasMale = true;
                }
                temp = temp.getNext();
            }
            if (!hasFemale || !hasMale) {
                throw new ListDEException("400","La lista debe contener al menos una mascota de cada género");
            }
            temp = head;
            sum = listDE1.getSize();
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listDE1.addInPos(temp.getData(), sum);
                    temp = temp.getNext();
                    sum = sum + 2;
                } else {
                    temp = temp.getNext();
                }
            }
            this.head = listDE1.getHead();
        }
    }

    public void losePositions(String id, int lose) {
        NodeDE temp = head;
        int sum = 0;
        ListDE listDE1 = new ListDE();
        boolean found = false;
        boolean added = false; // bandera para verificar si la mascota ya ha sido agregada a listDE1
        while (temp != null) {
            if (temp.getData().getIdentification().equals(id)) {
                if (added) {
                    // Si la mascota ya ha sido agregada a listDE1, no la agregues de nuevo.
                    listDE1.addPet(temp.getData());
                } else {
                    // Agrega la mascota a listDE1 y marca la bandera added como verdadera.
                    listDE1.addPet(temp.getData());
                    added = true;
                }
                found = true;
            } else {
                listDE1.addPet(temp.getData());
            }
            temp = temp.getNext();
        }
        if (!found) {
            throw new ListDEException("400","El id buscado no se encuentra en la lista");
        }
        sum = lose + getPosById(id);
        if (sum>size){
            addPet(getPetById(id));
        } else {
            if (!listDE1.getPetByPos(sum).getIdentification().equals(id)) {
                listDE1.addInPos(getPetById(id), sum);
            }
            this.head = listDE1.getHead();
        }
    }
    public Pet getPetByPos(int pos) {
        if (pos < 0 || pos >= size) {
            return null;
        } else {
            NodeDE temp = head;
            for (int i = 0; i < pos; i++) {
                temp = temp.getNext();
            }
            return temp.getData();
        }
    }


    public int getPosById(String id) {
        NodeDE temp = this.head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                acum = acum + 1;
                temp = temp.getNext();

            }
        }
        return acum;
    }

    public Pet getPetById(String id) {
        NodeDE temp = head;
        if (head != null) {
            while (temp!=null){
                temp= temp.getPrevious();
            }
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                temp = temp.getNext();
            }
        }
        Pet pet = new Pet(temp.getData().getAge(), temp.getData().getName(),
                temp.getData().getType(), temp.getData().getRace(), temp.getData().getGender(),temp.getData().getIdentification(), temp.getData().getLocation());
        return pet;
    }
    public void exchangeExtremes() throws ListDEException{
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp!=null){
                temp = temp.getPrevious();
            }Pet copy = temp.getData();
        if (this.head.getNext() != null && this.head.getPrevious() != null) {
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }//temp is in the last data
            //creation of a copy
            this.head.setData(temp.getData());//to use the data of the temp that is in the last data
            temp.setData(copy);//the head data into the last
        }
        }else{
            throw new ListDEException("400","No hay suficientes datos en la lista");
        }

    }
    public void invertList() throws ListDEException{
        NodeDE temp = this.head;
        ListDE listDE2 = new ListDE();
        if (this.head != null) {
            while (temp != null) {
                listDE2.addPetToBeginning(temp.getData());
                temp = temp.getNext();
            }
            this.head = listDE2.getHead();
        }else{
            throw new ListDEException("400","No hay suficientes datos en la lista");
        }
    }
    public void putPetsToBeginning() throws ListDEException{
        NodeDE temp = this.head;
        ListDE listDE1 = new ListDE();
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listDE1.addPetToBeginning(temp.getData());

                } else if (temp.getData().getGender() == 'F') {
                    listDE1.addPet(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listDE1.getHead();
        }else{
            throw new ListDEException("400","No hay suficientes datos en la lista");
        }
    }

    //method to delete a pet with a specified age-----------------------------------------------
    public void deleteByAge(byte age) throws ListDEException{
        NodeDE temp = this.head;
        ListDE listDE1 = new ListDE();
        boolean found = false;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getAge() != age) {
                    listDE1.addPetToBeginning(temp.getData());
                }else{
                    found = true;
                }
                temp = temp.getNext();
            }
            if (!found){
                throw new ListDEException("404","No hay mascotas con la edad indicada");
            }
            this.head = listDE1.getHead();
        }else{
            throw new ListDEException("400","No hay suficientes datos en la lista");
        }
    }

    //method to get the average age of the pets-----------------------------------------------
    public double getAverageAge() throws ListDEException{
        double averageAge = 0;
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                averageAge = averageAge + temp.getData().getAge();
                temp = temp.getNext();
            }
            averageAge = averageAge / size;
            return averageAge;
        }else {
            throw new ListDEException("400","No hay datos en la lista");
        }
    }

    //method to earn positions------------------------------------------------
    public void earnPositions(String id, int earn) throws ListDEException {
        NodeDE temp = head;
        int sum = 0;
        ListDE listDE1 = new ListDE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getIdentification().equals(id)) {
                    listDE1.addPet(temp.getData());
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
            }
        }else{
            throw new ListDEException("400","No hay datos en la lista");
        }
        sum = getPosById(id) - earn;
        listDE1.addInPos(getPetById(id), sum);
        this.head = listDE1.getHead();
    }

    //method to send the kids with a specifically character in its name------------------------
    public void sendPetsToEndByChar(char user)throws ListDEException {
        ListDE listDE1 = new ListDE();
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getName().charAt(0) != user) {
                    listDE1.addPetToBeginning(temp.getData());
                } else {
                    listDE1.addPet(temp.getData());
                }
                temp = temp.getNext();
            }
        }else{
            throw new ListDEException("400","no hay datos en la lista");
        }
        this.head = listDE1.getHead();
    }
    public String generateReportByAge() throws ListDEException {
        int quantity1 = 0;
        int quantity2 = 0;
        int quantity3 = 0;
        int quantity4 = 0;
        int quantity5 = 0;
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getAge() >= 0 && temp.getData().getAge() <= 3) {
                    quantity1++;
                } else if (temp.getData().getAge() > 3 && temp.getData().getAge() <= 6) {
                    quantity2++;
                } else if (temp.getData().getAge() > 6 && temp.getData().getAge() <= 9) {
                    quantity3++;
                } else if (temp.getData().getAge() > 9 && temp.getData().getAge() <= 12) {
                    quantity4++;
                } else if (temp.getData().getAge() > 12 && temp.getData().getAge() <= 15) {
                    quantity5++;
                }
                temp = temp.getNext();
            }


        }else{
            throw new ListDEException("400","no hay datos en la lista");
        }

        return " mascotas entre 0-3 años:" + quantity1 +
                " mascotas entre 4-6 años:" + quantity2 +
                " mascotas entre 7-9 años:" + quantity3 +
                " mascotas entre 10-12 años:" + quantity4 +
                " mascotas entre 13-15 años:" + quantity5;
    }
    public int verifyId(PetDTO petDTO) {
        NodeDE temp = this.head;
        boolean found = false;
        while (temp != null) {
            if (temp.getData().getIdentification().equals(petDTO.getIdentification())) {
                found = true;
                break;
            }
            temp = temp.getNext();
        }
        return found ? 1 : 0;
    }
    /*
method to delete the pet "kamikaze" it means that the next and the previous release the data to be deleted.
primer paso: si hay datos , creo un ayudante y lo posiciono en cabeza, creo otro ayudante y lo pongo como nulo. si
no hay datos, termina el procedimiento y mandale una excepción de tipo listDE.
segundo paso: mientras temporal sea distinto de nulo , y mientras los datos del temporal en donde esté parado sean
diferentes de la identificacion requerida , al ayudante dos o "empt" digale que es igual a temp. ¿para que eso? porque
necesito que empt se quede un dato atras de temp cuando se termine de iterar en la lista. Teniendo ya eso , ahora debo
decirle a temporal que se pase al siguiente hasta que en un momento va a estar en el requerido. ahora, que pasa si ese
a eliminar es el primer dato? entonces pregunto , si temp.getnext es diferente de nulo entonces le digo a cabeza que se haga
set en el siguiente dato para no perder datos y quito el enlace de la cabeza vieja con el dato segundo. de otra manera significa que cabeza es el unico dato
entonces bastaria con decirle a cabeza que se ponga como nulo.
paso 3: si el temp cuando llama al next es nulo significa que estamos ante el último dato , entonces solo se debe eliminar el enlace que tiene con el previo.
cada que yo termine de eliminar un dato lo que debo hacer es que debo decirle a el empt que ponga su set next en dos next adelante para saltarse el nodo a eliminar,y que
a ese mismo previo lo ponga en dos previos mas atrás para no perder los datos.

 */
    public void deleteKamikazePet(String id) throws ListDEException{
        if (this.head != null){
            NodeDE empt = null;
            NodeDE temp = this.head;
            while (temp!= null && temp.getData().getIdentification().equals(id)){
                empt = temp;
                temp = temp.getNext();
            }if (empt == null){
                this.head = null;

            }
            if (temp.getNext() != null){
                temp.setNext(temp);
                temp.setPrevious(temp);
                empt.setNext(empt.getNext().getNext());
                empt.setPrevious(empt.getPrevious().getPrevious());

            }
        }else {
            throw new ListDEException("404","No hay datos en la lista, no se pueden eliminar niños");
        }
        size--;
    }


    public String toListString(){
        StringBuilder sb = new StringBuilder();
        NodeDE temp = this.head;
        sb.append("[");
        while (temp != null){
            sb.append(temp.getData().toString());
            temp = temp.getNext();
            if (temp != null){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();

    }




}//end of list De-------------------------------------
