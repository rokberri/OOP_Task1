package RangeSet;

public class Exceptions {

    public static class InvalidArgsException extends Exception{
        String message;
        public InvalidArgsException(String message){
            super(message);
        }
        public void catchEx(){
            System.out.println(message);
        }
    }
}
