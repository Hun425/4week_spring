package com.firstweek.security.filter;

import com.firstweek.security.domain.CustomUser;
import com.firstweek.security.domain.User;
import com.firstweek.security.jwt.JwtUtil;
import com.firstweek.security.jwt.TokenPair;
import com.firstweek.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class RefreshTokenFilterTest {

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserService userService;

    private RefreshTokenFilter refreshTokenFilter;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.refreshTokenFilter = new RefreshTokenFilter(jwtUtil, userService);
        this.mockMvc = MockMvcBuilders.standaloneSetup()
                .addFilters(refreshTokenFilter)
                .build();
    }

    @Test
    void testDoFilterInternal_ValidRefreshToken() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        // Mocking JwtUtil
        String validRefreshToken = "validRefreshToken";
        when(jwtUtil.validateToken(validRefreshToken)).thenReturn(true);
        when(jwtUtil.getUsernameFromToken(validRefreshToken)).thenReturn("username");

        TokenPair newTokens = new TokenPair("newAccessToken", "newRefreshToken");
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setId(1);
        CustomUser customUser = new CustomUser(user);
        when(jwtUtil.generateToken(customUser)).thenReturn(newTokens);

        // Set the refresh token header
        request.addHeader("refreshToken", validRefreshToken);

        // Perform the filter
        refreshTokenFilter.doFilter(request, response, chain);

        // Verify that new access token was set in response or further assertions
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        // Add further assertions based on expected behavior
    }

    @Test
    void testDoFilterInternal_InvalidRefreshToken() throws Exception {
        // Given
        String invalidRefreshToken = "invalidRefreshToken";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("refreshToken", invalidRefreshToken);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        // Mocking JwtUtil
        when(jwtUtil.validateToken(invalidRefreshToken)).thenReturn(false);

        // When
        refreshTokenFilter.doFilterInternal(request, response, chain);

        // Then
        // Verify that the filter chain continues
        verify(chain).doFilter(request, response);

        // Additional assertions as needed
        // Example: Check response status code, headers, etc.
        assertEquals(401, response.getStatus()); // Assuming 401 Unauthorized status for invalid token
    }
}
