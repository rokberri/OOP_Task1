package RangeSet;


public class InvalidArgsException extends Exception {
    private String message;

    public InvalidArgsException(String message) {
        super(message);
    }
    public void catchEX(){
        System.out.println(message);
    }

}

