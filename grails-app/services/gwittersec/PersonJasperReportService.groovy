package gwittersec

import grails.gorm.transactions.Transactional
import grails.plugins.jasper.JasperReportDef
import grails.plugins.jasper.JasperService

@Transactional
class PersonJasperReportService {
    private static final String REPORT_FORMAT = 'PDF'
    private static final String MY_REPORT_FILE ="MyReport"

    JasperService jasperService

    Byte[] buildReport(Person person){
        return createReportDefinition(person).contentStream.toByteArray()
    }


    JasperReportDef createReportDefinition(Person person) {
        Map<String, ?> params =
            [
                _name: MY_REPORT_FILE,
                _file: MY_REPORT_FILE,
                _format: REPORT_FORMAT
            ]
        jasperService.buildReportDefinition(params, null, [data: [toMap(person)]])
    }

    private Map toMap(Person person) {
        [
            person : [username: person.username, enabled: person.enabled, subscriptions: person.subscriptions]


        ]
    }
}
