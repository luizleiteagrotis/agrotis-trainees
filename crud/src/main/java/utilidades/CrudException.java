package utilidades;

public class CrudException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CrudException(String message) {
        super(message);
    }
}
