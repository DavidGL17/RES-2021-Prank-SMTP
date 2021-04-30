/*
 * @File PrankGenerator.java
 * @Authors : David González León, Jade Gröli
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.prank;

import ch.heigvd.res.lab4.config.ConfigurationManager;
import ch.heigvd.res.lab4.prank.mail.Group;
import ch.heigvd.res.lab4.prank.mail.Mail;
import ch.heigvd.res.lab4.prank.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrankGenerator {
   private ConfigurationManager configurationManager;

   public PrankGenerator(ConfigurationManager configurationManager) {
      this.configurationManager = configurationManager;
   }

   /**
    * @brief this method creates several groups of person with their email addresses from the victims.txt files.
    * One person is designate as the sender and all the other are receivers. A message from the messages.txt file is
    * chosen and send to all the receivers from the sender. The composition of the groups and the selected message are
    * randomly chosen. 
    * @return the list of all the pranked mails which has been sent
    */
   public ArrayList<Mail> generatePranks() {
      // TODO: 16 avr. 2021 add prank generation getting the victims and messages from the configurationManager
      ArrayList<Mail> mails = new ArrayList<>();
      Group bcc = new Group();
      bcc.addMember(new Person(configurationManager.getWitnessToCC()));
      int nbInGroup = configurationManager.getVictims().size() / configurationManager.getNumberOfGroups();
      if (nbInGroup >= 3) {
         Collections.shuffle(configurationManager.getVictims());
         for (int i = 0; i < configurationManager.getNumberOfGroups(); ++i) {
            Collections.shuffle(configurationManager
                                        .getMessages()); // TODO: 16 avr. 2021 faire vérife pour pas shuffle a chaque
            // fois
            if (i != configurationManager.getNumberOfGroups() - 1) {
               List<String> victims =
                       configurationManager.getVictims().subList(i * nbInGroup, nbInGroup + i * nbInGroup);
               Person from = new Person(victims.get(0));
               Group to = new Group();
               for (int j = 1; j < victims.size(); ++j) {
                  to.addMember(new Person(victims.get(j)));
               }
               mails.add(new Mail(from, to, null, bcc, configurationManager.getMessages().get(0)));
            } else {
               List<String> victims = configurationManager.getVictims().subList(i * nbInGroup,
                                                                                configurationManager.getVictims()
                                                                                                    .size());
               Person from = new Person(victims.get(0));
               Group to = new Group();
               for (int j = 1; j < victims.size(); ++j) {
                  to.addMember(new Person(victims.get(j)));
               }
               mails.add(new Mail(from, to, null, bcc, configurationManager.getMessages().get(0)));
            }
         }
      }
      return mails;
   }
}
