package com.izzi.integra.console.test.repository;

import com.izzi.integra.console.dao.entity.CatProfile;
import com.izzi.integra.console.dao.entity.CatUser;
import com.izzi.integra.console.dao.repository.CatProfileRepository;
import com.izzi.integra.console.dao.repository.CatUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Rafael on 19/12/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CatUserRepository userRepository;

    @Autowired
    private CatProfileRepository profileRepository;

    private CatUser catUser1;

    @Before
    public void setup() {
        final CatProfile catProfile1 = new CatProfile("ROLE_ADMIN", "TEST", new java.util.Date());
        final CatProfile catProfile2 = new CatProfile("ROLE_SUPPORT", "TEST", new java.util.Date());
        this.entityManager.persist(catProfile1);
        this.entityManager.persist(catProfile2);

        catUser1 = new CatUser("user1", "initial data", new java.util.Date(), catProfile1);
        this.entityManager.persist(catUser1);
    }

    @Test
    public void updateUserProfileTest() {
        final String profileName = "ROLE_SUPPORT";
        final CatProfile catProfile = profileRepository.findByProfileName(profileName);
        catUser1.setProfile(catProfile);
        final CatUser result = userRepository.save(catUser1);

        assertThat(result).isNotEqualTo(null);
        assertThat(result.getProfile().getProfileName()).isEqualTo(profileName);

    }

}
