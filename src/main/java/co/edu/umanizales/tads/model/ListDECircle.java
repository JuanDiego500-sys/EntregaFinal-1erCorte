package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDEException;
import lombok.Data;

@Data
public class ListDECircle {
    private NodeDE head;
    private int size;
    /*metodo agregar al final
    preguntar si hay datos, si no hay entonces le digo a nuevo nodo que es la nueva cabeza, además , le digo que sus
    brazos le apunten a si mismo para empezar a crear la lista circular.
    -----------------------------------------------------------------------------------------------------------------
    si hay datos:
    hago la verificación de que la mascota no exista tomando como referencia a la cabeza , la cual es el primer y ultimo dato.
    ------------------------------------------------------------------------------------------------------------------
    si efectivamente la mascota no existe en la lista, entonces hago lo siguiente:
    meto  a la mascota en un nodo nuevo
    le digo al nuevo nodo que ponga su previo en el anterior de la cabeza.
    le digo a cabeza que me llame al anterior y en ese anterior , que coja el next y lo ponga en el nuevo nodo
    le digo a nuevo nodo que ponga su siguiente en cabeza.
    le digo a cabeza que ponga su previo en el nuevo nodo.
    y finalmente ya me queda añadido al final en cualquier caso.
    entonces actualizo la variable size de la lista.
     */
    public void addPetToEnd(Pet pet) throws ListDEException {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != head) {
                if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                    throw new ListDEException("400", "Ya existe una mascota con ese codigo");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                throw new ListDEException("400", "Ya existe una mascota con ese codigo");
            }

            NodeDE newNode = new NodeDE(pet);
            newNode.setPrevious(this.head.getPrevious());
            this.head.getPrevious().setNext(newNode);
            newNode.setNext(this.head);
            this.head.setPrevious(newNode);
        } else {
            this.head = new NodeDE(pet);
            this.head.setPrevious(head);
            this.head.setNext(head);
        }
        size++;
    }
    /*
    para agregar al inicio, si no hay datos entonces llamo al metodo agregar al final que tengo arriba que es el que
    ya tengo con esa validación ya hecha.
    ------------------------------------------------------------------------------------------------------------------
    si hay datos, busco si el dato que se desea ingresar, ya está en la lista, si no , se procede así:
    el next de nuevo nodo va para el siguiente a la cabeza.
    el previo de nuevo nodo va para la cabeza.
    el previo del siguiente a la cabeza va para nuevo nodo
    el next de la cabeza va para nuevo nodo
    finalmente digo que la nueva cabeza es el nuevo nodo
     */
    public void addPetToBeginning(Pet pet) {
        NodeDE newNode = new NodeDE(pet);
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                    throw new ListDEException("400", "Ya existe una mascota con ese codigo");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getIdentification().equals(pet.getIdentification())) {
                throw new ListDEException("400", "Ya existe una mascota con ese codigo");
            }
            newNode.setNext(this.head.getNext());
            newNode.setPrevious(this.head);
            this.head.getNext().setPrevious(newNode);
            this.head.setNext(newNode);
            this.head = newNode;
        }else {
            addPetToEnd(pet);
        }
        size++;
    }

}
