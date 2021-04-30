/*
 * @File ConfigurationManager.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.config;

import ch.heigvd.res.lab4.mail.Message;
import lombok.Getter;

import java.io.*;
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
         //Get properties
         String rootPath = new File(".").getCanonicalPath() + "/config";
         String appConfigPath = rootPath + "/config.properties";

         Properties appProps = new Properties();
         appProps.load(new FileInputStream(appConfigPath));

         smtpServerAddress = appProps.getProperty(Options.SERVER_ADDRESS.getParamName());
         smtpServerPort = Integer.parseInt(appProps.getProperty(Options.SERVER_PORT.getParamName()));
         numberOfGroups = Integer.parseInt(appProps.getProperty(Options.NUMBER_OF_GROUPS.getParamName()));
         witnessToCC = appProps.getProperty(Options.WITNESS_TO_CC.getParamName());

         //Get victims
         BufferedReader reader = new BufferedReader(new FileReader(rootPath + "/victims.txt"));
         String victim = reader.readLine();
         victims = new ArrayList<>();
         while (victim != null) {
            victims.add(victim);
            victim = reader.readLine();
         }

         //Get messages
         reader = new BufferedReader(new FileReader(rootPath + "/messages.txt"));
         loadMessages(reader);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void loadMessages(BufferedReader reader) throws IOException {
      messages = new ArrayList<>();
      String subject = reader.readLine();
      do {
         String body = "";
         String line = reader.readLine();
         while (!line.equals("==")) {
            body += line;
            line = reader.readLine();
         }
         Message m = new Message(subject, body);
         messages.add(m);
         subject = reader.readLine();
      } while (subject != null && !subject.equals(""));
   }
}
