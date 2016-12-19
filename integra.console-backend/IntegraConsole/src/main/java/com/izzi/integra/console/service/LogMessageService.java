package com.izzi.integra.console.service;

import com.izzi.integra.console.dao.entity.QLogMessage;
import com.izzi.integra.console.dao.repository.LogMessageRepository;
import com.izzi.integra.console.web.request.LogMessageRequest;
import com.izzi.integra.console.web.response.LogMessageResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laura.hernandez.ext on 24/11/2016.
 */
@Service
public class LogMessageService {

    private static final Logger logger = LoggerFactory.getLogger(LogMessageService.class);

    @Autowired
    LogMessageRepository logMessageRepository;

    public LogMessageResponse getLogMessageByProperties(final LogMessageRequest logMessageRequest){
        QLogMessage qLogMessage = QLogMessage.logMessage;

        BooleanExpression businessMsgPredicate = null;
        if(logMessageRequest.getBusinessMsgId() != null && logMessageRequest.getBusinessMsgId().trim() != ""){
            businessMsgPredicate = qLogMessage.businessMsgId.eq(logMessageRequest.getBusinessMsgId());
        }

        BooleanExpression processPredicate = null;
        if(logMessageRequest.getProcessId() != null && logMessageRequest.getProcessId().trim() != ""){
            processPredicate = qLogMessage.processId.eq(logMessageRequest.getProcessId());
        }

        BooleanExpression logIdPredicate = null;
        if(logMessageRequest.getLogId() != null && logMessageRequest.getLogId() > 0){
            logIdPredicate = qLogMessage.logId.eq(logMessageRequest.getLogId());
        }

        BooleanExpression logTypeIdPredicate = null;
        if(logMessageRequest.getLogTypeId() != null && logMessageRequest.getLogTypeId() > 0){
            logTypeIdPredicate = qLogMessage.logTypeId.eq(logMessageRequest.getLogTypeId());
        }

        //add conditions
        BooleanExpression expression = qLogMessage.creationDate
                .between(logMessageRequest.getFromDate(), logMessageRequest.getToDate())
                .and(businessMsgPredicate)
                .and(processPredicate)
                .and(logIdPredicate)
                .and(logTypeIdPredicate);

        Predicate predicate = expression;
        OrderSpecifier sorter = qLogMessage.creationDate.asc();
        try {
            return new LogMessageResponse(true, "success", logMessageRepository.findAll(predicate));
        }catch(final Exception e){
            logger.error(MessageFormat.format("Error at getting LogMessage: {0}", ReflectionToStringBuilder.toString(logMessageRequest)), e);
            return new LogMessageResponse(false, e.getMessage(), null);
        }

    }

}
