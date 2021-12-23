package gwittersec

import grails.validation.Validateable
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = ['header', 'content'])
@ToString(includes = ['header', 'content'], includeNames = true)
class Twit implements Validateable {

    String header
    String content

    static belongsTo = [person: Person]

    static constraints = {
        header blank: false, nullable: false
        content blank: false, nullable: false
    }

    static Twit create(String header, String content, Person person, boolean flush = false) {
        Twit instance = new Twit(header: header, content: content, person: person)
        List twitIsExist = Twit.findAll()

        if ((instance in Twit.findAll()) && (person.id in twitIsExist.personId)) {
            println('such a tweet is already in the database')
            println(instance.toString() + ' person_id ' + person.id)
        }
        else {
            instance.save(flush: flush)
            instance
        }
    }

    static mapping = {
        table 'twit'
        version true
    }
}
