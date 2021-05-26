package karol.kowalski.groovyScriptHolder.groovyScript.service;

import karol.kowalski.groovyScriptHolder.groovyScript.api.request.GroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.UpdateGroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptWithAnswerResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.domain.GroovyScript;
import karol.kowalski.groovyScriptHolder.groovyScript.repository.GroovyScriptRepository;
import karol.kowalski.groovyScriptHolder.groovyScript.support.GroovyScriptExceptionSupplier;
import karol.kowalski.groovyScriptHolder.groovyScript.support.GroovyScriptMapper;
import karol.kowalski.groovyScriptHolder.groovyScript.support.GroovyScriptSolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Services called by GroovyScriptController.
 */
@Component
public class GroovyScriptService {

    private final GroovyScriptRepository groovyScriptRepository;
    private final GroovyScriptMapper groovyScriptMapper;
    private final GroovyScriptSolver groovyScriptSolver;

    public GroovyScriptService(GroovyScriptRepository groovyScriptRepository,
                               GroovyScriptMapper groovyScriptMapper,
                               GroovyScriptSolver groovyScriptSolver) {
        this.groovyScriptRepository = groovyScriptRepository;
        this.groovyScriptMapper = groovyScriptMapper;
        this.groovyScriptSolver = groovyScriptSolver;
    }

    public GroovyScriptResponse create(GroovyScriptRequest groovyScriptRequest) {
        GroovyScript groovyScript = groovyScriptRepository.save(
                groovyScriptMapper.toGroovyScript(groovyScriptRequest));
        return groovyScriptMapper.toGroovyScriptResponse(groovyScript);
    }

    public GroovyScriptResponse find(Long id) {
        GroovyScript groovyScript = groovyScriptRepository.findById(id)
                .orElseThrow(GroovyScriptExceptionSupplier.groovyScriptNotFound(id));
        return groovyScriptMapper.toGroovyScriptResponse(groovyScript);
    }

    public GroovyScriptResponse update(UpdateGroovyScriptRequest updateGroovyScriptRequest) {
        GroovyScript groovyScript = groovyScriptRepository.findById(
                updateGroovyScriptRequest.getId())
                .orElseThrow(GroovyScriptExceptionSupplier.groovyScriptNotFound(
                        updateGroovyScriptRequest.getId()));
        groovyScriptRepository.save(groovyScriptMapper.toGroovyScript(
                groovyScript, updateGroovyScriptRequest));
        return groovyScriptMapper.toGroovyScriptResponse(groovyScript);
    }

    public List<GroovyScriptResponse> findAll() {
        List<GroovyScriptResponse> responses = new ArrayList<>();
        List<GroovyScript> scripts = groovyScriptRepository.findAll();
        for (GroovyScript script : scripts) {
            responses.add(groovyScriptMapper.toGroovyScriptResponse(script));
        }
        return responses;
    }

    public void delete(Long id) {
        GroovyScript groovyScript = groovyScriptRepository.findById(id)
                .orElseThrow(GroovyScriptExceptionSupplier.groovyScriptNotFound(id));
        groovyScriptRepository.delete(groovyScript);
    }

    public GroovyScriptWithAnswerResponse solveScript(Long id, String[] args) {
        GroovyScript groovyScript = groovyScriptRepository.findById(id)
                .orElseThrow(GroovyScriptExceptionSupplier.groovyScriptNotFound(id));
        String result = groovyScriptSolver.solveScript(groovyScript, args);
        return groovyScriptMapper.toGroovyScriptWithAnswerResponse(groovyScript, result);
    }
}
