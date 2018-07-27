package vishu.personal.springmvchibernate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vishu.personal.springmvchibernate.dao.PersonDao;
import vishu.personal.springmvchibernate.model.Person;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    @Transactional
    public void addPerson(Person p) {
        this.personDao.addPerson(p);
    }

    @Override
    @Transactional
    public void updatePerson(Person p) {
        this.personDao.updatePerson(p);
    }

    @Override
    @Transactional
    public List<Person> getAllPersons() {
        return this.personDao.getAllPersons();
    }

    @Override
    @Transactional
    public Person getPersonById(int id) {
        return this.personDao.getPersonById(id);
    }

    @Override
    @Transactional
    public void removePersonById(int id) {
        this.personDao.removePersonById(id);
    }
}
