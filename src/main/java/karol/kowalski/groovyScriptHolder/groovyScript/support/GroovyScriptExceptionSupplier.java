package karol.kowalski.groovyScriptHolder.groovyScript.support;

import karol.kowalski.groovyScriptHolder.groovyScript.support.Exception.GroovyScriptNotExecutableException;
import karol.kowalski.groovyScriptHolder.groovyScript.support.Exception.GroovyScriptNotFoundException;

import java.util.function.Supplier;

/**
 * Class returning suppliers for proper Exception handling.
 */
public class GroovyScriptExceptionSupplier {

    public static Supplier<GroovyScriptNotFoundException> groovyScriptNotFound(Long id) {
        return () -> new GroovyScriptNotFoundException(id);
    }

    public static Supplier<GroovyScriptNotExecutableException> groovyScriptNotExecutable(Long id) {
        return () -> new GroovyScriptNotExecutableException(id);
    }
}
