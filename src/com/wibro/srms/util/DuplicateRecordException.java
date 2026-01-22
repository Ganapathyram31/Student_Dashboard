package com.wibro.srms.util;

public class DuplicateRecordException extends Exception{

	public String toString() {
		return "DuplicateRecordException" + getMessage();
	}


}
