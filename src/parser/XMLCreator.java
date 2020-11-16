package parser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLCreator {
    private static Node putFinalNode(Document doc, String tag, String value){
        Element node = doc.createElement(tag);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    private static Node putNode(Document doc, String tag, String[] attr, String[] attr_values) {
        Element element = doc.createElement(tag);
        for (int i = 0; i < attr.length; i++){
            element.setAttribute(attr[i], attr_values[i]);
        }
        return element;
    }
    public static void create(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            Node middle_node = putNode(doc, "books", new String[]{"count"}, new String[]{"3"});

            Node book_node1 = putNode(doc, "book", new String[]{"pages", "price"}, new String[]{"120", "540"});
            Node title1 = putFinalNode(doc, "title", "Alice in Wonderland");
            Node author1 = putFinalNode(doc, "author", "L. Karrol");
            book_node1.appendChild(title1);
            book_node1.appendChild(author1);

            Node book_node2 = putNode(doc, "book", new String[]{"pages", "price"}, new String[]{"200", "500"});
            Node title2 = putFinalNode(doc, "title", "Programming in C");
            Node author2 = putFinalNode(doc, "author", "D. Ritchie");
            book_node2.appendChild(title2);
            book_node2.appendChild(author2);

            Node book_node3 = putNode(doc, "book", new String[]{"pages", "price"}, new String[]{"400", "800"});
            Node title3 = putFinalNode(doc, "title", "Programming in Java");
            Node author3 = putFinalNode(doc, "author", "S. Someone");
            book_node3.appendChild(title3);
            book_node3.appendChild(author3);


            middle_node.appendChild(book_node1);
            middle_node.appendChild(book_node2);
            middle_node.appendChild(book_node3);

            rootElement.appendChild(middle_node);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);
            System.out.println(console);
            StreamResult file = new StreamResult(new File("C:\\Users\\1\\Desktop\\Универ\\3 курс\\СиТАиРИС\\Laba 4\\Parser\\file.xml"));

            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
