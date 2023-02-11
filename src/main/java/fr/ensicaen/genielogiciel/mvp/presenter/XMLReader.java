package fr.ensicaen.genielogiciel.mvp.presenter;


import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class XMLReader {
    Document _document;
    public XMLReader(File file) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            _document = builder.parse(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getFirstElementAttribute(String tag, String attr) {
        try{
            return ((Element)_document.getElementsByTagName(tag).item(0)).getAttribute(attr);
        } catch (Exception e) {
            throw e;
        }
    }

    public String getSpecifiedElementAttribute(String tag, String specifiedAttr, String specifiedValue, String attr) throws Exception {
        try{
            for (int i = 0; i < _document.getElementsByTagName(tag).getLength(); i++) {
                if (((Element) _document.getElementsByTagName(tag).item(i)).getAttribute(specifiedAttr).equals(specifiedValue))
                    return ((Element) _document.getElementsByTagName(tag).item(i)).getAttribute(attr);
            }
        } catch (Exception e) {
            throw e;
        }
        throw new Exception("NOT REACHEABLE");
    }
}
