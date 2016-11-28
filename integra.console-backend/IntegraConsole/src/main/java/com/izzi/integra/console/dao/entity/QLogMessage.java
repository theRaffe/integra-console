package com.izzi.integra.console.dao.entity;


import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

/**
 * QLogMessage is a Querydsl query type for LogMessage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLogMessage extends EntityPathBase<LogMessage> {

    private static final long serialVersionUID = 1745537804L;

    public static final QLogMessage logMessage = new QLogMessage("logMessage");

    public final StringPath application = createString("application");

    public final StringPath businessMsgId = createString("businessMsgId");

    public final StringPath category = createString("category");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final StringPath destinationId = createString("destinationId");

    public final StringPath destinationName = createString("destinationName");

    public final NumberPath<Long> errorAutomaticActionType = createNumber("errorAutomaticActionType", Long.class);

    public final StringPath errorCode = createString("errorCode");

    public final StringPath errorDesc = createString("errorDesc");

    public final NumberPath<Long> errorId = createNumber("errorId", Long.class);

    public final NumberPath<Long> errorStatus = createNumber("errorStatus", Long.class);

    public final NumberPath<Long> flag = createNumber("flag", Long.class);

    public final StringPath info = createString("info");

    public final NumberPath<Long> logId = createNumber("logId", Long.class);

    public final NumberPath<Long> logTypeId = createNumber("logTypeId", Long.class);

    public final StringPath originId = createString("originId");

    public final StringPath originName = createString("originName");

    public final StringPath processId = createString("processId");

    public final StringPath processIdentifier = createString("processIdentifier");

    public final StringPath processName = createString("processName");

    public final StringPath processQueue = createString("processQueue");

    public final StringPath resendableMessage = createString("resendableMessage");

    public final NumberPath<Long> retryCount = createNumber("retryCount", Long.class);

    public final StringPath sequence = createString("sequence");

    public final StringPath serviceId = createString("serviceId");

    public final StringPath subprocess = createString("subprocess");

    public QLogMessage(String variable) {
        super(LogMessage.class, forVariable(variable));
    }

    public QLogMessage(Path<? extends LogMessage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogMessage(PathMetadata metadata) {
        super(LogMessage.class, metadata);
    }

}

