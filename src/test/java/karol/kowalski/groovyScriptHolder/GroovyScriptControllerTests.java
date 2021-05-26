package karol.kowalski.groovyScriptHolder;


import com.fasterxml.jackson.databind.ObjectMapper;
import karol.kowalski.groovyScriptHolder.groovyScript.api.GroovyScriptApi;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.GroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.request.UpdateGroovyScriptRequest;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.api.response.GroovyScriptWithAnswerResponse;
import karol.kowalski.groovyScriptHolder.groovyScript.service.GroovyScriptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GroovyScriptControllerTests {

    @Autowired
    private GroovyScriptApi controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroovyScriptService service;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateGroovyScript() throws Exception {

        GroovyScriptResponse response = new GroovyScriptResponse(
                1L,
                "some name",
                "some text");

        GroovyScriptRequest request = new GroovyScriptRequest(
                "some name",
                "some text");

        when(service.create(any(GroovyScriptRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/groovyScripts")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scriptName").value("some name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scriptText").value("some text"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetGroovyScriptById() throws Exception {

        GroovyScriptResponse response = new GroovyScriptResponse(
                1L,
                "some name",
                "some text");

        when(service.find(anyLong())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/groovyScripts/12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scriptName").value("some name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scriptText").value("some text"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateGroovyScript() throws Exception {

        GroovyScriptResponse response = new GroovyScriptResponse(
                1L,
                "some name",
                "some text");

        UpdateGroovyScriptRequest request = new UpdateGroovyScriptRequest(
                "some name",
                "some text",
                1L);

        when(service.update(any(UpdateGroovyScriptRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/groovyScripts")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scriptName").value("some name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scriptText").value("some text"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllGroovyScripts() throws Exception {

        GroovyScriptResponse response1 = new GroovyScriptResponse(
                1L,
                "some name 1",
                "some text 1");
        GroovyScriptResponse response2 = new GroovyScriptResponse(
                2L,
                "some name 2",
                "some text 2");

        when(service.findAll()).thenReturn(Arrays.asList(response1, response2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/groovyScripts"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].scriptName").value("some name 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].scriptText").value("some text 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].scriptName").value("some name 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].scriptText").value("some text 2"));
    }

    @Test
    void testDeleteGroovyScriptWithId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/groovyScripts/12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testAddArgumentsToGroovyScriptWithId() throws Exception {

        GroovyScriptWithAnswerResponse response = new GroovyScriptWithAnswerResponse(
                1L,
                "some name",
                "some text",
                "some result");

        when(service.solveScript(anyLong(), any(String[].class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/groovyScripts/12")
                .param("args", "some arg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scriptName").value("some name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scriptText").value("some text"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scriptResult").value("some result"))
                .andExpect(status().isOk());
    }
}
