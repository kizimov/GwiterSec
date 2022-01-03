package gwittersec

import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class IndexController {

    def index() {
        render('hello')
    }
}
