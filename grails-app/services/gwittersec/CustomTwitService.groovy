package gwittersec

import com.sun.xml.bind.v2.model.core.ID
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
