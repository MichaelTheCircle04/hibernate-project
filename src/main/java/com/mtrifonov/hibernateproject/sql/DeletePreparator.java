package com.mtrifonov.hibernateproject.sql;

import java.util.ArrayList;
import java.util.List;

import com.mtrifonov.hibernateproject.sql.customizers.FromCustomizer;
import com.mtrifonov.hibernateproject.sql.customizers.WhereCustomizer;

public class DeletePreparator implements SqlPreparator {

    private String sql = "DELETE";
    private FromCustomizer<DeletePreparator> from;
    private WhereCustomizer<DeletePreparator> where;
    private int paramCounter = 1;
    private List<Object> paramArgs = new ArrayList<>();


    public FromCustomizer<DeletePreparator> from(Class<?> table, String shortName) {
        from = new FromCustomizer<>(this).from(table, shortName);
        return from;
    }

    public WhereCustomizer<DeletePreparator> where() {
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
