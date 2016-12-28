package com.izzi.integra.console.test.integral;

import com.izzi.integra.console.Application;
import com.izzi.integra.console.test.ApplicationTest;
import com.izzi.integra.console.test.utils.UserAgentInterceptor;
import com.izzi.integra.console.web.request.LogMessageRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;

import static com.izzi.integra.console.security.TokenAuthHelper.getTokenAuth;
import static com.izzi.integra.console.test.utils.DateHelper.*;

/**
 * Created by Rafael on 27/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogMessageIntegralTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationTest.class);

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() {
        final String token = getTokenAuth();
        logger.info(MessageFormat.format("auth token is: {0}", token));
        template.getRestTemplate().setInterceptors(Collections.<ClientHttpRequestInterceptor>singletonList(new UserAgentInterceptor("Authorization", token)));
    }

    @Test
    public void integrationLogMessageTest() {
        final LogMessageRequest logMessageRequest1 = new LogMessageRequest();
        Date dtm = settingDate(26, 12, 2016);

        final Date beginDate = beginOfDay(dtm);
        final Date endDate = endOfDay(dtm);

        logMessageRequest1.setBusinessMsgId("ADTN14170B88");
        logMessageRequest1.setFromDate(beginDate);
        logMessageRequest1.setToDate(endDate);

        /*final ResponseEntity<LogMessageResponse> responseEntity =  this.template.postForEntity("/getLogMessages", logMessageRequest1, LogMessageResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        final LogMessageResponse logMessageResponse = responseEntity.getBody();
        assertThat(logMessageResponse).isNotNull();
        assertThat(logMessageResponse.getLogMessageList()).isNotEmpty();

        logger.info(MessageFormat.format("getting LogMessageResponse: {0}", responseEntity.getBody()));
        */
    }

}
