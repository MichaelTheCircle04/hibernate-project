package com.mtrifonov.hibernateproject.sql;

import java.util.ArrayList;
import java.util.List;

import com.mtrifonov.hibernateproject.sql.customizers.FromCustomizer;
import com.mtrifonov.hibernateproject.sql.customizers.WhereCustomizer;


public class UpdatePreparator implements SqlPreparator {

    private String sql = "";
    private FromCustomizer<UpdatePreparator> from;
    private WhereCustomizer<UpdatePreparator> where;
    private int paramCounter = 1;
    private List<Object> paramArgs = new ArrayList<>();

    public UpdatePreparator update(Class<?> table, String shortName) {
        sql += "UPDATE " + table.getSimpleName() + " " + shortName;
        return this;
    }
    
    public UpdatePreparator set(String[] fields, Object[] values) {

        if (fields.length != values.length) {
            throw new IllegalArgumentException("fields and values must have same length");
        }
        sql += " SET";

        for (int i = 0; i < fields.length; i++) {
            sql += " " + fields[i] + " = :p" + paramCounter;
            paramCounter++;
            paramArgs.add(values[i]);
            if (i < fields.length - 1) {
                sql += ",";
            }
        }

        return this;
    }

    public FromCustomizer<UpdatePreparator> from(Class<?> table, String shortName) {
        from = new FromCustomizer<>(this).from(table, shortName);
        return from;
    }

    public WhereCustomizer<UpdatePreparator> where() {
        where = new WhereCustomizer<>(this);
        return where;
    }

    @Override
    public String getSql() {
        var result = sql;
        if (from != null) {
            result += from.getSql();
        }
        if (where != null) {
            result += where.getSql();
        }
        return result;
    }

    @Override
    public int getParamCounter() {
        return paramCounter;
    }

    @Override
    public List<Object> getParamArgs() {
        return paramArgs;
    }
}
