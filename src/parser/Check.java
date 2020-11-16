package parser;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class Check {
    public static boolean check(String file_name, String schema_name){
        try {
            File xml = new File(file_name);
            File xsd = new File(schema_name);

            if (!xml.exists()) {
                System.out.println("Не найден XML." + file_name);
                return false;
            }

            if (!xsd.exists()) {
                System.out.println("Не найден XSD." + schema_name);
                return false;
            }

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(schema_name));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file_name));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
