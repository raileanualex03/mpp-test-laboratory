package holiday.exceptions;

public class StringNotConvertibleToDateException extends Exception{
    String errorMessage;

    public StringNotConvertibleToDateException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
