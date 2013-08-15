package test.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.entities.MyEntity;

/**
 * @author guyburton
 *         Date: 15/08/2013
 */
@Repository
public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {

}
