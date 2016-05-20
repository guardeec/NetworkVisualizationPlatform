package parsers.maxpatrol;

import data.rawdata.maxpatroldata.MaxPatrolData;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Guardeec on 26.04.16.
 */
public class MaxpatrolParser {
    public MaxPatrolData getMaxPatrolData(File file) throws IOException {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        MaxPatrolData handler = new MaxPatrolData();
        try {
            SAXParser parser = parserFactor.newSAXParser();
            parser.parse(file, handler);
        }catch (ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
        return handler;
    }
}
