package parser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SimpleSAX {
    private String  file_name;
    private String tag;
    private ArrayList<String> results;

    DefaultHandler handler;

    public SimpleSAX(String file_name, String tag_name)
    {
        this.file_name = file_name;
        this.tag = tag_name;
        results = new ArrayList();

        handler = new DefaultHandler() {
            boolean tagOn = false;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                tagOn = qName.equalsIgnoreCase(tag);
            }

            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
                if (tagOn) {
                    results.add(new String(ch, start, length));
                    tagOn = false;
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException
            {
                super.endElement(uri, localName, qName);
            }

            @Override
            public void startDocument() throws SAXException
            {
                System.out.println("Разбираю документ...");
            }
            @Override
            public void endDocument() throws SAXException
            {
                System.out.println("Разбор документа завершен.");
            }
        };
    }

    public ArrayList<String> start() {
        try {
            SAXParserFactory factory;
            factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(file_name, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
