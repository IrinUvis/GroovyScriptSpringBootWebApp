package karol.kowalski.groovyScriptHolder.groovyScript.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Class inheriting from GroovyScriptRequest, containing script's id info as well.
 * Used in e.g. updating already existing scripts.
 */
public class UpdateGroovyScriptRequest extends GroovyScriptRequest {

    private final Long id;

    @JsonCreator
    public UpdateGroovyScriptRequest(String scriptName, String scriptText, Long id) {
        super(scriptName, scriptText);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
