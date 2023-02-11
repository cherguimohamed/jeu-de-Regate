/*package fr.ensicaen.genielogiciel.mvp.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;

public class ProxyWind {

    public void recup(String url){
        try (BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fos = new FileOutputStream("./wind.json")) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = bis.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    public static void main(String[] args) {
        String url = "https://www.prevision-meteo.ch/services/json/lat=49.182lng=-0.37";
    }
}
*/