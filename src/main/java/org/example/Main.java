package org.example;

import org.example.database.PostgresDdConnection;
import org.example.dto.XmlObject;
import org.example.parser.XmlParsable;
import org.example.parser.XmlParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class Main {

    private static final int RECORDS_PER_ITERATION = 5_000;

    public static void main(String[] args){
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/resources/xml/test.XML";
        File file = new File(currentDirectory, relativePath);

        try (InputStream in = new FileInputStream(file);
                XmlParsable<XmlObject> xmlParser = new XmlParser<>(in, "OBJECT", XmlObject.class);
                PostgresDdConnection postgresDdConnection = new PostgresDdConnection()) {
            List<XmlObject> list;
            while (xmlParser.hasNext()) {
                list = xmlParser.getNextPart(RECORDS_PER_ITERATION);
                postgresDdConnection.insertData(list);
                list.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}