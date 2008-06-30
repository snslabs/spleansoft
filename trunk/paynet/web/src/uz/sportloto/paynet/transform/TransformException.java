package uz.sportloto.paynet.transform;

/**
 * Исключение выбрасываемое при ошибке трансформации ответа от пейнета
 */
public class TransformException extends Exception{
    public TransformException() {
        super();
    }

    public TransformException(String message) {
        super(message);
    }

    public TransformException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransformException(Throwable cause) {
        super(cause);
    }
}
