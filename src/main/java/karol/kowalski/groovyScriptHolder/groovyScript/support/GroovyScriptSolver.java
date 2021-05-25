package karol.kowalski.groovyScriptHolder.groovyScript.support;

import karol.kowalski.groovyScriptHolder.groovyScript.domain.GroovyScript;
import org.springframework.scripting.ScriptEvaluator;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GroovyScriptSolver {

    public GroovyScriptSolver() {
    }

    public String solveScript(GroovyScript script, String[] args) {

        StaticScriptSource scriptSource = new StaticScriptSource(script.getScriptText());
        ScriptEvaluator evaluator = new GroovyScriptEvaluator();
        String result;
        try{
            result = (String) evaluator.evaluate(scriptSource, argsToMap(args));
        } catch (Exception exception) {
            throw GroovyScriptExceptionSupplier.groovyScriptNotExecutable(script.getId()).get();
        }
        return result;
    }

    private Map<String, Object> argsToMap(String[] args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            map.put("arg" + i, args[i]);
        }
        return map;
    }
}
