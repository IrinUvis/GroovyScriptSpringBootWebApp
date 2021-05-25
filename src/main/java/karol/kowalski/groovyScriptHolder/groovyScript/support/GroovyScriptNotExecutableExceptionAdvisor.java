package karol.kowalski.groovyScriptHolder.groovyScript.support;

import karol.kowalski.groovyScriptHolder.groovyScript.support.Exception.GroovyScriptNotExecutableException;
import karol.kowalski.groovyScriptHolder.share.api.response.ErrorMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GroovyScriptNotExecutableExceptionAdvisor {

    private static final Logger LOG = LoggerFactory.getLogger(GroovyScriptNotExecutableExceptionAdvisor.class);

    @ExceptionHandler(GroovyScriptNotExecutableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageResponse groovyScriptNotExecutable(GroovyScriptNotExecutableException exeception) {
        LOG.error(exeception.getMessage(), exeception);
        return new ErrorMessageResponse(exeception.getMessage());
    }

}
