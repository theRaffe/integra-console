package com.izzi.integra.console.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by laura.hernandez.ext on 23/11/2016.
 */
@Entity
@Table(name = "VIEW_LOG_MESSAGE")
public class LogMessage {

    @Id
    @Column(name = "LOG_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_ID_SEQ")
    @SequenceGenerator(name = "LOG_ID_SEQ", sequenceName = "LOG_ID_SEQ", allocationSize = 1)
    private Long logId;

    @Column(name = "LOG_TYPE_ID")
    private Long logTypeId;

    @Column(name = "PROCESS_ID")
    private String processId;

    @Column(name = "PROCESS_NAME")
    private String processName;

    @Column(name = "SUB_PROCESS")
    private String subprocess;

    @Column(name = "INFO")
    private String info;

    @Column(name = "ORIGIN_ID")
    private String originId;

    @Column(name = "ORIGEN_NAME")
    private String originName;

    @Column(name = "DESTINATION_ID")
    private String destinationId;

    @Column(name = "DESTINATION_NAME")
    private String destinationName;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "PROCESS_IDENTIFIER")
    private String processIdentifier;

    @Column(name = "RESENDABLE_MESSAGE")
    private String resendableMessage;

    @Column(name = "ERROR_ID")
    private Long errorId;

    @Column(name = "ERROR_CODE")
    private String errorCode;

    @Column(name = "ERROR_STATUS")
    private Long errorStatus;

    @Column(name = "RETRY_COUNT")
    private Long retryCount;

    @Column(name = "BUSINESS_MSG_ID")
    private String businessMsgId;

    @Column(name = "ERROR_DESC")
    private String errorDesc;

    @Column(name = "ERROR_AUTOMATIC_ACTION_TYPE")
    private Long errorAutomaticActionType;

    @Column(name = "PROCESS_QUEUE")
    private String processQueue;

    @Column(name = "FLAG")
    private Long flag;

    @Column(name = "SERVICE_ID")
    private String serviceId;

    @Column(name = "APPLICATION")
    private String application;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "SEQUENCE")
    private String sequence;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getLogTypeId() {
        return logTypeId;
    }

    public void setLogTypeId(Long logTypeId) {
        this.logTypeId = logTypeId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getSubprocess() {
        return subprocess;
    }

    public void setSubprocess(String subprocess) {
        this.subprocess = subprocess;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getProcessIdentifier() {
        return processIdentifier;
    }

    public void setProcessIdentifier(String processIdentifier) {
        this.processIdentifier = processIdentifier;
    }

    public String getResendableMessage() {
        return resendableMessage;
    }

    public void setResendableMessage(String resendableMessage) {
        this.resendableMessage = resendableMessage;
    }

    public Long getErrorId() {
        return errorId;
    }

    public void setErrorId(Long errorId) {
        this.errorId = errorId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Long getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(Long errorStatus) {
        this.errorStatus = errorStatus;
    }

    public Long getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Long retryCount) {
        this.retryCount = retryCount;
    }

    public String getBusinessMsgId() {
        return businessMsgId;
    }

    public void setBusinessMsgId(String businessMsgId) {
        this.businessMsgId = businessMsgId;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public Long getErrorAutomaticActionType() {
        return errorAutomaticActionType;
    }

    public void setErrorAutomaticActionType(Long errorAutomaticActionType) {
        this.errorAutomaticActionType = errorAutomaticActionType;
    }

    public String getProcessQueue() {
        return processQueue;
    }

    public void setProcessQueue(String processQueue) {
        this.processQueue = processQueue;
    }

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "logId=" + logId +
                ", logTypeId=" + logTypeId +
                ", processId='" + processId + '\'' +
                ", processName='" + processName + '\'' +
                ", subprocess='" + subprocess + '\'' +
                ", info='" + info + '\'' +
                ", originId='" + originId + '\'' +
                ", originName='" + originName + '\'' +
                ", destinationId='" + destinationId + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", creationDate=" + creationDate +
                ", processIdentifier='" + processIdentifier + '\'' +
                ", resendableMessage='" + resendableMessage + '\'' +
                ", errorId=" + errorId +
                ", errorCode='" + errorCode + '\'' +
                ", errorStatus=" + errorStatus +
                ", retryCount=" + retryCount +
                ", businessMsgId='" + businessMsgId + '\'' +
                ", errorDesc='" + errorDesc + '\'' +
                ", errorAutomaticActionType=" + errorAutomaticActionType +
                ", processQueue='" + processQueue + '\'' +
                ", flag=" + flag +
                ", serviceId='" + serviceId + '\'' +
                ", application='" + application + '\'' +
                ", category='" + category + '\'' +
                ", sequence='" + sequence + '\'' +
                '}';
    }
}
