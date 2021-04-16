/*
 * @File PrankGenerator.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.prank;

import ch.heigvd.res.lab4.config.ConfigurationManager;
import ch.heigvd.res.lab4.prank.mail.Group;
import ch.heigvd.res.lab4.prank.mail.Mail;
import ch.heigvd.res.lab4.prank.mail.Message;
import ch.heigvd.res.lab4.prank.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrankGenerator {
   private ConfigurationManager configurationManager;

   public PrankGenerator(ConfigurationManager configurationManager) {
      this.configurationManager = configurationManager;
   }

   public ArrayList<Mail> generatePranks(){
      // TODO: 16 avr. 2021 add prank generation getting the victims and messages from the configurationManager
      ArrayList<Mail> mails = new ArrayList<>();
      Group bcc = new Group();
      Person personBCC = new Person(configurationManager.getWitnessToCC());
      int nbInGroup = configurationManager.getVictims().size() / configurationManager.getNumberOfGroups();
      if (nbInGroup >= 3) {
         Collections.shuffle(configurationManager.getVictims());
         Collections.shuffle(configurationManager.getMessages());
         for (int i = 0; i < nbInGroup; ++i) {
            if (i != nbInGroup - 1) {
               List<String> victims = configurationManager.getVictims().subList(i * nbInGroup, nbInGroup - 1 + i * nbInGroup);
               Person from = new Person(victims.get(0));
               Group to = new Group();
               for (int j = 1; i < victims.size(); ++i) {
                  to.addMember(new Person(victims.get(j)));
               }
               mails.add(new Mail(from, to, null, null, configurationManager.getMessages().get(i)));
            } else {
               List<String> victims = configurationManager.getVictims().subList(i * nbInGroup, configurationManager.getVictims().size() - 1);
               Person from = new Person(victims.get(0));
               Group to = new Group();
               for (int j = 1; i < victims.size(); ++i) {
                  to.addMember(new Person(victims.get(j)));
               }
               mails.add(new Mail(from, to, null, bcc, configurationManager.getMessages().get(i)));
            }
         }
      }
      return mails;
   }
}
