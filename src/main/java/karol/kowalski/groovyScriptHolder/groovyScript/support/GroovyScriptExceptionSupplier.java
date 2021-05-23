package karol.kowalski.groovyScriptHolder.groovyScript.support;

import karol.kowalski.groovyScriptHolder.groovyScript.support.Exception.GroovyScriptNotFoundException;

import java.util.function.Supplier;

public class GroovyScriptExceptionSupplier {

    public static Supplier<GroovyScriptNotFoundException> groovyScriptNotFound(Long id) {
        return () -> new GroovyScriptNotFoundException(id);
    }
}
