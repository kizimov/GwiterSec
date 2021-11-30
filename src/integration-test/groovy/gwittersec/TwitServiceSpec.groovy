package gwittersec

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TwitServiceSpec extends Specification {

    TwitService twitService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Twit(...).save(flush: true, failOnError: true)
        //new Twit(...).save(flush: true, failOnError: true)
        //Twit twit = new Twit(...).save(flush: true, failOnError: true)
        //new Twit(...).save(flush: true, failOnError: true)
        //new Twit(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //twit.id
    }

    void "test get"() {
        setupData()

        expect:
        twitService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Twit> twitList = twitService.list(max: 2, offset: 2)

        then:
        twitList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        twitService.count() == 5
    }

    void "test delete"() {
        Long twitId = setupData()

        expect:
        twitService.count() == 5

        when:
        twitService.delete(twitId)
        sessionFactory.currentSession.flush()

        then:
        twitService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Twit twit = new Twit()
        twitService.save(twit)

        then:
        twit.id != null
    }
}
