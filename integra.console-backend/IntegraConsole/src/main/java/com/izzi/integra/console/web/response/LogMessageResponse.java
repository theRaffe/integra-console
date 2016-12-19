package com.izzi.integra.console.web.response;

import com.izzi.integra.console.dao.entity.LogMessage;

import java.util.List;

/**
 * Created by laura.hernandez.ext on 24/11/2016.
 */
public class LogMessageResponse {
    private boolean success;
    private String message;
    private List<LogMessage> logMessageList;

    public LogMessageResponse(boolean success, String message, List<LogMessage> logMessageList){
        this.success = success;
        this.message = message;
        this.logMessageList = logMessageList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LogMessage> getLogMessageList() {
        return logMessageList;
    }

    public void setLogMessageList(List<LogMessage> logMessageList) {
        this.logMessageList = logMessageList;
    }
}
