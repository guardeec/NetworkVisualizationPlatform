package parsers.innerstructure;

import com.google.gson.Gson;
import data.networkdata.Network;
import org.json.JSONObject;
import org.json.XML;

/**
 * Created by Guardeec on 10.06.16.
 */
public class NetworkToXMLParser {
    public String parse(Network network) {
        Gson gson = new Gson();
        JSONObject json = new JSONObject(gson.toJson(network));
        String xml = XML.toString(json);
        return xml;
    }
}
