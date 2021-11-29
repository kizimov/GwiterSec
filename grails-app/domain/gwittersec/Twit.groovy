package gwittersec

import grails.validation.Validateable
import groovy.transform.ToString

@ToString(includes = ['header', 'content'], includeNames = true)
class Twit implements Validateable{

    String header
    String content

    static belongsTo = [person: Person]

    static constraints = {
        header blank: false, nullable: false
        content blank: false, nullable: false
    }

    static mapping = {
        table 'twit'
        version true
    }
}