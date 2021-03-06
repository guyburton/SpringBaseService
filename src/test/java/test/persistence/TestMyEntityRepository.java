package test.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import test.entities.MyEntity;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author guyburton
 *         Date: 15/08/2013
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class TestMyEntityRepository {

    public static final int AGE = 15;

    @Autowired
    private MyEntityRepository myEntityRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test public void
    creatingAndEntityGeneratesAnId() {
        MyEntity entity = createTestEntity();
        MyEntity savedEntity = myEntityRepository.save(entity);
        assertThat("Object Identity", entity == savedEntity);
        assertThat(savedEntity.getId(), notNullValue());
    }

    @Test public void
    canRetrieveSavedEntityById() {
        MyEntity savedEntity = myEntityRepository.save(createTestEntity());
        MyEntity retrievedEntity = myEntityRepository.findOne(savedEntity.getId());
        assertThat(retrievedEntity, equalTo(savedEntity));
    }

    @Test public void
    canRetrieveSavedEntity() {
        MyEntity savedEntity = myEntityRepository.saveAndFlush(createTestEntity());
        List<MyEntity> retrievedEntity = myEntityRepository.findAll();
        assertThat(retrievedEntity, hasSize(1));
    }

    @Test public void
    canFindByAge() {
        MyEntity savedEntity = myEntityRepository.save(createTestEntity());
        assertThat(myEntityRepository.findAll(), hasSize(1));
        List<MyEntity> retrievedEntities = myEntityRepository.findByAge(AGE);
        assertThat(retrievedEntities, hasSize(1));
        assertThat(retrievedEntities.get(0), equalTo(savedEntity));
    }

    @Test public void
    findByAgeWithNoResultsReturnsEmptyList() {
        MyEntity savedEntity = myEntityRepository.save(createTestEntity());
        assertThat(myEntityRepository.findByAge(AGE + 1), empty());
    }

    private MyEntity createTestEntity() {
        MyEntity entity = new MyEntity();
        entity.setCreatedDate(new Date());
        entity.setAge(AGE);
        entity.setName("Test");
        return entity;
    }
}
