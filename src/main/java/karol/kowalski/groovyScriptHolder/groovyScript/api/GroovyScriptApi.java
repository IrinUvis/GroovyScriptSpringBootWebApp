package karol.kowalski.groovyScriptHolder.groovyScript.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.GroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.UpdateGroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptWithAnswerResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.service.GroovyScriptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller providing all functionalities related to Groovy Scripts.
 */
@RestController
@Api(tags = "GroovyScripts")
@RequestMapping("/api/groovyScripts")
public class GroovyScriptApi {

    private final GroovyScriptService groovyScriptService;

    public GroovyScriptApi(GroovyScriptService groovyScriptService) {
        this.groovyScriptService = groovyScriptService;
    }

    @PostMapping
    @ApiOperation("Create Groovy Script and put in the database")
    public ResponseEntity<GroovyScriptResponse> create(@RequestBody GroovyScriptRequest groovyScriptRequest) {
        GroovyScriptResponse groovyScriptResponse = groovyScriptService.create(groovyScriptRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(groovyScriptResponse);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find Groovy Script with specified id")
    public ResponseEntity<GroovyScriptResponse> find(@PathVariable Long id) {
        GroovyScriptResponse groovyScriptResponse = groovyScriptService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(groovyScriptResponse);
    }

    @PutMapping
    @ApiOperation("Update Groovy Script and store the updated one in the database")
    public ResponseEntity<GroovyScriptResponse> update(@RequestBody UpdateGroovyScriptRequest updateGroovyScriptRequest) {
        GroovyScriptResponse groovyScriptResponse = groovyScriptService.update(updateGroovyScriptRequest);
        return ResponseEntity.status(HttpStatus.OK).body(groovyScriptResponse);
    }

    @GetMapping
    @ApiOperation("Find all Groovy Scripts in the database")
    public ResponseEntity<List<GroovyScriptResponse>> findAll() {
        List<GroovyScriptResponse> groovyScriptResponses = groovyScriptService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(groovyScriptResponses);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Groovy Script with specified id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groovyScriptService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Pass arguments to Groovy Script with id and get answer as a response")
    public ResponseEntity<GroovyScriptWithAnswerResponse> addArguments(@PathVariable Long id, @RequestParam String[] args) {
        GroovyScriptWithAnswerResponse groovyScriptWithAnswerResponse = groovyScriptService.solveScript(id, args);
        return ResponseEntity.status(HttpStatus.OK).body(groovyScriptWithAnswerResponse);
    }
}
