package karol.kowalski.groovyScriptHolder.groovyScript.support;

import karol.kowalski.groovyScriptHolder.groovyScript.api.request.GroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.UpdateGroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptWithAnswerResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.domain.GroovyScript;
import org.springframework.stereotype.Component;

/**
 * Mapper used for conversion between GroovyScript-Responses, Requests and GroovyScripts.
 */
@Component
public class GroovyScriptMapper {

    public GroovyScriptMapper() {
    }

    public GroovyScript toGroovyScript(GroovyScriptRequest groovyScriptRequest) {
        return new GroovyScript(groovyScriptRequest.getScriptName(),
                groovyScriptRequest.getScriptText());
    }

    public GroovyScript toGroovyScript(GroovyScript groovyScript,
                                       UpdateGroovyScriptRequest groovyScriptRequest) {
        groovyScript.setScriptName(groovyScriptRequest.getScriptName());
        groovyScript.setScriptText(groovyScriptRequest.getScriptText());
        return groovyScript;
    }

    public GroovyScriptResponse toGroovyScriptResponse(GroovyScript groovyScript) {
        return new GroovyScriptResponse(groovyScript.getId(), groovyScript.getScriptName(),
                groovyScript.getScriptText());
    }

    public GroovyScriptWithAnswerResponse toGroovyScriptWithAnswerResponse(
            GroovyScript groovyScript, String result) {
        return new GroovyScriptWithAnswerResponse(
                groovyScript.getId(), groovyScript.getScriptName(),
                groovyScript.getScriptText(), result);
    }
}
