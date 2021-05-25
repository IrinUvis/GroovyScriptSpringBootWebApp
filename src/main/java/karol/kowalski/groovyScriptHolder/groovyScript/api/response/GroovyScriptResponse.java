package karol.kowalski.groovyScriptHolder.groovyScript.api.response;

public class GroovyScriptResponse {

    private final Long id;
    private final String scriptName;
    private final String scriptText;
    private final String scriptResult;

    public GroovyScriptResponse(Long id, String scriptName, String scriptText, String scriptResult) {
        this.id = id;
        this.scriptName = scriptName;
        this.scriptText = scriptText;
        this.scriptResult = scriptResult;
    }

    public Long getId() {
        return id;
    }

    public String getScriptName() {
        return scriptName;
    }

    public String getScriptText() {
        return scriptText;
    }

    public String getScriptResult() {
        return scriptResult;
    }
}
