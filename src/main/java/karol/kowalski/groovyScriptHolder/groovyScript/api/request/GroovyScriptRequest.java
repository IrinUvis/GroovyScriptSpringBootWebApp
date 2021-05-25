package karol.kowalski.groovyScriptHolder.groovyScript.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Class representing the most basic GroovyScriptRequest used e.g. in creation of Groovy Scripts.
 */
public class GroovyScriptRequest {

    private final String scriptName;
    private final String scriptText;

    @JsonCreator
    public GroovyScriptRequest(String scriptName, String scriptText) {
        this.scriptName = scriptName;
        this.scriptText = scriptText;
    }

    public String getScriptName() {
        return scriptName;
    }

    public String getScriptText() {
        return scriptText;
    }
}
