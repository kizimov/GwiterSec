package gwittersec

import grails.gorm.transactions.Transactional

@Transactional
class CustomPersonService implements PersonService{

    Person show(Long id){
        println ("get id = " + id)
        Person.findById(id)
    }

    Set <Person> showSubscriptions(Long id){
        Person.get(id).getSubscriptions()
    }

    void follow(Long followerId, Long followsId){
        // add check methods if exists
        Person follower = Person.get(followerId)
        Person follows = Person.get(followsId)
        follows.addToSubscriptions(follower).save()
    }


    @Override
    Person get(Serializable id) {
        return null
    }

    @Override
    List<Person> list(Map args) {
        Person.findAll(args)
    }

    List<Person> list(){
        Person.findAll()
    }

    @Override
    Long count() {
        return null
    }

    @Override
    void delete(Serializable id) {
        Person user = Person.get(id)
        PersonRole.findByPerson(user).delete()
        user.delete()
    }

    @Override
    Person save(Person person) {
        return null
    }
}
