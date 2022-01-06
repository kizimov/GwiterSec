package gwittersec

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


class PersonController {

    PersonService personService
    CustomPersonService customPersonService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ANONYMOUS'])
    def index() {
        respond customPersonService.list()
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ANONYMOUS'])
    def show(Long id) {
        respond customPersonService.show(id)
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        println(params.toString())
        respond customPersonService.list()
    }

    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        respond customPersonService.show(id)
    }

    @Secured(['ROLE_ADMIN'])
    def addSubscriptions(Person person) {
        respond customPersonService.addSubscriptions(person)
    }

    @Secured(['ROLE_ADMIN'])
    def save(Person person) {
        if (person == null) {
            notFound()
            return
        }
        try {
            personService.save(person)
        } catch (ValidationException e) {
            respond person.errors, view:'create'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'person.label', default: 'Person'), person.id])
                redirect person
            }
            '*' { respond person, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update(Person person) {
        if (person == null) {
            notFound()
            return
        }

        try {
            personService.save(person)
        } catch (ValidationException e) {
            respond person.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'person.label', default: 'Person'), person.id])
                redirect person
            }
            '*'{ respond person, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        customPersonService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'person.label', default: 'Person'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ANONYMOUS'])
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}