package karol.kowalski.groovyScriptHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.GroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.UpdateGroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptWithAnswerResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.service.GroovyScriptService;
import karol.kowalski.groovyScriptHolder.groovyScript.support.Exception.GroovyScriptNotExecutableException;
import karol.kowalski.groovyScriptHolder.groovyScript.support.Exception.GroovyScriptNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GroovyScriptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroovyScriptService service;

    @Test
    @DisplayName("POST api/groovyScripts - success")
    void testCreateGroovyScriptSuccess() throws Exception {

        GroovyScriptRequest request = new GroovyScriptRequest(
                "some name",
                "some text"
        );

        GroovyScriptResponse response = new GroovyScriptResponse(
                1L,
                "some name",
                "some text");

        doReturn(response).when(service).create(any(GroovyScriptRequest.class));

        // Execute the POST request
        mockMvc.perform(post("/api/groovyScripts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.scriptName", is("some name")))
                .andExpect(jsonPath("$.scriptText", is("some text")));
    }

    @Test
    @DisplayName("GET api/groovyScripts/{id} - success")
    void testGetGroovyScriptByIdSuccess() throws Exception {

        GroovyScriptResponse response = new GroovyScriptResponse(
                1L,
                "some name",
                "some text");

        doReturn(response).when(service).find(anyLong());

        mockMvc.perform(get("/api/groovyScripts/{id}", 1L))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.scriptName", is("some name")))
                .andExpect(jsonPath("$.scriptText", is("some text")));
    }

    @Test
    @DisplayName("GET api/groovyScripts/{id} - notFound")
    void testGetGroovyScriptByIdNotFound() throws Exception {

        doThrow(new GroovyScriptNotFoundException(2L)).
                when(service).find(anyLong());

        mockMvc.perform(get("/api/groovyScripts/{id}", 2L))
                // Validate the response code and content type
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.message",
                        is("Groovy script with id 2 not found")));
    }

    @Test
    @DisplayName("GET api/groovyScripts - filled")
    void testGetAllGroovyScriptsFilled() throws Exception {

        GroovyScriptResponse response1 = new GroovyScriptResponse(
                1L,
                "some name 1",
                "some text 1");
        GroovyScriptResponse response2 = new GroovyScriptResponse(
                2L,
                "some name 2",
                "some text 2");

        doReturn(Arrays.asList(response1, response2)).
                when(service).findAll();

        mockMvc.perform(get("/api/groovyScripts"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].scriptName", is("some name 1")))
                .andExpect(jsonPath("$[0].scriptText", is("some text 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].scriptName", is("some name 2")))
                .andExpect(jsonPath("$[1].scriptText", is("some text 2")));
    }

    @Test
    @DisplayName("GET api/groovyScripts - empty")
    void testGetAllGroovyScriptsEmpty() throws Exception {

        List<GroovyScriptResponse> emptyResponses = new ArrayList<>();

        doReturn(emptyResponses).when(service).findAll();

        mockMvc.perform(get("/api/groovyScripts"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("PUT api/groovyScripts - success")
    void testUpdateGroovyScriptSuccess() throws Exception {

        GroovyScriptResponse response = new GroovyScriptResponse(
                1L,
                "new name",
                "new text");

        UpdateGroovyScriptRequest request = new UpdateGroovyScriptRequest(
                "new name",
                "new text",
                1L);

        doReturn(response).when(service).update(any(UpdateGroovyScriptRequest.class));

        mockMvc.perform(put("/api/groovyScripts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.scriptName", is("new name")))
                .andExpect(jsonPath("$.scriptText", is("new text")));
    }

    @Test
    @DisplayName("PUT api/groovyScripts - notFound")
    void testUpdateGroovyScriptNotFound() throws Exception {

        UpdateGroovyScriptRequest request = new UpdateGroovyScriptRequest(
                "new name",
                "new text",
                2L);

        doThrow(new GroovyScriptNotFoundException(2L))
                .when(service).update(any(UpdateGroovyScriptRequest.class));

        mockMvc.perform(put("/api/groovyScripts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))

                // Validate the response code and content type
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.message",
                        is("Groovy script with id 2 not found")));
    }

    @Test
    @DisplayName("DELETE api/groovyScripts/{id} - success")
    void testDeleteGroovyScriptWithIdSuccess() throws Exception {

        mockMvc.perform(delete("/api/groovyScripts/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE api/groovyScripts/{id} - notFound")
    void testDeleteGroovyScriptWithIdNotFound() throws Exception {

        doThrow(new GroovyScriptNotFoundException(2L)).
                when(service).delete(anyLong());

        mockMvc.perform(delete("/api/groovyScripts/{id}", 2L))
                // Validate the response code and content type
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.message",
                        is("Groovy script with id 2 not found")));
    }

    @Test
    @DisplayName("PUT api/groovyScripts/{id} - success")
    void testAddArgumentsToGroovyScriptWithIdSuccess() throws Exception {

        GroovyScriptWithAnswerResponse response = new GroovyScriptWithAnswerResponse(
                1L,
                "some name",
                "some text",
                "some result");

        doReturn(response).when(service).solveScript(anyLong(), any(String[].class));

        mockMvc.perform(put("/api/groovyScripts/{id}", 1)
                .param("args", "some arg"))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.scriptName", is("some name")))
                .andExpect(jsonPath("$.scriptText", is("some text")))
                .andExpect(jsonPath("$.scriptResult", is("some result")));
    }

    @Test
    @DisplayName("PUT api/groovyScripts/{id} - notFound")
    void testAddArgumentsToGroovyScriptWithIdNotFound() throws Exception {

        doThrow(new GroovyScriptNotFoundException(2L)).
                when(service).solveScript(anyLong(), any(String[].class));

        mockMvc.perform(put("/api/groovyScripts/{id}", 2)
                .param("args", "some arg"))

                // Validate the response code and content type
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.message",
                        is("Groovy script with id 2 not found")));
    }

    @Test
    @DisplayName("PUT api/groovyScripts/{id} - notExecutable")
    void testAddArgumentsToGroovyScriptWithIdNotExecutable() throws Exception {

        doThrow(new GroovyScriptNotExecutableException(2L))
                .when(service).solveScript(anyLong(), any(String[].class));

        mockMvc.perform(put("/api/groovyScripts/{id}", 2)
                .param("args", "some arg"))

                // Validate the response code and content type
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.message",
                        is("Groovy script with id 2 cannot be executed with passed parameters")));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
