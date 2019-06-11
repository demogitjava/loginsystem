package de.jsoft.Exception;

import de.jsoft.dto.UsersDTO;

public class CustomErrorType extends UsersDTO {

	private String errorMessage;
	 
    public CustomErrorType(final String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
}
