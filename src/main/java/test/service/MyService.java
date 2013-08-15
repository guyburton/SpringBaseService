package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import test.entities.MyEntity;
import test.persistence.MyEntityRepository;

import java.util.Date;

/**
 * @author guyburton
 * Date: 15/08/2013
 */
@Component
@Transactional
public class MyService {

    @Autowired
    private MyEntityRepository myEntityRepository;

    public Long createAnEntity() {
        MyEntity entity = new MyEntity();
        entity.setCreatedDate(new Date());
        entity.setAge(15);
        entity.setName("Test");

        MyEntity savedEntity = myEntityRepository.save(entity);
        return savedEntity.getId();
    }

    public void doSomethingToAnEntity(Long id) {
        MyEntity entity = myEntityRepository.findOne(id);
        entity.setModifiedDate(new Date());
    }
}
