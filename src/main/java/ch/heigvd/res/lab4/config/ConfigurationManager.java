/*
 * @File ConfigurationManager.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.config;

import ch.heigvd.res.lab4.prank.mail.Message;
import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ConfigurationManager {
   @Getter private ArrayList<Message> messages;
   @Getter private ArrayList<String> victims;
   @Getter private String smtpServerAddress;
   @Getter private int smtpServerPort;
   @Getter private int numberOfGroups;
   @Getter private String witnessToCC;

   public ConfigurationManager() {
      try {
         String rootPath = new File(".").getAbsolutePath();
         String appConfigPath = rootPath + "app.properties";

         Properties appProps = new Properties();
         appProps.load(new FileInputStream(appConfigPath));

         smtpServerAddress = appProps.getProperty("smtpServerAddress");
         smtpServerPort = Integer.parseInt(appProps.getProperty("smtpServerPort"));
         numberOfGroups = Integer.parseInt(appProps.getProperty("numberOfGroups"));
         witnessToCC = appProps.getProperty("witnessToCC");

      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
