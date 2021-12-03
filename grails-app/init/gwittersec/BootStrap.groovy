package gwittersec

import gwittersec.*
import org.springframework.security.core.userdetails.UsernameNotFoundException

class BootStrap {

    def init = { servletContext ->
        // def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
        // def person = Person.findOrSaveWhere(username: 'admin', password: 'password', email: 'admin@example.com', fullName: 'Admin Devopsovich')

        //can CRUD users\posts
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        // def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')

        //default user role
        //As anonymous user I want to be able to see list of registered users
        //As anonymous user I want to be able to read posts of specified user
        def anonymRole = new Role('ROLE_ANONYM').save()

        //can create posts, do subscription, see list of registered users, read posts of specified user
        def userRole = new Role('ROLE_USER').save()


        def adminUser = new Person(username: 'admin', password: 'password').save(failOnError: true)
        PersonRole.create(adminUser, adminRole, true)

        def createPerson = { String username, String password ->
            def person = new Person(username: username, password: password).save(failOnError: true)
            PersonRole.create(person, userRole, true)
            }

        createPerson 'admin@example.com', 'password'
        createPerson 'Jeff@gmail.com', 'pass1234'
        createPerson 'Lari777@mail.ru', 'Loki'
        createPerson 'Graeme_jr@i.ua', 'Dog_Rose'

        def anonymUser = new Person(username: 'anonym', password: 'anonym').save(failOnError: true)
        PersonRole.create(anonymUser, anonymRole, true)





        //     SpringSecurityUtils.clientRegisterFilter("securityCorsFilter", SecurityFilterPosition.SECURITY_CONTEXT_FILTER.order - 1)
        //
        //         //can CRUD users\posts
        //         def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        //
        //         //can delete or change posts
        //         def adminModerate = new Role(authority: 'ROLE_MODERATE').save()
        //
        //         //default user role
        //         //can only read post, can't see registered users
        //         def readOnly = new Role(authority: 'ROLE_READONLY').save()
        //
        //         //change to this role after admin review
        //         //can create posts, do subscription, change itself info, see hidden posts
        //         def userRole = new Role(authority: 'ROLE_USER').save()
        //
        //         def adminUser = new User(username: 'admin', password: 'admin').save()
        //
        //         def user1 = new User(username: 'user1', password: 'pass1').save()//user
        //         def user2 = new User(username: 'user2', password: 'pass2').save()//read only
        //         def user3 = new User(username: 'user3', password: 'pass3').save()//user
        //
        //         UserRole.create(adminUser, adminRole, true)
        //         UserRole.create(user1, userRole, true)
        //         UserRole.create(user2, readOnly, true)
        //         UserRole.create(user3, userRole, true)
        //
        //         try {
        //             println("do post bootstrap!")
        //             Post.findOrSaveWhere(header: 'New post 1', content: 'some content for post', user: user2)
        //             Post.findOrSaveWhere(header: 'New post 2', content: 'some content for post', user: user3)
        //             Post.findOrSaveWhere(header: 'New post 3', content: 'some content for post', user: user2)
        //             Post.findOrSaveWhere(header: 'New post 4', content: 'some content for post', user: user3)
        //             Post.findOrSaveWhere(header: 'New post 5', content: 'some content for post', user: user3)
        // //            new Post(header: 'New post 1', content: 'some content for post', user: user2).save flush: true
        // //            new Post(header: 'New post 2', content: 'some content for post', user: user3).save flush: true
        // //            new Post(header: 'New post 3', content: 'some content for post', user: user2).save flush: true
        // //            new Post(header: 'New post 4', content: 'some content for post', user: user3).save flush: true
        // //            new Post(header: 'New post 5', content: 'some content for post', user: user3).save flush: true
        //         }
        //         catch (Exception e){
        //             e.printStackTrace()
        //         }


    }
    def destroy = {
    }
}