package gwittersec

import grails.gorm.transactions.Transactional

@Transactional
class TwitCreateService {

    static Twit create(String header, String content, Person person, boolean flush = false) {
        Twit instance = new Twit(header: header, content: content, person: person)
        List twitIsExist = Twit.findAll()

        if ((instance in Twit.findAll()) && (person.id in twitIsExist.personId)) {
            println('such a tweet is already in the database')
        }
        else {
            instance.save(flush: flush)
            instance
        }
    }

    List<Twit> getTwitsByPerson(Long id){
        Person person = Person.get(id: id)
        person.getTwits().toList()
    }
}
