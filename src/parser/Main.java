package parser;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NullPointerException, ParserConfigurationException, SAXException, IOException {

        String file_name = "file.xml";
        // XMLCreator.create(); // create XML if you need it

        if (Check.check(file_name, "schema.xsd")) { // checking if this XML is valid according to XSD
            System.out.println("XML file is valid!");

            Scanner scanner = new Scanner(System.in);
            String tag_name;

            System.out.println("------- SAX -------");
            System.out.println("Input tag name: ");
            tag_name = scanner.nextLine();

            // As SAX-parser is used for information search in XML,
            // I decided to make class SimpleSAX return an array with resulted strings

            SimpleSAX sax_parser = new SimpleSAX(file_name, tag_name);
            ArrayList<String> results;
            results = sax_parser.start();

            System.out.println("----- Results: -----");
            if (!results.isEmpty()) {
                for (String s : results) {
                    System.out.println(s);
                }
            }
            else {
                System.out.println("No results.");
            }

            System.out.println("------- DOM -------");
            SimpleDOM dom_parser = new SimpleDOM(file_name);
            ArrayList<Book> books = dom_parser.parse();
            // Ideally, I would create classes DOMParser and BookParser, where BookParser would inherit from the 1st one

            BookShop bookShop = new BookShop(books);
            bookShop.showBooksData();
            System.out.println("\nTotal books cost: " + bookShop.countTotalCost()); // idk why i did it and don't ask me
        }
        else {
            System.out.println("XML file is invalid!");
        }

    }
}
