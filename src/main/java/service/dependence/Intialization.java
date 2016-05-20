package service.dependence;

import data.RawDataImpl;
import data.rawdata.csvdata.CsvPattern;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ApplicationContextContainer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Guardeec on 03.05.16.
 */
public class Intialization implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("springConfig.xml");
        ApplicationContextContainer.getInstance().setFactory(classPathXmlApplicationContext);

        RawDataImpl csvStorage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        CsvPattern csvPattern = new CsvPattern("testPattern");
        csvPattern.addCsvKey(CsvPattern.PossibleNames.linkChannelCapacity, 2);
        csvPattern.addCsvKey(CsvPattern.PossibleNames.linkChannelDamageCoast, 3);
        csvPattern.addCsvKey(CsvPattern.PossibleNames.linkChannelLoad, 4);
        csvPattern.addCsvKey(CsvPattern.PossibleNames.linkChannelValue, 5);
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostHostIp, 6);
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostFqdn, 7);
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostInformationValue, 8);
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostLoad, 9);
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostPossibleDamage, 10);
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostType, 11);
        csvStorage.setPattern(csvPattern);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
