package uz.sportloto.paynet.comm;

public class PaynetCommException extends Exception {
    public PaynetCommException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public PaynetCommException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public PaynetCommException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public PaynetCommException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
