package gwittersec

import grails.gorm.services.Service

@Service(Twit)
interface TwitService {

    Twit get(Serializable id)

    List<Twit> list(Map args)

    Long count()

    void delete(Serializable id)

    Twit save(Twit twit)

}