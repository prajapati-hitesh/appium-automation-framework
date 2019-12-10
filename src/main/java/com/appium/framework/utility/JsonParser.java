package com.appium.framework.utility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {

    private JSONParser jsonParser;
    private JSONObject jsonObject;
    private Object obj;

    public JsonParser() {
        jsonParser = new JSONParser();
    }

    public void loadJsonFile(String jsonFilePath) throws IOException {
        try {

            // Parse JSON File
            this.obj = this.jsonParser.parse(new FileReader(jsonFilePath));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getNodeValue(String jsonNodeName) {
        this.jsonObject = (JSONObject) this.obj;
        // String nodeValue = this.jsonObject.get(jsonNodeName).toString().trim();
        String nodeValue = this.jsonObject.get(jsonNodeName).toString().trim();
        return nodeValue;
    }
}
