package com.manasconsults.word_generator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This is a test class for GeneratorController.
 * This tests get and  post methods on web page
 */

@SpringBootTest
@AutoConfigureMockMvc
class GeneratorControllerTest {

    @Autowired
    private GeneratorController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Enter a Number")));
    }

    @Test
    @WithMockUser
    void testConvertNumberToWords() throws Exception {
        mockMvc.perform(post("/convert")
                        .param("number", "123.04").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("words"))
                .andExpect(model().attribute("words", "ONE HUNDRED AND TWENTY THREE DOLLARS AND FOUR CENTS"))
                .andExpect(view().name("base"));
    }

}