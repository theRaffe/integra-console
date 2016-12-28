package com.izzi.integra.console.dao.converter;

import javax.persistence.AttributeConverter;

/**
 * Created by Rafael on 15/12/2016.
 */
public class RouterLinkConverter implements AttributeConverter<String[], String> {
    @Override
    public String convertToDatabaseColumn(String[] strings) {
        return strings != null & strings.length > 0 ? strings[0] : "";
    }

    @Override
    public String[] convertToEntityAttribute(String s) {
        return !"".equals(s) && s != null ? new String[]{s} : new String[0];
    }
}
