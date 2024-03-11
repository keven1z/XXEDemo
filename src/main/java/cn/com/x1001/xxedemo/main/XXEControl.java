package cn.com.x1001.xxedemo.main;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;


@Controller
@RequestMapping("/xxe")
public class XXEControl {

    /**
     * DocumentBuilderFactory
     */
    @RequestMapping(value = "/xxe1", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe1(HttpServletRequest request) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String FEATURE = null;
        FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
        dbf.setFeature(FEATURE, true);
        FEATURE = "http://xml.org/sax/features/external-general-entities";
        dbf.setFeature(FEATURE, false);
        FEATURE = "http://xml.org/sax/features/external-parameter-entities";
        dbf.setFeature(FEATURE, false);
        FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
        dbf.setFeature(FEATURE, false);
        dbf.setXIncludeAware(false);

//        dbf.setExpandEntityReferences无法防止xxe
        dbf.setExpandEntityReferences(false);
        try {
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            documentBuilder.parse(request.getInputStream());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
    /**
     * SAXBuilder
     */
    @RequestMapping(value = "/xxe2", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe2(HttpServletRequest request)  {
        SAXBuilder sb = new SAXBuilder();
        sb.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        sb.setFeature("http://xml.org/sax/features/external-general-entities", false);
        sb.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        sb.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        try {
            Document doc = sb.build(request.getInputStream());
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * SAXParserFactory
     */
    @RequestMapping(value = "/xxe3", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe3(HttpServletRequest request) throws SAXNotSupportedException, SAXNotRecognizedException, ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        try {
            SAXParser parser = spf.newSAXParser();
            parser.parse(request.getInputStream(), (HandlerBase) null);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * SAXReader
     */
    @RequestMapping(value = "/xxe4", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe4(HttpServletRequest request)  {
        SAXBuilder sb = new SAXBuilder();
        sb.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        sb.setFeature("http://xml.org/sax/features/external-general-entities", false);
        sb.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        sb.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        try {
            Document doc = sb.build(request.getInputStream());
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * SAXTransformerFactory
     */
    @RequestMapping(value = "/xxe5", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe5(HttpServletRequest request)  {
        StreamSource source = null;
        try {
            source = new StreamSource(request.getInputStream());
            SAXTransformerFactory sf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        sf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        sf.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            TransformerHandler transformerHandler = sf.newTransformerHandler(source);
            Transformer transformer = transformerHandler.getTransformer();
        } catch (IOException | TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }
    /**
     * SchemaFactory
     */
    @RequestMapping(value = "/xxe6", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe6(HttpServletRequest request) throws SAXNotSupportedException, SAXNotRecognizedException {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        try {
            StreamSource source = new StreamSource(request.getInputStream());
            Schema schema = factory.newSchema(source);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * TransformerFactory
     */
    @RequestMapping(value = "/xxe7", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe7(HttpServletRequest request)  {
        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        try {
            StreamSource source = new StreamSource(request.getInputStream());
            tf.newTransformer().transform(source, new DOMResult());
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * SchemaFactory
     */
    @RequestMapping(value = "/xxe8", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe8(HttpServletRequest request)  {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = null;
        try {
            schema = factory.newSchema();
            Validator validator = schema.newValidator();
        validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            StreamSource source = new StreamSource(request.getInputStream());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * XMLInputFactory
     */
    @RequestMapping(value = "/xxe9", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe9(HttpServletRequest request)  {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        XMLStreamReader reader = null;
        try {
            reader = xmlInputFactory.createXMLStreamReader(request.getInputStream());
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
        try {
            assert reader != null;
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {//开始节点
                    System.out.print(reader.getName());
                } else if (type == XMLStreamConstants.CHARACTERS) {//表示事件字符
                    System.out.println("type" + type);
                } else if (type == XMLStreamConstants.END_ELEMENT) {//结束节点
                    System.out.println(reader.getName());
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * XMLReader
     */
    @RequestMapping(value = "/xxe10", method = RequestMethod.POST,produces = "text/html")
    @ResponseBody
    public void xxe10(HttpServletRequest request)  {
        XMLReader reader = null;
        try {
            reader = XMLReaderFactory.createXMLReader();
        reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
        reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            reader.parse(new InputSource(request.getInputStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }

}

