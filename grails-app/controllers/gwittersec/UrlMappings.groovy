package gwittersec

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view: '/index')
        "500"(view:'/error')
        "404"(view:'/notFound')

        "/index"(controller: 'index')

        "/login"(controller: 'login')
        "/login/auth"(controller: 'login', action: 'auth')

        "/twit"(controller: 'twit', action: 'index')
        "/twit/$id"(controller: 'twit', action: 'show')
        "/twit/$id"(controller: 'twit', action: 'create')

    }
}
