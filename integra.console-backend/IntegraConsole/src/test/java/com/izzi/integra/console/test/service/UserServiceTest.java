package com.izzi.integra.console.test.service;

import com.izzi.integra.console.dao.entity.CatProfile;
import com.izzi.integra.console.dao.entity.CatUser;
import com.izzi.integra.console.dao.repository.CatProfileRepository;
import com.izzi.integra.console.dao.repository.CatUserRepository;
import com.izzi.integra.console.service.CatUserService;
import com.izzi.integra.console.web.request.UserRestRequest;
import com.izzi.integra.console.web.response.UserRestResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Rafael on 20/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    CatUserRepository userRepositoryMock;
    @Mock
    CatProfileRepository profileRepositoryMock;
    @InjectMocks
    CatUserService catUserService;

    private UserRestRequest request1;
    private CatProfile profile1;
    private CatProfile profile2;
    private CatUser user1;

    @Before
    public void setup() {
        final String profileName1 = "ROLE_ADMIN";
        final String profileName2 = "ROLE_SUPPORT";
        this.request1 = new UserRestRequest("usertest1", 1L, 2L, profileName2, "Initial", true);
        final Date currentDate = new java.util.Date();
        this.profile1 = new CatProfile(profileName1, "Initial", currentDate);
        this.profile2 = new CatProfile(profileName2, "Initial", currentDate);
        this.user1 = new CatUser(request1.getUsername(), "Initial", currentDate, profile1);

    }

    @Test
    public void updatingCatUserTest() {
        when(userRepositoryMock.findByUsername(this.request1.getUsername())).thenReturn(user1);
        when(profileRepositoryMock.findByProfileName(this.request1.getProfileName())).thenReturn(profile2);
        when(userRepositoryMock.save(any(CatUser.class)))
                .thenAnswer(new Answer<CatUser>() {
                    @Override
                    public CatUser answer(InvocationOnMock invocation) throws Throwable {
                        final CatUser user = (CatUser) invocation.getArguments()[0];
                        user.setProfile(profile2);
                        return user;
                    }
                });

        final UserRestResponse userRestResponse = this.catUserService.updateUser(this.request1);
        logger.info(MessageFormat.format("userRestResponse: {0}", userRestResponse));
        assertThat(userRestResponse).isNotNull();
        assertThat(userRestResponse.getCatUser()).isNotNull();
        assertThat(userRestResponse.getCatUser().getProfile().getProfileName()).isEqualTo(this.request1.getProfileName());

    }

    private CatUser copyUser(final CatUser originalUser) {
        return new CatUser(originalUser.getUsername(), originalUser.getCreationUser(), originalUser.getCreationDate(), originalUser.getProfile());
    }

}
