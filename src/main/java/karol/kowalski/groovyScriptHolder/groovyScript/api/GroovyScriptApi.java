package karol.kowalski.groovyScriptHolder.groovyScript.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.GroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.UpdateGroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.domain.GroovyScript;
import karol.kowalski.groovyScriptHolder.groovyScript.service.GroovyScriptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "GroovyScripts")
@RequestMapping("/api/groovyScripts")
public class GroovyScriptApi {

    private final GroovyScriptService groovyScriptService;

    public GroovyScriptApi(GroovyScriptService groovyScriptService) {
        this.groovyScriptService = groovyScriptService;
    }

    @PostMapping
    @ApiOperation("Create Groovy Script")
    public ResponseEntity<GroovyScriptResponse> create(@RequestBody GroovyScriptRequest groovyScriptRequest) {
        GroovyScriptResponse groovyScriptResponse = groovyScriptService.create(groovyScriptRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(groovyScriptResponse);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find Groovy Script")
    public ResponseEntity<GroovyScriptResponse> find(@PathVariable Long id) {
        GroovyScriptResponse groovyScriptResponse = groovyScriptService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(groovyScriptResponse);
    }

    @PutMapping
    @ApiOperation("Update Groovy Script")
    public ResponseEntity<GroovyScriptResponse> update(@RequestBody UpdateGroovyScriptRequest updateGroovyScriptRequest) {
        GroovyScriptResponse groovyScriptResponse = groovyScriptService.update(updateGroovyScriptRequest);
        return ResponseEntity.status(HttpStatus.OK).body(groovyScriptResponse);
    }

//    @PutMapping("/{id}")
//    @ApiOperation("Update Groovy Script")
//    public ResponseEntity<GroovyScriptResponse> update(@PathVariable Long id, @RequestBody UpdateGroovyScriptRequest updateGroovyScriptRequest) {
//        GroovyScriptResponse groovyScriptResponse = groovyScriptService.update(id, updateGroovyScriptRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(groovyScriptResponse);
//    }

    @GetMapping
    @ApiOperation("Find all Groovy Scripts")
    public ResponseEntity<List<GroovyScriptResponse>> findAll() {
        List<GroovyScriptResponse> groovyScriptResponses = groovyScriptService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(groovyScriptResponses);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Groovy Script")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groovyScriptService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Pass arguments to Groovy Script with id.")
    public ResponseEntity<GroovyScriptResponse> addArguments(@PathVariable Long id, @RequestParam String[] args) {
        GroovyScriptResponse groovyScriptResponse = groovyScriptService.solveScript(id, args);
        return ResponseEntity.status(HttpStatus.OK).body(groovyScriptResponse);
    }
}
