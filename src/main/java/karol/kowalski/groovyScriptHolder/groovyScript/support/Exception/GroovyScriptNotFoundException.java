package karol.kowalski.groovyScriptHolder.groovyScript.support.Exception;

/**
 * Exception thrown when the script with given id cannot be found in the database.
 */
public class GroovyScriptNotFoundException extends RuntimeException {
    public GroovyScriptNotFoundException(Long id) {
        super(String.format("Groovy script with id %d not found", id));
    }
}
