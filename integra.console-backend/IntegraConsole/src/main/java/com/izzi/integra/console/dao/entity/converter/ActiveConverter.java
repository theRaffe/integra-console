package com.izzi.integra.console.dao.entity.converter;

import javax.persistence.AttributeConverter;

/**
 * Created by Rafael on 20/12/2016.
 */
public class ActiveConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean != null && aBoolean ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return "Y".equals(s);
    }
}
