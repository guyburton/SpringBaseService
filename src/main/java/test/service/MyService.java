package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import test.entities.MyEntity;
import test.persistence.MyEntityRepository;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.Date;
import java.util.List;

/**
 * @author guyburton
 * Date: 15/08/2013
 */
@WebService
@Path("testService")
public class MyService {

    @Autowired
    private MyEntityRepository myEntityRepository;

    @GET
    @Produces("application/json")
    @Transactional(readOnly = true)
    public List<MyEntity> get() {
        return myEntityRepository.findAll();
    }

    @POST
    @Transactional
    public Long create() {
        MyEntity entity = new MyEntity();
        entity.setCreatedDate(new Date());
        entity.setAge(15);
        entity.setName("Test");

        MyEntity savedEntity = myEntityRepository.save(entity);
        return savedEntity.getId();
    }

    @PUT
    @Transactional
    public void update(@QueryParam("id") Long id) {
        MyEntity entity = myEntityRepository.findOne(id);
        entity.setModifiedDate(new Date());
    }
}
