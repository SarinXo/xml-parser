package org.example.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XmlParser<T> implements AutoCloseable{

    private final InputStream inputStream;
    private final Class<T> clazz;
    private final String xmlAttribute;

    private XMLEventReader eventReader;
    private ObjectMapper mapper;

    public XmlParser(InputStream inputStream,
                     String xmlAttribute,
                     Class<T> clazz) {
        this.inputStream = inputStream;
        this.xmlAttribute = xmlAttribute;
        this.clazz = clazz;
        configure();
    }

    private void configure() {
        mapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .addModule(new JavaTimeModule())
                .build();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            eventReader = factory.createXMLEventReader(inputStream);
        } catch (XMLStreamException e) {
            System.err.println("err with configure in XmlParser");
        }
    }

    /**
     * Берёт recordsCount записей или читает документ до конца и возвращает все оставшиеся.
     * Если документ был прочитан, то возвращает пустой массив.
     * @return ArrayList or Empty ArrayList
     */
    public List<T> getNextPart(int recordsCount) {
        List<T> valueList = new ArrayList<>();
        int count = 0;
        try {
            while (eventReader.hasNext() && count < recordsCount) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase(xmlAttribute)) {
                            Map<String, String> map = attributesToMap(startElement.getAttributes());
                            T value = mapper.convertValue(map, clazz);
                            valueList.add(value);
                            count++;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            System.out.println("err with tacking part");
        }
        return valueList;
    }

    private static Map<String, String> attributesToMap(Iterator<Attribute> attributes) {
        Map<String, String> map = new HashMap<>();
        while (attributes.hasNext()) {
            Attribute attr = attributes.next();
            map.put(attr.getName().toString(), attr.getValue());
        }
        return map;
    }

    public Boolean hasNext() {
        return eventReader != null && eventReader.hasNext();
    }
    @Override
    public void close() throws Exception {
        eventReader.close();
        // После использования останется много мусора в виде бесполезных листов. Почему бы и не убрать?
        //System.gc();
    }
}
