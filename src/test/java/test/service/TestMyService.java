package test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import test.entities.MyEntity;
import test.persistence.MyEntityRepository;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author guyburton
 *         Date: 15/08/2013
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TestMyService {

    @Autowired
    private MyEntityRepository myEntityRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test public void
    creatingAndEntityGeneratesAnId() {
        MyEntity entity = createEntity();
        MyEntity savedEntity = myEntityRepository.save(entity);
        assertThat("Object Identity", entity == savedEntity);
        assertThat(entity.getId(), equalTo(1L));
    }

    @Test public void
    canRetrieveSavedEntityById() {
        transactionManager.getTransaction(null);

        MyEntity entity = createEntity();
        MyEntity savedEntity = myEntityRepository.save(entity);
        MyEntity retrievedEntity = myEntityRepository.findOne(savedEntity.getId());
        assertThat(retrievedEntity, equalTo(savedEntity));
    }

    private MyEntity createEntity() {
        MyEntity entity = new MyEntity();
        entity.setCreatedDate(new Date());
        entity.setAge(15);
        entity.setName("Test");
        return entity;
    }
}
