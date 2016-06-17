package parsers.export;

import org.json.JSONObject;
import org.json.XML;

/**
 * Created by Guardeec on 10.06.16.
 */
public class VizToXMLParser {
    public String parse(String object) {
        JSONObject json = new JSONObject(object);
        String xml = XML.toString(json);
        return xml;
    }
}
