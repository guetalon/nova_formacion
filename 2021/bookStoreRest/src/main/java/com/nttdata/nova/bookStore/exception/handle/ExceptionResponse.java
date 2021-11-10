package com.nttdata.nova.bookStore.exception.handle;

public class ExceptionResponse {
	
	private EnumBusinessCodeError errorCode;
	private String message;
	
	
	public ExceptionResponse(EnumBusinessCodeError errorCodeError) {
		this.errorCode = errorCodeError;
		
		switch (errorCodeError) {
		case BUSI_ID:
			this.message = "Invalid id";
			break;

		case BUSI_DATE:
			this.message = "Invalid date";
			break;
			
		case BUSI_EDITORIAL:
			this.message = "Invalid editorial";
			break;

		default:
			break;
		}
	}


	public EnumBusinessCodeError getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(EnumBusinessCodeError errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
