package parser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleSAX {
    private String  file_name;
    private String  tag_name;

    DefaultHandler handler;

    public SimpleSAX(String file_name, String tag_name)
    {
        this.file_name = file_name;
        this.tag_name = tag_name;

        handler = new DefaultHandler() {
            boolean tagOn = false;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                tagOn = (qName.equalsIgnoreCase(tag_name));
                System.out.println("<" + qName + ">");
            }

            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
                if (tagOn) {
                    System.out.println("\tText: " + new String(ch, start, length));
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
                System.out.println("Начало разбора документа: ");
            }
            @Override
            public void endDocument() throws SAXException
            {
                System.out.println("Разбор документа завершен.");
            }
        };
    }
    public void start(){
        try {
            SAXParserFactory factory;
            factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(file_name, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
