package karol.kowalski.groovyScriptHolder.groovyScript.support.Exception;

/**
 * Exception thrown when the script is not executable,
 * in its current state or with parameters that were given to it.
 */
public class GroovyScriptNotExecutableException extends RuntimeException {
    public GroovyScriptNotExecutableException(Long id) {
        super(String.format("Groovy script with id %d cannot be executed with passed parameters", id));
    }
}
