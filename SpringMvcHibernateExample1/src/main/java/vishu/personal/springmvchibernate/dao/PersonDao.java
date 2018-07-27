package vishu.personal.springmvchibernate.dao;

import vishu.personal.springmvchibernate.model.Person;

import java.util.List;

public interface PersonDao {
    public void addPerson(Person p);
    public void updatePerson(Person p);
    public List<Person> getAllPersons();
    public Person getPersonById(int id);
    public void removePersonById(int id);
}
