package vishu.personal.springmvchibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import vishu.personal.springmvchibernate.model.Person;

import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {
    private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPerson(Person p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(p);
        logger.info("Person successfully added. Person Details: " +p);
    }

    @Override
    public void updatePerson(Person p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("Person updated successfully. Updated Person Details: " +p);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Person> getAllPersons() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> personList = session.createQuery("from Person").list();
        for(Person p: personList){
            logger.info("Person List :: " +p);
        }
        return personList;
    }

    @Override
    public Person getPersonById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        if(p != null){
            logger.info("Person loaded successfully. Person details: " +p);
//            return p;
        }
        else{
            logger.info("No person with ID: " +id);
        }
        return p;
    }

    @Override
    public void removePersonById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        if(p != null){
            session.delete(p);
            logger.info("Person with ID: " +id +"deleted successfully");
        }
        else{
            logger.info("There is no Person with ID: " +id);
        }
    }
}
