package com.wuzz.demo;

import org.apache.xpath.XPathAPI;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/12
 * Time: 15:57
 * Description:
 */
public class Test {

    public static void main(String[] args) throws IOException, SAXException, TransformerException {
        Document doc = null;
        DOMParser parser = new DOMParser();
        parser.parse(new InputSource(new FileInputStream(new File("E:/test.html"))));
        // 获取document对象
        doc = parser.getDocument();

        NodeList nodeList = doc.getElementsByTagName("a");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element e = (Element) nodeList.item(i);
            System.out.print(e.getAttribute("href") + "\t");
            System.out.println(e.getTextContent());
        }

    }
}
