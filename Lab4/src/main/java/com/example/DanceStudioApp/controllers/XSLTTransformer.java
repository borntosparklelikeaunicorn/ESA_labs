package com.example.DanceStudioApp.controllers;

import com.example.DanceStudioApp.models.DanceStudio;
import com.example.DanceStudioApp.models.DanceClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.List;

public class XSLTTransformer {

    public static String transform2XLT(List<?> list, String xsltPath)  {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        StringWriter writer = new StringWriter();
        try {
            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTemplates(new StreamSource("src\\main\\resources\\templates\\xslt\\" + xsltPath))
                    .newTransformer();

            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(xmlMapper.writeValueAsBytes(list))),
                    new StreamResult(writer));
        } catch (TransformerException | JsonProcessingException e){
            throw new RuntimeException(e);
        }


        return writer.toString();
    }

    public static String transform2XLT(List<DanceStudio> list, String xsltPath, long id)  {
        list.removeIf(DanceStudio-> DanceStudio.getId() != id);
        System.out.println(list);
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        StringWriter writer = new StringWriter();
        try {
            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTemplates(new StreamSource("src\\main\\resources\\templates\\xsl\\" + xsltPath))
                    .newTransformer();

            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(xmlMapper.writeValueAsBytes(list))),
                    new StreamResult(writer));
        } catch (TransformerException | JsonProcessingException e){
            throw new RuntimeException(e);
        }
        return writer.toString();
    }


    public static String transform2XLT_class(List<DanceClass> list, String xsltPath, long id)  {
        list.removeIf(DanceStudio-> DanceStudio.getStudio_id() != id);
        System.out.println(list);
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        StringWriter writer = new StringWriter();
        try {
            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTemplates(new StreamSource("src\\main\\resources\\templates\\xsl\\" + xsltPath))
                    .newTransformer();

            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(xmlMapper.writeValueAsBytes(list))),
                    new StreamResult(writer));
        } catch (TransformerException | JsonProcessingException e){
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

}