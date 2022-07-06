package com.cadykd.kdgames.validation;

// This class stops duplication of characters
public class CharacterAlreadyExistException extends Exception{
	
	private String code = "DuplicateCharacterNotAllowed";
	
	public CharacterAlreadyExistException(String message) {
		super(message);
	}
	
	public CharacterAlreadyExistException(String code, String message) {
		super(message);
		this.setCode(code);
	}
	
	public CharacterAlreadyExistException(String code, String message, Throwable cause) {
		super(message, cause);
		this.setCode(code);
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
