package gwittersec

import grails.gorm.services.Service

@Service(Person)
class TwitByPersonIDService {
    List<Twit> getTwitsByPerson(Long id){
        Person person = Person.get(id: id)
        person.getTwits().toList()
    }

}