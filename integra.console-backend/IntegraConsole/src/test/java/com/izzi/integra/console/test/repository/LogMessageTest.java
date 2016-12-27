package com.izzi.integra.console.test.repository;

import com.izzi.integra.console.dao.entity.LogMessage;
import com.izzi.integra.console.dao.entity.QLogMessage;
import com.izzi.integra.console.dao.repository.LogMessageRepository;
import com.izzi.integra.console.web.request.LogMessageRequest;
import com.izzi.integra.console.web.response.LogMessageResponse;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Rafael on 26/12/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class LogMessageTest {

    private static final Logger logger = LoggerFactory.getLogger(LogMessageTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LogMessageRepository logMessageRepository;

    private LogMessage mLogMessage1;
    private LogMessage mLogMessage2;

    public static Date beginOfDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date endOfDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
    }

    @Before
    public void setup() {
        mLogMessage1 = new LogMessage();
        mLogMessage1.setBusinessMsgId("businessMsgId-1");
        mLogMessage1.setProcessId("proc-1");
        mLogMessage1.setLogTypeId(1L);
        mLogMessage1.setCreationDate(new Date());

        mLogMessage2 = new LogMessage();
        mLogMessage2.setBusinessMsgId("businessMsgId-2");
        mLogMessage2.setProcessId("proc-1");
        mLogMessage2.setLogTypeId(2L);
        mLogMessage2.setCreationDate(new Date());

        this.entityManager.persist(mLogMessage1);
        this.entityManager.persist(mLogMessage2);

    }

    @Test
    public void customLogMessageQueryOk() {
        //request1
        final LogMessageRequest logMessageRequest1 = new LogMessageRequest();
        final Date beginDate = beginOfDay(new Date());
        final Date endDate = endOfDay(new Date());

        logMessageRequest1.setBusinessMsgId("businessMsgId-1");
        logMessageRequest1.setFromDate(beginDate);
        logMessageRequest1.setToDate(endDate);

        final LogMessageResponse logMessageResponse = getLogMessageByParams(logMessageRequest1);
        assertThat(logMessageResponse).isNotNull();
        assertThat(logMessageResponse.getLogMessageList().size()).isEqualTo(1);

        final LogMessage logMessage1 = logMessageResponse.getLogMessageList().get(0);
        assertThat(logMessage1.getBusinessMsgId()).isEqualTo(logMessageRequest1.getBusinessMsgId());
        //request2
        final LogMessageRequest logMessageRequest2 = new LogMessageRequest();
        logMessageRequest2.setProcessId("proc-1");
        logMessageRequest2.setFromDate(beginDate);
        logMessageRequest2.setToDate(endDate);

        final LogMessageResponse logMessageResponse2 = getLogMessageByParams(logMessageRequest2);
        assertThat(logMessageResponse2).isNotNull();
        assertThat(logMessageResponse2.getLogMessageList().size()).isEqualTo(2);

        //request3
        final LogMessageRequest logMessageRequest3 = new LogMessageRequest();
        logMessageRequest3.setLogTypeId(2L);
        logMessageRequest3.setFromDate(beginDate);
        logMessageRequest3.setToDate(endDate);

        final LogMessageResponse logMessageResponse3 = getLogMessageByParams(logMessageRequest3);
        assertThat(logMessageResponse3).isNotNull();
        assertThat(logMessageResponse3.getLogMessageList().size()).isEqualTo(1);

        final LogMessage logMessage2 = logMessageResponse3.getLogMessageList().get(0);
        logger.debug(MessageFormat.format("response logMessage2: {0}", logMessage2));
        assertThat(logMessage2.getBusinessMsgId()).isEqualTo(this.mLogMessage2.getBusinessMsgId());

    }

    private LogMessageResponse getLogMessageByParams(final LogMessageRequest logMessageRequest) {
        final QLogMessage qLogMessage = QLogMessage.logMessage;

        final BooleanExpression businessMsgPredicate = getBooleanExpression(qLogMessage.businessMsgId, logMessageRequest.getBusinessMsgId());

        final BooleanExpression processPredicate = getBooleanExpression(qLogMessage.processId, logMessageRequest.getProcessId());

        final BooleanExpression logIdPredicate = getBooleanExpression(qLogMessage.logId, logMessageRequest.getLogId());

        final BooleanExpression logTypeIdPredicate = getBooleanExpression(qLogMessage.logTypeId, logMessageRequest.getLogTypeId());

        //add conditions
        final Predicate predicate = qLogMessage.creationDate
                .between(logMessageRequest.getFromDate(), logMessageRequest.getToDate())
                .and(businessMsgPredicate)
                .and(processPredicate)
                .and(logIdPredicate)
                .and(logTypeIdPredicate);
        try {
            final List<LogMessage> logMessages = logMessageRepository.findAll(predicate);
            if (logMessages == null) {
                return new LogMessageResponse(false, "No records found", null);
            } else {
                return new LogMessageResponse(true, "success", logMessages);
            }
        } catch (final Exception e) {
            return new LogMessageResponse(false, e.getMessage(), null);
        }
    }

    private BooleanExpression getBooleanExpression(final StringPath stringPath, final String value) {
        return value != null && !"".equals(value) ? stringPath.eq(value) : null;
    }

    private BooleanExpression getBooleanExpression(final NumberPath numberPath, final Long value) {
        return (value != null) ? numberPath.eq(value) : null;
    }

}
