package com.izzi.integra.console.service;

import com.izzi.integra.console.dao.entity.LogMessage;
import com.izzi.integra.console.dao.entity.QLogMessage;
import com.izzi.integra.console.dao.repository.LogMessageRepository;
import com.izzi.integra.console.web.request.LogMessageRequest;
import com.izzi.integra.console.web.response.LogMessageResponse;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by laura.hernandez.ext on 24/11/2016.
 */
@Service
public class LogMessageService {

    private static final Logger logger = LoggerFactory.getLogger(LogMessageService.class);

    @Autowired
    private LogMessageRepository logMessageRepository;

    public LogMessageResponse getLogMessageByProperties(final LogMessageRequest logMessageRequest) {
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
            if (logMessages == null || logMessages.size() == 0) {
                return new LogMessageResponse(false, "Records not found", null);
            }

            return new LogMessageResponse(true, "success", logMessages);
        } catch (final Exception e) {
            logger.error(MessageFormat.format("Error at getting LogMessage: {0}", ReflectionToStringBuilder.toString(logMessageRequest)), e);
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
