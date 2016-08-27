package com.yx.controller;

import com.yx.bean.Person;
import com.yx.service.PersonService;
import com.yx.utils.GenResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "person")
public class PersonController {

    @Resource
    private PersonService personService;

    @RequestMapping(value = "savePerson")
    @ResponseBody
    public Map<String, Object> savePerson(Integer id, String name, Integer age) {
        Person person = new Person(id, name, age);
        personService.savePerson(person);
        return GenResult.SUCCESS.genResult();
    }

    @RequestMapping(value = "queryPersons")
    @ResponseBody
    public Map<String, Object> queryPersons() {
        List<Person> persons = personService.queryPersons();
        return GenResult.SUCCESS.genResult(persons);
    }

    @RequestMapping(value = "queryPersonById")
    @ResponseBody
    public Map<String, Object> queryPersonById(Integer id) {
        Person person = personService.queryPersonById(id);
        return GenResult.SUCCESS.genResult(person);
    }

    @RequestMapping(value = "findAndModify")
    @ResponseBody
    public Map<String, Object> findAndModify(Integer id, Integer age) {
        personService.findAndModify(id, age);
        return GenResult.SUCCESS.genResult();
    }

    @RequestMapping(value = "removeOne")
    @ResponseBody
    public Map<String, Object> removeOne(Integer id) {
        personService.removeOne(id);
        return GenResult.SUCCESS.genResult();
    }

    @RequestMapping(value = "count")
    @ResponseBody
    public Map<String, Object> count(Integer id, String name, Integer age) {
        Person person = new Person(id, name, age);
        long count = personService.count(person);
        return GenResult.SUCCESS.genResult(count);
    }
}
