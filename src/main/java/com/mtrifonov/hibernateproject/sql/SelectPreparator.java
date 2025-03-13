package com.mtrifonov.hibernateproject.sql;

import java.util.ArrayList;
import java.util.List;

import com.mtrifonov.hibernateproject.sql.customizers.FromCustomizer;
import com.mtrifonov.hibernateproject.sql.customizers.WhereCustomizer;

public class SelectPreparator implements SqlPreparator {

    private FromCustomizer<SelectPreparator> from;
    private WhereCustomizer<SelectPreparator> where;
    private int paramCounter = 1;
    private List<Object> paramArgs = new ArrayList<>();

    public FromCustomizer<SelectPreparator> from(Class<?> table, String shortName) {
        from = new FromCustomizer<>(this).from(table, shortName);
        return from;
    }

    public WhereCustomizer<SelectPreparator> where() {
        where = new WhereCustomizer<>(this);
        return where;
    }

    @Override
    public String getSql() {
        var sql = "";
        if (from != null) {
            sql += from.getSql();
        }
        if (where != null) {
            sql += where.getSql();
        }
        return sql;
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
