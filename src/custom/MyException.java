package custom;

/**
 * La clase MyException, al heredar directamente de Exception
 * es una excepción de tipo COMPROBADA por el compilador
 */
public class MyException extends Exception {

    // Crea una excepción con el mensaje null
    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }
}
