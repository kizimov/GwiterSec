package gwittersec

import grails.gorm.transactions.Transactional

@Transactional
class CustomTwitService {


    List<Twit> list(){
        Twit.findAll()
    }

    Twit show(Long id){
        Twit.get(id)
    }

    Twit save(Twit twit){
        twit.save()
        }

    void delete(Long id){
        Twit.get(id).delete()
    }
}
