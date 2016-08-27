package com.yx.dao;

import com.yx.bean.Person;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PersonDao {
    @Resource
    private MongoTemplate mongoTemplate;

    public void savePerson(Person person) {
        mongoTemplate.save(person);
    }

    public List<Person> queryPersons() {
        return mongoTemplate.findAll(Person.class);
    }

    public Person queryPersonById(Integer id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Person.class);
    }

    public void findAndModify(Integer id, Integer age) {
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), new Update().inc("age", age), Person.class);
    }

    public void insert(Person person) {
        mongoTemplate.insert(person);
    }

    public void removeOne(Integer id) {
        Criteria criteria = Criteria.where("id").in(id);
        if (criteria != null) {
            Query query = new Query(criteria);
            if (query != null && mongoTemplate.findOne(query, Person.class) != null)
                mongoTemplate.remove(mongoTemplate.findOne(query, Person.class));
        }
    }

    public void update(Person criteriaPerson, Person person) {
        Criteria criteria = Criteria.where("age").gt(criteriaPerson.getAge());
        Query query = new Query(criteria);
        Update update = Update.update("name", person.getName()).set("age", person.getAge());
        mongoTemplate.updateMulti(query, update, Person.class);
    }

    public List<Person> find(Person criteriaPerson, int skip, int limit) {
        Query query = getQuery(criteriaPerson);
        query.skip(skip);
        query.limit(limit);
        return mongoTemplate.find(query, Person.class);
    }

    public Person findAndModify(Person criteriaPerson, Person updatePerson) {
        Query query = getQuery(criteriaPerson);
        Update update = Update.update("age", updatePerson.getAge()).set("name", updatePerson.getName());
        return mongoTemplate.findAndModify(query, update, Person.class);
    }

    public Person findAndRemove(Person criteriaPerson) {
        Query query = getQuery(criteriaPerson);
        return mongoTemplate.findAndRemove(query, Person.class);
    }

    public long count(Person criteriaPerson) {
        Query query = getQuery(criteriaPerson);
        return mongoTemplate.count(query, Person.class);
    }

    private Query getQuery(Person criteriaPerson) {
        if (criteriaPerson == null) {
            criteriaPerson = new Person();
        }
        Query query = new Query();
        if (criteriaPerson.getId() != 0) {
            Criteria criteria = Criteria.where("id").is(criteriaPerson.getId());
            query.addCriteria(criteria);
        }
        if (criteriaPerson.getAge() > 0) {
            Criteria criteria = Criteria.where("age").is(criteriaPerson.getAge());
            query.addCriteria(criteria);
        }
        if (criteriaPerson.getName() != null) {
            Criteria criteria = Criteria.where("name").regex("^" + criteriaPerson.getName());
            query.addCriteria(criteria);
        }
        return query;
    }
}
