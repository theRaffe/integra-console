package com.izzi.integra.console.web.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by laura.hernandez.ext on 24/11/2016.
 */
public class LogMessageRequest {

    private static final Logger logger = LoggerFactory.getLogger(LogMessageRequest.class);
    private static SimpleDateFormat SDF_ddMMyyyy_HHmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private String businessMsgId;
    private Long logId;
    private String processId;
    private Date fromDate;
    private Date toDate;
    private Long logTypeId;


    public String getBusinessMsgId() {
        return businessMsgId;
    }

    public void setBusinessMsgId(String businessMsgId) {
        this.businessMsgId = businessMsgId;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Date getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return this.toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getLogTypeId() {
        return logTypeId;
    }

    public void setLogTypeId(Long logTypeId) {
        this.logTypeId = logTypeId;
    }

    private Date formatStringToDate(String date) {
        try {
            return date != null && !"".equals(date) ? SDF_ddMMyyyy_HHmm.parse(date) : null;
        } catch (ParseException e) {
            logger.error(MessageFormat.format("Error at parsing Date: {0}", date), e);
            return null;
        }
    }
}
