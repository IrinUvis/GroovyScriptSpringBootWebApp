package karol.kowalski.groovyScriptHolder.share.api.response;

/**
 * Class representing GroovyScriptResponse that is returned after unsuccessful request handling.
 */
public class ErrorMessageResponse {

    private final String message;

    public ErrorMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
