package test.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Basic model bean
 * @author guyburton
 * Date: 15/08/2013
 */
@Entity
@Table(name="MyEntities")
public class MyEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long id;

    private String name;

    private int age;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyEntity)) return false;

        MyEntity myEntity = (MyEntity) o;

        if (age != myEntity.age) return false;
        if (createdDate != null ? !createdDate.equals(myEntity.createdDate) : myEntity.createdDate != null)
            return false;
        if (id != null ? !id.equals(myEntity.id) : myEntity.id != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(myEntity.modifiedDate) : myEntity.modifiedDate != null)
            return false;
        if (name != null ? !name.equals(myEntity.name) : myEntity.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        return result;
    }
}
