package parser;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NullPointerException, ParserConfigurationException, SAXException, IOException {

        String file_name = "file.xml";
        XMLCreator.create(); // create XML if you need it

        if (Check.check(file_name, "schema.xsd")) { // checking if this XML is valid according to XSD
            System.out.println("XML file is valid!");

            Scanner scanner = new Scanner(System.in);
            String tag_name;

            System.out.println("------- SAX -------");
            System.out.println("Input tag name: ");
            tag_name = scanner.nextLine();

            SimpleSAX sax_parser = new SimpleSAX(file_name, tag_name);
            sax_parser.start();

            System.out.println("------- DOM -------");
            SimpleDOM dom_parser = new SimpleDOM(file_name);
            dom_parser.parse();
        }
        else {
            System.out.println("XML file is invalid!");
        }

    }
}
