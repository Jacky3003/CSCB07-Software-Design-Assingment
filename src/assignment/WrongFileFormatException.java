package assignment;
import java.lang.Exception;

public class WrongFileFormatException extends Exception{
	String msg;
	public WrongFileFormatException(String message) {
		msg = message;
	}
}
