package karol.kowalski.groovyScriptHolder.groovyScript.api.response;

/**
 * Class representing GroovyScript object and the result of script's execution as a response.
 * It is returned after successful request handling.
 */
public class GroovyScriptWithAnswerResponse extends GroovyScriptResponse {

    private final String scriptResult;

    public GroovyScriptWithAnswerResponse(Long id, String scriptName,
                                          String scriptText, String scriptResult) {
        super(id, scriptName, scriptText);
        this.scriptResult = scriptResult;
    }

    public String getScriptResult() {
        return scriptResult;
    }
}
