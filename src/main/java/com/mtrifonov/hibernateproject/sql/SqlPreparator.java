package com.mtrifonov.hibernateproject.sql;

import java.util.List;

public interface SqlPreparator {

    static SelectPreparator select() {
        return new SelectPreparator();
    }

    static UpdatePreparator update(Class<?> table, String shortName) {
        return new UpdatePreparator().update(table, shortName);
    }

    static DeletePreparator delete() {
        return new DeletePreparator();
    }

    String getSql();
    int getParamCounter();
    List<Object> getParamArgs();
}
