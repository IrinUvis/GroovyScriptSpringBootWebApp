package karol.kowalski.groovyScriptHolder.groovyScript.support;

import karol.kowalski.groovyScriptHolder.groovyScript.domain.GroovyScript;
import org.springframework.scripting.ScriptEvaluator;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class GroovyScriptSolver {

    public GroovyScriptSolver() {
    }

    public String solveScript(GroovyScript script, String[] args) {

        StaticScriptSource scriptSource = new StaticScriptSource(script.getScriptText());
        ScriptEvaluator evaluator = new GroovyScriptEvaluator();
        String result = (String) evaluator.evaluate(scriptSource, argsToMap(args));
        
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
