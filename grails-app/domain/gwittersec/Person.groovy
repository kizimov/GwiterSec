package gwittersec

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Person implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email
    String fullName
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<Role> getAuthorities() {
        (PersonRole.findAllByPerson(this) as List<PersonRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        email nullable: false, blank: false, unique: true
        fullName nullable: false, blank: false

    }

    static mapping = {
	    password column: '`password`'
    }
}
