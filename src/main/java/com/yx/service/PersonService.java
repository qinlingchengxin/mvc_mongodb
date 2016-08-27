package com.yx.service;

import com.yx.bean.Person;
import com.yx.dao.PersonDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonService {

    @Resource
    private PersonDao personDao;

    public void savePerson(Person person) {
        personDao.savePerson(person);
    }

    public List<Person> queryPersons() {
        return personDao.queryPersons();
    }

    public Person queryPersonById(Integer id) {
        return personDao.queryPersonById(id);
    }

    public void findAndModify(Integer id, Integer age) {
        personDao.findAndModify(id, age);
    }

    public void removeOne(Integer id) {
        personDao.removeOne(id);
    }

    public long count(Person person) {
        return personDao.count(person);
    }
}
