/*
 * @File Launcher.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4;

import ch.heigvd.res.lab4.config.ConfigurationManager;
import ch.heigvd.res.lab4.prank.PrankGenerator;
import ch.heigvd.res.lab4.prank.mail.Mail;

import java.util.ArrayList;

public class Launcher {
   public static void main(String[] args) {
      ConfigurationManager configurationManager = new ConfigurationManager();
      SMTPClient client = new SMTPClient(configurationManager.getSmtpServerAddress(), configurationManager.getSmtpServerPort());
      PrankGenerator generator = new PrankGenerator(configurationManager);
      ArrayList<Mail> mails = generator.generatePranks();
      for (Mail m : mails){
         client.sendMail(m);
      }
   }
}
