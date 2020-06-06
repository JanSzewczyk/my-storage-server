package jrs.mystorage.auth;

import jrs.mystorage.MyStorageApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MyStorageApplication.class)
public class AuthTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Value("${security.jwt.client-id}")
    private String clientId;

    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String EMAIL = "jan.szewczyk1997@gmail.com";
    private static final String PASS = "zaq1@WSXcde3";

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    private String getAccessToken(String email, String password) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", email);
        params.add("password", password);

        ResultActions result = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic(clientId, clientSecret))
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void givenUserDetails_whenGetAuth_thenOK() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", EMAIL);
        params.add("password", PASS);

        mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic(clientId, clientSecret))
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE));
    }

    @Test
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isUnauthorized());
    }

    @Test
    public void givenInvalidRole_whenGetSecureRequest_thenForbidden() throws Exception {
        final String accessToken = getAccessToken("hans@gmail.com", "zaq1@WSXcde3");
        mockMvc.perform(get("/employees")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isForbidden());
    }
}
