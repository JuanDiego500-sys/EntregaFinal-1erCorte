package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDEException;
import com.google.gson.Gson;
import lombok.Data;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

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
        } else {
            addPetToEnd(pet);
        }
        size++;
    }
    /*
    metodo agregar en posicion
    se crea el ayudante y se crea un nuevo nodo donde va a ir la mascota
    si no hay datos entonces se llama a agregar al final para que haga la validacion.
    si la posición es igual a 0 entonces llamo a agregar al inicio que tiene esa validacion.
    en otro caso , inicio la posicion en 1 que es la cabeza.
    -------------------------------------------------------------------------------------------------------------
    ahora hay dos escenarios, para girar a la derecha se usan valores positivos y para la izquierda valores negativos.
    -------------------------------------------------------------------------------------------------------------
    para girar hacia la derecha se hace el ciclo para recorrer la lista hacia la posicion en la que se quiere agregar a
    la mascota (contando que cabeza cuando es el primer dato es la posicion 0).
    ahora el previo de newNode se va para el temporal , el next de newNode se va para el siguiente al temporal.
    el previo del siguiente a temporal va a apuntar a newNode y el next de temporal se va para newNode.
    ----------------------------------------------------------------------------------------------------------------
    si el valor es negativo y se va a girar hacia la izquierda: se transforma el valor en positivo para
    hacer el mismo ciclo de for pero diciendole al ayudante que se vaya para el anterior.
    cuando termine el ciclo entonces.
    el previo de newNode se va para el anterior del temp.
    el next del newNode se va para el temp.
    el previous de el temp se va para el newNode
    el next del anterior del temp se va para el newNode
    con esto ya finaliza el metodo.
     */
    public void addInPos(Pet pet, int pos2) {
        NodeDE temp = head;
        NodeDE newNode = new NodeDE(pet);
        if (this.head == null) {
            addPetToEnd(pet);
            return;
        }
        if (pos2 == 0) {
            addPetToBeginning(pet);
        }if (pos2>0) {
            for (int i = 1; i < pos2; i++) {
                temp = temp.getNext();
            }
            newNode.setPrevious(temp);
            newNode.setNext(temp.getNext());
            temp.getNext().setPrevious(newNode);
            temp.setNext(newNode);
        }else if(pos2<0){
            int pos = pos2 *(-1);
            for (int i = 1; i < pos2;i++){
                temp = temp.getPrevious();
            }
            newNode.setPrevious(temp.getPrevious());
            newNode.setNext(temp);
            temp.setPrevious(newNode);
            temp.getPrevious().setNext(newNode);
        }
        size++;

    }
    /*
    method to show the listDE
    voy a retornar la lista en tipo json para asi mostrar los datos estructurados.
    creo una lista para guardar la data de cada mascota
    creo un ayudante y lo posiciono en la cabeza.
    recorro la lista agregando la data de los objetos a la lista mientras que el ayudante no este parado en la cabeza
    que es el ultimo dato.
    uso la libreria Gson para transformar de lista a json todos los datos obtenidos.
    creo un objeto de tipo jsonObject para retornarlo de esa manera con ayuda de varias funciones.
     */
    public JsonObject showList() {
        List<Object> data = new ArrayList<>();
        NodeDE temp = this.head;
        while (temp != this.head) {
            data.add(temp.getData());
            temp = temp.getNext();
        }

        Gson gson = new Gson();
        String json = gson.toJson(data);
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("data", json)
                .build();
        return jsonObject;
    }


}
