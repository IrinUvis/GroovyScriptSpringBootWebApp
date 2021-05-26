package karol.kowalski.groovyScriptHolder.groovyScript.api.response;

/**
 * Class representing GroovyScript object as a response.
 * It is returned after successful request handling.
 */
public class GroovyScriptResponse {

    private final Long id;
    private final String scriptName;
    private final String scriptText;

    public GroovyScriptResponse(Long id, String scriptName, String scriptText) {
        this.id = id;
        this.scriptName = scriptName;
        this.scriptText = scriptText;
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
}
