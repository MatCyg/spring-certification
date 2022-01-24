package io.cygert.webmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class AppSpringBootTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testShouldAllowUnauthenticated() throws Exception {
        mockMvc.perform(get("/test"))
               .andExpect(status().isOk())
               .andExpect(content().string("called /test"));
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    void testShouldAllowAdmin() throws Exception {
        mockMvc.perform(get("/test"))
               .andExpect(status().isOk())
               .andExpect(content().string("called /test"));
    }

    @Test
    void nameShouldNotAllowUnauthenticated() throws Exception {
        mockMvc.perform(get("/test/name"))
               .andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    void nameShouldAllowAdmin() throws Exception {
        mockMvc.perform(get("/test/name"))
               .andExpect(status().isOk())
                .andExpect(content().string("called /test/name"));
    }

    @Test
    void namesShouldNotAllowUnauthenticated() throws Exception {
        mockMvc.perform(get("/test/names"))
               .andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    void namesShouldAllowAdmin() throws Exception {
        mockMvc.perform(get("/test/names"))
               .andExpect(status().isOk())
               .andExpect(content().string("called /test/names"));
    }
}