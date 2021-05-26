package karol.kowalski.groovyScriptHolder.groovyScript.api.response;

public class GroovyScriptWithAnswerResponse extends GroovyScriptResponse {

    private final String scriptResult;

    public GroovyScriptWithAnswerResponse(Long id, String scriptName, String scriptText, String scriptResult) {
        super(id, scriptName, scriptText);
        this.scriptResult = scriptResult;
    }

    public String getScriptResult() {
        return scriptResult;
    }
}
