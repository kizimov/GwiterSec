package gwittersec

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

class TwitController {

    TwitService twitService
    CustomTwitService customTwitService


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ANONYMOUS'])
    def index() {
        respond customTwitService.list()
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ANONYMOUS'])
    def show(Long id) {
        respond customTwitService.show(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new Twit(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(Twit twit) {
        respond customTwitService.save(twit)

    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond customTwitService.show(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(Twit twit) {
        if (twit == null) {
            notFound()
        }

        try {
            customTwitService.save(twit)
        } catch (ValidationException e) {
            respond twit.errors, view:'edit'
            }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message',
                                        args: [message(code: 'twit.label', default: 'Twit'), twit.id])
                redirect twit
            }
            '*'{ respond twit, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        customTwitService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'twit.label', default: 'Twit'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'twit.label', default: 'Twit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
