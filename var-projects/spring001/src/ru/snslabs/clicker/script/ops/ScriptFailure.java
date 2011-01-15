package ru.snslabs.clicker.script.ops;

public class ScriptFailure extends RuntimeException {
    public ScriptFailure() {
        super();
    }

    public ScriptFailure(String message) {
        super(message);
    }

    public ScriptFailure(String message, Throwable cause) {
        super(message, cause);
    }

    public ScriptFailure(Throwable cause) {
        super(cause);
    }
}
