package com.izzi.integra.console.test.integral;

import com.izzi.integra.console.Application;
import com.izzi.integra.console.test.ApplicationTest;
import com.izzi.integra.console.test.utils.UserAgentInterceptor;
import com.izzi.integra.console.web.request.LogMessageRequest;
import com.izzi.integra.console.web.response.LogMessageResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;

import static com.izzi.integra.console.security.TokenAuthHelper.getTokenAuth;
import static com.izzi.integra.console.test.utils.DateHelper.beginOfDay;
import static com.izzi.integra.console.test.utils.DateHelper.endOfDay;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

/**
 * Created by Rafael on 27/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeLogMessageIntegral.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterLogMessageIntegral.sql") })
*/
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
    @SqlGroup({
            @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/beforeLogMessageIntegral.sql", config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/afterLogMessageIntegral.sql", config = @SqlConfig(transactionMode = ISOLATED))
    })
    public void integrationLogMessageTest() {
        final LogMessageRequest logMessageRequest1 = new LogMessageRequest();
        final Date dtm = new Date();

        final Date beginDate = beginOfDay(dtm);
        final Date endDate = endOfDay(dtm);

        logMessageRequest1.setBusinessMsgId("businessMsgId-1");
        logMessageRequest1.setFromDate(beginDate);
        logMessageRequest1.setToDate(endDate);

        final ResponseEntity<LogMessageResponse> responseEntity = this.template.postForEntity("/getLogMessages", logMessageRequest1, LogMessageResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        final LogMessageResponse logMessageResponse = responseEntity.getBody();
        assertThat(logMessageResponse).isNotNull();
        assertThat(logMessageResponse.getLogMessageList()).isNotEmpty();

        assertThat(logMessageResponse.getLogMessageList().get(0).getBusinessMsgId()).isEqualTo(logMessageRequest1.getBusinessMsgId());
        logger.info(MessageFormat.format("getting LogMessageResponse: {0}", responseEntity.getBody()));

    }

}
