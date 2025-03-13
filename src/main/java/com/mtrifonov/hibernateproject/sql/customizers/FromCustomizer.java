package com.mtrifonov.hibernateproject.sql.customizers;

import com.mtrifonov.hibernateproject.sql.SqlPreparator;

public class FromCustomizer<T extends SqlPreparator> {

    private String sql = "";
    private T main;

    public FromCustomizer(T main) {
        this.main = main;
    }

    public FromCustomizer<T> from(Class<?> table, String shortName) {
        sql += " FROM " + table.getSimpleName() + " " + shortName;
        return this;
    }

    public T join(String[] fetch, String[] shortNames) {
        for (int i = 0; i < fetch.length; i++) {
            sql += " JOIN FETCH " + fetch[i] + " " + shortNames[i];
        }
        return main;
    }

    public T joinForCar() {
        this.join(new String[]{"c.model", "m.brand", "c.status"}, new String[]{"m", "b", "s"});
        return main;
    }

    public T joinForModel() {
        this.join(new String[]{"m.brand"}, new String[]{"b"});
        return main;
    }

    public T joinForModelWithCars() {
        this.join(new String[]{"m.cars", "m.brand", "c.status"}, new String[]{"c", "b", "s"});
        return main;
    }

    public T joinForBrandWithCars() {
        this.join(new String[]{"b.models", "m.cars", "c.status"}, new String[]{"m", "c", "s"});
        return main;
    }

    public T joinForBrandWithModels() {
        this.join(new String[]{"b.models"}, new String[]{"m"});
        return main;
    }

    public T toMain() {
        return main;
    }

    public String getSql() {
        return sql;
    }
}
