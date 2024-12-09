package com.example.DanceStudioApp.controllers;

import com.example.DanceStudioApp.models.DanceClass;
import com.example.DanceStudioApp.models.DanceStudio;
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

    // Преобразование для списка объектов (например, DanceStudio или DanceClass)
    public static String transformToXSLT(List<?> list, String xsltPath) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        StringWriter writer = new StringWriter();
        try {
            // Сериализация списка в XML и вывод его в консоль для проверки
            String xmlString = xmlMapper.writeValueAsString(list);
            System.out.println("Serialized XML: " + xmlString); // Выводим XML в консоль для проверки

            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTemplates(new StreamSource("src\\main\\resources\\templates\\xslt\\" + xsltPath))
                    .newTransformer();

            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(xmlMapper.writeValueAsBytes(list))),
                    new StreamResult(writer));
        } catch (TransformerException | JsonProcessingException e) {
            throw new RuntimeException("Transformation failed", e);
        }
        return writer.toString();
    }

    // Преобразование для одиночного объекта (например, DanceStudio или DanceClass)
    public static String transformToXSLT(Object object, String xsltPath) {
        return transformToXSLT(List.of(object), xsltPath);
    }

    // Преобразование для списка DanceClass с фильтрацией по id
    public static String transformToXSLTForClass(List<DanceClass> classes, String xsltPath, long classId) {
        classes.removeIf(danceClass -> danceClass.getId() != classId);
        System.out.println("Filtered Classes: " + classes); // Печать отфильтрованного списка
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        StringWriter writer = new StringWriter();
        try {
            // Сериализация списка классов в XML и вывод его в консоль
            String xmlString = xmlMapper.writeValueAsString(classes);
            System.out.println("Serialized XML: " + xmlString); // Выводим XML в консоль для проверки

            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTemplates(new StreamSource("src\\main\\resources\\templates\\xslt\\" + xsltPath))
                    .newTransformer();

            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(xmlMapper.writeValueAsBytes(classes))),
                    new StreamResult(writer));
        } catch (TransformerException | JsonProcessingException e) {
            throw new RuntimeException("Transformation failed", e);
        }
        return writer.toString();
    }

    // Преобразование для списка DanceStudio с фильтрацией по id (если нужно)
    public static String transformToXSLTForStudio(List<DanceStudio> studios, String xsltPath, long studioId) {
        studios.removeIf(studio -> studio.getId() != studioId);
        System.out.println("Filtered Studios: " + studios); // Печать отфильтрованного списка
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        StringWriter writer = new StringWriter();
        try {
            // Сериализация списка студий в XML и вывод его в консоль
            String xmlString = xmlMapper.writeValueAsString(studios);
            System.out.println("Serialized XML: " + xmlString); // Выводим XML в консоль для проверки

            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTemplates(new StreamSource("src\\main\\resources\\templates\\xslt\\" + xsltPath))
                    .newTransformer();

            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(xmlMapper.writeValueAsBytes(studios))),
                    new StreamResult(writer));
        } catch (TransformerException | JsonProcessingException e) {
            throw new RuntimeException("Transformation failed", e);
        }
        return writer.toString();
    }
}
