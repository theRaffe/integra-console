package com.izzi.integra.console.dao.repository;

import com.izzi.integra.console.dao.entity.LogMessage;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by laura.hernandez.ext on 23/11/2016.
 */
public interface LogMessageRepository  extends Repository<LogMessage, Long>, QueryDslPredicateExecutor<LogMessage> {

    List<LogMessage> findAll(Predicate predicate);
}
