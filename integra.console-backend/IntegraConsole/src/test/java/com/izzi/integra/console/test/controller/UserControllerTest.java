package com.izzi.integra.console.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izzi.integra.console.dao.entity.CatProfile;
import com.izzi.integra.console.dao.entity.CatUser;
import com.izzi.integra.console.service.CatUserService;
import com.izzi.integra.console.service.UserMenuService;
import com.izzi.integra.console.web.controller.UserRestController;
import com.izzi.integra.console.web.response.UserRestResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.MessageFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Rafael on 21/12/2016.
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(UserRestController.class)
public class UserControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @InjectMocks
    UserRestController userRestController;

    private MockMvc mockMvc;

    @Mock
    private CatUserService userServiceMock;

    @Mock
    private UserMenuService userMenuServiceMock;

    private CatUser userResult1;
    private String userRequest1;
    private UserRestResponse userRestResponseOk;
    private UserRestResponse userRestResponseNotFound;

    @Before
    public void setup(){
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();

        userRequest1 = "usertest1";
        final String profileName1 = "ROLE_ADMIN";
        final Date currentDate = new java.util.Date();
        final CatProfile profile1 = new CatProfile(profileName1, "Initial", currentDate);
        userResult1 = new CatUser(userRequest1, "Initial", currentDate, profile1);
        userRestResponseOk =  new UserRestResponse(true, "success", userResult1);

        userRestResponseNotFound = new UserRestResponse(false, "user not found!!", null);
    }

    @Test
    public void getUserOk() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonResult = objectMapper.writeValueAsString(userRestResponseOk);

        given(userServiceMock.loadUserByUsername(userRequest1)).willReturn(userRestResponseOk);
        final ResultActions resultActions =  this.mockMvc.perform(get("/getUser").param("username", userRequest1).accept(MediaType.APPLICATION_JSON));
        logger.info(MessageFormat.format("result of /getUser test ok: {0}", resultActions.andReturn().getResponse().getContentAsString()));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().json(jsonResult));

    }

    @Test
    public void getUserFail()throws Exception{
        given(userServiceMock.loadUserByUsername(userRequest1)).willReturn(userRestResponseNotFound);

        final ResultActions resultActions =  this.mockMvc.perform(get("/getUser").param("username", userRequest1).accept(MediaType.APPLICATION_JSON));
        logger.info(MessageFormat.format("result of /getUser test fail: {0}", resultActions.andReturn().getResponse().getContentAsString()));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.success", is(false)));
    }
}
