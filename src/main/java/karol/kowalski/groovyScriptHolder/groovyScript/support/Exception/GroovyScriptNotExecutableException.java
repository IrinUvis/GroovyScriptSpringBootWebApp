package karol.kowalski.groovyScriptHolder.groovyScript.support.Exception;

public class GroovyScriptNotExecutableException extends RuntimeException {
    public GroovyScriptNotExecutableException(Long id) {
        super(String.format("Groovy script with id %d cannot be executed with passed parameters", id));
    }
}
