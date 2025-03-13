package com.mtrifonov.hibernateproject.sql.customizers;

import java.util.List;

import com.mtrifonov.hibernateproject.sql.SqlPreparator;


public class WhereCustomizer<T extends SqlPreparator> {

    private String sql = " WHERE ";
    private T main;
    private int paramCounter;
    private List<Object> paramArgs;

    public WhereCustomizer(T main) {
        this.main = main;
        this.paramCounter = main.getParamCounter();
        this.paramArgs = main.getParamArgs();
    }

    public WhereCustomizer<T> eq(String field, Object[] args) {

        sql += "(";
        for (int i = 0; i < args.length; i++) {
            sql += field + " = :p" + paramCounter;
            paramCounter++;
            paramArgs.add(args[i]);
            if (i < args.length - 1) {
                this.or();
            }
        }
        sql += ")";
        return this;
    }

    public WhereCustomizer<T> like(String[] fields, String arg) {

        sql += "(";
        for (int i = 0; i < fields.length; i++) {

            String field = fields[i];
            sql += "upper(" + field + ") LIKE upper('%" + arg + "%')";
            if (i < fields.length - 1) {
                this.or();
            }
        }
        sql += ")";
        return this;
    }

    public WhereCustomizer<T> less(String field, Object arg) {

        sql += "(" + field + " < :p" + paramCounter + ")";
        paramCounter++;
        paramArgs.add(arg);
        return this;
    }

    public WhereCustomizer<T> greater(String field, Object arg) {

        sql += "(" + field + " > :p" + paramCounter + ")";
        paramCounter++;
        paramArgs.add(arg);
        return this;
    }

    public WhereCustomizer<T> beetwen(String field, Object arg1, Object arg2) {
    
        sql += "(" + field + " BETWEEN :p" + paramCounter;
        paramCounter++;
        paramArgs.add(arg1);
        this.and();
        sql += " :p" + paramCounter  + ")";
        paramCounter++; 
        paramArgs.add(arg2);
        return this;
    }

    public WhereCustomizer<T> or() {
        sql += " OR ";
        return this;
    }

    public WhereCustomizer<T> and() {
        sql += " AND ";
        return this;
    }

    public T toMain() {
        return main;
    }

    public String getSql() {
        return sql;
    }
}
