package karol.kowalski.groovyScriptHolder.groovyScript.support.Exception;

public class GroovyScriptNotFoundException extends RuntimeException {
    public GroovyScriptNotFoundException(Long id) {
        super(String.format("Groovy script with id %d not found", id));
    }
}
