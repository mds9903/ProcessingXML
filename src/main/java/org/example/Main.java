package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

// "Read an XML file from the disk and convert it into POJO
// Serialize the POJO to a XML file on the disk."
public class Main {
    public static void main(String[] args) {
        String filepath = "src/Car.xml";
        // "Read an XML file from the disk and convert it into POJO
        String xml = "";
        try {
            xml = readFile(filepath);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("IOException in readfile()");
        }
        Car car = deserializeCarObj(xml);
        System.out.println("Deserialized xml into a pojo:\t" + car);

        // Serialize the POJO to a XML file on the disk."
        Car newCar = new Car("blue", "semi truck");
        Car newCar2 = new Car("purple", "sedan");

        xml = serializeCarObj(newCar);
//        System.out.println();
        try {
            if (appendToFile(filepath, serializeCarObj(newCar)) && appendToFile(filepath, serializeCarObj(newCar2))) {
                System.out.println("pojo: " + newCar + " serialized into Car.xml file");
                System.out.println("pojo: " + newCar2 + " serialized into Car.xml file");
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("IOException in writefile()");
        }
    }

    private static Car deserializeCarObj(String xml) {
        // create pojo from xml content (string)
        XmlMapper xmlMapper = new XmlMapper();
        Car car = null;
        try {
            car = xmlMapper.readValue(xml, Car.class);
            return car;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    private static String serializeCarObj(Car car) {
        // from pojo to xml string
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(car);
//            System.out.println("serialized from car obj\n" + xml);
            return xml;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFile(String filepath) throws IOException {
        // from disk file to string
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
        StringBuilder content = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            content.append("\n" + line);
        }
        return content.toString();
    }

    private static boolean appendToFile(String filepath, String content) throws IOException {
        // from string to file in disk
        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
        bw.write(content);
        bw.close();
        return true;
    }
}