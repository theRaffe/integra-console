package com.izzi.integra.console.web.controller;

import com.izzi.integra.console.service.LogMessageService;
import com.izzi.integra.console.web.request.LogMessageRequest;
import com.izzi.integra.console.web.response.LogMessageResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

/**
 * Created by laura.hernandez.ext on 24/11/2016.
 */
@RestController
public class LogMessageRestController {

    private static final Logger logger = LoggerFactory.getLogger(LogMessageRestController.class);

    @Autowired
    private LogMessageService logMessageService;


    @RequestMapping(value = "/getLogMessages", method = RequestMethod.POST)
    public ResponseEntity<?> getLogMessages(@RequestBody final LogMessageRequest logMessageRequest){
        logger.debug(MessageFormat.format("searching log messages {0}", ReflectionToStringBuilder.toString(logMessageRequest)));
        final LogMessageResponse logMessageResponse = logMessageService.getLogMessageByProperties(logMessageRequest);
        return ResponseEntity.ok(logMessageResponse);
    }
}
