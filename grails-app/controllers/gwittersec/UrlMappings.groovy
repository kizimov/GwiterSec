package gwittersec

import java.lang.reflect.Method

class UrlMappings {

    static mappings = {
        // "/$controller/$action?/$id?(.$format)?"{
        //     constraints {
        //         // apply constraints here
        //     }
        // }

        "/"(view: '/index')
        "500"(view:'/error')
        "404"(view:'/notFound')

        "/index"(controller: 'index')

        "/login"(controller: 'login')
        "/login/auth"(controller: 'login', action: 'auth')

        "/logout/index"(controller: 'logout', action: 'index')

        "/twit"(controller: 'twit', action: 'index', method: 'GET')
        "/twit/index"(controller: 'twit', action: 'index', method: 'GET')
        "/twit/$id"(controller: 'twit', action: 'show', method: 'GET')
        "/twit/$id"(controller: 'twit', action: 'update', method: 'PUT')
        "/twit/$id"(controller: 'twit', action: 'delete', method: 'DELETE')
        "/twit/$id"(controller: 'twit', action: 'delete', method: 'POST')
        "/twit/edit/$id"(controller: 'twit', action: 'edit', method: 'GET')
        "/twit/create"(controller: 'twit', action: 'create', method: 'GET')
        "/twit/save"(controller: 'twit', action: 'save', method: 'POST')


        "/person"(controller: 'person', action: 'index', method: 'GET')
        "/person/$id"(controller: 'person', action: 'show', method: 'GET')
        "/person/edit/$id"(controller: 'person', action: 'edit', method: 'GET')
        "/person/$id"(controller: 'person', action: 'update', method: 'PUT')

    }
}
