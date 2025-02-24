package ctv.core_service.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ctv.core_service.dto.request.UserCreationRequest;
import ctv.core_service.dto.response.UserResponse;
import ctv.core_service.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserCreationRequest request;
    private UserResponse userResponse;

    @BeforeEach
    void initData() {
        request = UserCreationRequest.builder()
                .userName("testUser123")
                .lastName("Doe")
                .firstName("John")
                .password("Test123")
                .email("john.doe@example.com")
                .dateCreated(LocalDateTime.now())
                .createdBy("admin")
                .role("USER")
                .build();
        userResponse = UserResponse.builder()
                .id(1L)
                .userName("testUser123")
                .lastName("Doe")
                .firstName("John")
                .email("john.doe@example.com")
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .lastModifiedBy("admin")
                .createdBy("admin")
                .role("USER")
                .build();
    }

    @Test

    void createUser_validRequest_success() throws Exception {
        // Given: du lieu dau vao, da biet truoc
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);
        // when: khi request api
        // then: khi request xay ra thi
        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("data.id").value(1));
    }

    @Test
    void createUser_usernameInvalid_fail() throws Exception {
        // GIVEN
        request.setUserName("joh");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);

        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.userName").value("Username must be at least 5 characters"));
    }
}
