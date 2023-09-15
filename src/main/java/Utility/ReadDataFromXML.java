package Utility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

//import net.bytebuddy.dynamic.scaffold.MethodGraph.NodeList;

public class ReadDataFromXML {
    private static HashMap<String, String> TestCaseData = new HashMap<String, String>();

    public static void AddTestDataIntoTest(String env, String testClassName, String testCaseName) {
        try {
            ReadDataFromXML(env, testClassName, testCaseName);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ReadDataFromXML(String env, String testClassName, String testCaseName)
            throws ParserConfigurationException, SAXException, IOException {
        // TODO Auto-generated method stub
        String projectpath = System.getProperty("user.dir");
        String appName = "Portal";
        String configPropPath = projectpath + "\\TestData\\" + appName + "\\" + env + "\\" + testClassName + ".xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(configPropPath);


        NodeList testDataChildNodes = document.getElementsByTagName(testCaseName);
        Node parentNode = testDataChildNodes.item(0);
        NodeList childItems = parentNode.getChildNodes();
        String key = null;
        String keyValue = null;
        for (int i = 0; i < childItems.getLength(); i++) {
            if (childItems.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element eachChildItemNode = (Element) childItems.item(i);
                key = eachChildItemNode.getNodeName().trim();  //--------------
                keyValue = eachChildItemNode.getTextContent();
                TestCaseData.put(key, keyValue);


            } else {
                Node eachChildItemNode = (Node) childItems.item(i);
                if (!(eachChildItemNode.getNodeName() == "#text")) {
                    key = eachChildItemNode.getNodeName();
                    keyValue = eachChildItemNode.getTextContent();
                    TestCaseData.put(key, keyValue);
                }
            }
        }


    }

    public static String ConfigDataValueperkey(String key) {
        return TestCaseData.containsKey(key) ? TestCaseData.get(key) : null;
    }
}
