package io.cygert.webmvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SecurityApplicationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .apply(SecurityMockMvcConfigurers.springSecurity())
                                 .build();
    }

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