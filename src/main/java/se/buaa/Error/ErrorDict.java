package se.buaa.Error;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ErrorDict {
    private static ErrorDict instance = null;

    static {
        try {
            instance = new ErrorDict();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final Map<String, ErrorValue> dict;

    public static ErrorDict getInstance() {
        return instance;
    }

    public ErrorDict() throws Exception {
        dict = new HashMap<>();
        load();
    }

    public void load() throws Exception{
        File xmlFile;

        if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            xmlFile = new File("src\\main\\resources\\ErrorConfig.xml");
        }
        else xmlFile = new File("src/main/resources/ErrorConfig.xml");

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        Element root = doc.getDocumentElement();

        NodeList nodeList = doc.getElementsByTagName("error");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            ErrorValue errorValue = new ErrorValue();

            String errorCode = getErrorCode(node, root);
            String errorMsg = node.getAttributes().getNamedItem("msg").getNodeValue();
            String className = node.getAttributes().getNamedItem("classname").getNodeValue();
            String shortCode = node.getAttributes().getNamedItem("code").getNodeValue();

            errorValue.setClassName(className);
            errorValue.setCode(errorCode);
            errorValue.setMsg(errorMsg);
            errorValue.setShortCode(shortCode);
            dict.put(errorCode, errorValue);
        }
    }

    private String getErrorCode(Node node, Node root) {
        Node cur = node;
        StringBuilder res = new StringBuilder();
        while (cur != root) {
            res.append(cur.getAttributes().getNamedItem("code").getNodeValue());
            cur = cur.getParentNode();
        }
        return res.toString();
    }

    public ErrorValue getValue(String code) {
        return instance.dict.get(code);
    }

    public void show() {
        System.out.println(dict);
    }

//    public static void main(String[] args) throws Exception {
//        ErrorDict dict = new ErrorDict();
//        System.out.println(System.getProperty("os.name"));
//        dict.load();
//        dict.show();
//    }
}
