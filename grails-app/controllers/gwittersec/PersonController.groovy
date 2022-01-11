package gwittersec

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


class PersonController {

    PersonService personService
    CustomPersonService customPersonService
    PersonJasperReportService personJasperReportService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ANONYMOUS'])
    def index() {
        respond personService.list()
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
        println request.getUserPrincipal()
        respond customPersonService.show(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def subscribe(Long id) {
        Long followsId = Person.findByUsername(request.getUserPrincipal().getName()).getId()
        if (PersonUtils.checkFollowOneSelf(id, followsId)) {
            render " you can't subscribe to yourself!!!"
        } else {
            customPersonService.follow(id, followsId)
        }
        redirect(customPersonService.show(followsId))
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def report(Long id) {
        byte[] report = personJasperReportService.buildReport(Person.get(id))
        response.setContentType("application/pdf")
        response.contentLength = report.size()
        response.outputStream << report

    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ANONYMOUS'])
    def subscriptions(Long id) {
        render customPersonService.showSubscriptions(id)
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
