package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.StatusTable;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Mikhail Trifonov
 */
@Repository
public class StatusTableRepository extends ParentRepositoryImpl<StatusTable> {
    
    @Autowired
    public StatusTableRepository(SessionFactory factory) {
        super(factory, StatusTable.class);
    }
    
}
