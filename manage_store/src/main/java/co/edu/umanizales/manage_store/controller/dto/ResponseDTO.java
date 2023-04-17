package co.edu.umanizales.manage_store.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

//you could use this response dto and the error dto in all the classes that you want
@Data
@AllArgsConstructor
public class ResponseDTO {//beggining of response dto
    //variables that we will use to give a response , it includes data and errors------------------------
    private int code;
    private Object data;
    private List<ErrorDTO> errors;
}//end of response dto
