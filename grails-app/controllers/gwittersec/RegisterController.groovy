package gwittersec

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import gwittersec.Person
import gwittersec.Role
import gwittersec.PersonRole

@Transactional
@Secured('ROLE_ANONYMOUS')
class RegisterController {

    static allowedMethods = [register: "POST"]

    def index() { }

    def register() {
        if(!params.password.equals(params.repassword)) {
            flash.message = "Password and Re-Password not match"
            redirect action: "index"
            return
        } else {
            try {
                def person = Person.findByUsername(params.username)?: new Person(username: params.username, password: params.password, fullname: params.fullname).save()
                def role = Role.get(params.role.id)
                if(person && role) {
                    PersonRole.create person, role

                    PersonRole.withSession {
                        it.flush()
                        it.clear()
                    }

                    flash.message = "You have registered successfully. Please login."
                    redirect controller: "login", action: "auth"
                } else {
                    flash.message = "Register failed"
                    render view: "index"
                    return
                }
            } catch (ValidationException e) {
                flash.message = "Register Failed"
                redirect action: "index"
                return
            }
        }
    }
}
