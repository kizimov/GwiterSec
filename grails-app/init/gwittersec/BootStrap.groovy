package gwittersec

import gwittersec.*
import org.springframework.security.core.userdetails.UsernameNotFoundException

class BootStrap {

    def init = { servletContext ->
        // def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
        // def person = Person.findOrSaveWhere(username: 'admin', password: 'password', email: 'admin@example.com', fullName: 'Admin Devopsovich')

        //can CRUD users\posts
        // def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')

        //can create posts, do subscription, see list of registered users, read posts of specified user
        def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')


        def adminUser = new Person(username: 'admin', password: 'password').save(failOnError: false)
        PersonRole.create(adminUser, adminRole, true)

        def createPerson = { String username, String password ->
            def person = new Person(username: username, password: password).save(failOnError: false)
            PersonRole.create(person, userRole, true)
            }

        createPerson 'example', 'password'
        createPerson 'user', 'user'
        createPerson 'Jeff@gmail.com', 'pass1234'
        createPerson 'Lari777@mail.ru', 'Loki'
        createPerson 'Graeme_jr@i.ua', 'Dog_Rose'


        def createTwit = {String header, String content, BigInteger person_id ->
            Twit.create(header, content, Person.findById(person_id), true)
        }

        createTwit 'twit#1', 'some text for twit 1', 1
        createTwit 'twit#1', 'some text for twit 1', 2
        createTwit 'twit#1', 'some text for twit 1', 3
        createTwit 'twit#2', 'some text for twit 2', 3
        createTwit 'twit#3', 'some text for twit 3', 3
        createTwit 'twit#4', 'some text for twit 4', 4
        createTwit 'twit#2', 'some text for twit 2', 1


    }
    def destroy = {
    }
}