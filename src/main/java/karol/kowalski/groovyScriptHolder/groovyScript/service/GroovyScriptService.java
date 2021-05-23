package karol.kowalski.groovyScriptHolder.groovyScript.service;

import karol.kowalski.groovyScriptHolder.groovyScript.api.request.GroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.UpdateGroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.domain.GroovyScript;
import karol.kowalski.groovyScriptHolder.groovyScript.repository.GroovyScriptRepository;
import karol.kowalski.groovyScriptHolder.groovyScript.support.GroovyScriptExceptionSupplier;
import karol.kowalski.groovyScriptHolder.groovyScript.support.GroovyScriptMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroovyScriptService {

    private final GroovyScriptRepository groovyScriptRepository;
    private final GroovyScriptMapper groovyScriptMapper;

    public GroovyScriptService(GroovyScriptRepository groovyScriptRepository, GroovyScriptMapper groovyScriptMapper) {
        this.groovyScriptRepository = groovyScriptRepository;
        this.groovyScriptMapper = groovyScriptMapper;
    }

    public GroovyScriptResponse create(GroovyScriptRequest groovyScriptRequest) {
        GroovyScript groovyScript = groovyScriptRepository.save(groovyScriptMapper.toGroovyScript(groovyScriptRequest));
        return groovyScriptMapper.toGroovyScriptResponse(groovyScript);
    }

    public GroovyScriptResponse find(Long id) {
        GroovyScript groovyScript = groovyScriptRepository.findById(id).orElseThrow(GroovyScriptExceptionSupplier.groovyScriptNotFound(id));
        return groovyScriptMapper.toGroovyScriptResponse(groovyScript);
    }

    public GroovyScriptResponse update(UpdateGroovyScriptRequest updateGroovyScriptRequest) {
        GroovyScript groovyScript = groovyScriptRepository.findById(updateGroovyScriptRequest.getId()).orElseThrow(GroovyScriptExceptionSupplier.groovyScriptNotFound(updateGroovyScriptRequest.getId()));
        groovyScriptRepository.save(groovyScriptMapper.toGroovyScript(groovyScript, updateGroovyScriptRequest));
        return groovyScriptMapper.toGroovyScriptResponse(groovyScript);
    }

    public GroovyScriptResponse update(Long id, UpdateGroovyScriptRequest updateGroovyScriptRequest) {
        GroovyScript groovyScript = groovyScriptRepository.findById(id).orElseThrow(GroovyScriptExceptionSupplier.groovyScriptNotFound(id));
        groovyScriptRepository.save(groovyScriptMapper.toGroovyScript(groovyScript, updateGroovyScriptRequest));
        return groovyScriptMapper.toGroovyScriptResponse(groovyScript);
    }

    public List<GroovyScriptResponse> findAll() {
        return groovyScriptRepository.findAll().stream().map(groovyScriptMapper::toGroovyScriptResponse).collect(Collectors.toList());
    }

    public void delete(Long id) {
        GroovyScript groovyScript = groovyScriptRepository.findById(id).orElseThrow(GroovyScriptExceptionSupplier.groovyScriptNotFound(id));
        groovyScriptRepository.delete(groovyScript);
    }
}
