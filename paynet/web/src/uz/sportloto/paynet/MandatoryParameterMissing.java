package uz.sportloto.paynet;

/**
 * Исключение выбрасываемое если отсутсвует обязательный параметр
 */
public class MandatoryParameterMissing extends Exception {
    public MandatoryParameterMissing() {
        super();
    }

    public MandatoryParameterMissing(String message) {
        super(message);
    }

    public MandatoryParameterMissing(String message, Throwable cause) {
        super(message, cause);
    }

    public MandatoryParameterMissing(Throwable cause) {
        super(cause);
    }
}
