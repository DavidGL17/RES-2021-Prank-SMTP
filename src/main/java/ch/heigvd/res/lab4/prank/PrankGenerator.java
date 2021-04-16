/*
 * @File PrankGenerator.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.prank;

import ch.heigvd.res.lab4.config.ConfigurationManager;
import ch.heigvd.res.lab4.prank.mail.Mail;

import java.util.ArrayList;

public class PrankGenerator {
   private ConfigurationManager configurationManager;

   public PrankGenerator(ConfigurationManager configurationManager) {
      this.configurationManager = configurationManager;
   }

   public ArrayList<Mail> generatePranks(){
      // TODO: 16 avr. 2021 add prank generation getting the victims and messages from the configurationManager
      return null;
   }
}
