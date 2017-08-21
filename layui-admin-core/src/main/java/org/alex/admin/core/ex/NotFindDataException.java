package org.alex.admin.core.ex;

/**
 * 未查到数据异常
 * Created by Gaojun.Zhou 2017年6月8日
 */
public class NotFindDataException extends RuntimeException{

	private int code = 604 ;
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFindDataException() {
		super("Not find data");
		// TODO Auto-generated constructor stub
	}

	public NotFindDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotFindDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotFindDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotFindDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
}
