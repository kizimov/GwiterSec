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

        //default user role
        //As anonymous user I want to be able to see list of registered users
        //As anonymous user I want to be able to read posts of specified user
        def anonymRole = Role.findOrSaveWhere(authority: 'ROLE_ANONYM')

        //can create posts, do subscription, see list of registered users, read posts of specified user
        def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')


        def adminUser = new Person(username: 'admin', password: 'password').save(failOnError: false)
        PersonRole.create(adminUser, adminRole, true)

        def createPerson = { String username, String password ->
            def person = new Person(username: username, password: password).save(failOnError: false)
            PersonRole.create(person, userRole, true)
            }

        createPerson 'admin@example.com', 'password'
        createPerson 'Jeff@gmail.com', 'pass1234'
        createPerson 'Lari777@mail.ru', 'Loki'
        createPerson 'Graeme_jr@i.ua', 'Dog_Rose'

        def anonymUser = new Person(username: 'anonym', password: 'anonym').save(failOnError: false)
        PersonRole.create(anonymUser, anonymRole, true)

    }
    def destroy = {
    }
}