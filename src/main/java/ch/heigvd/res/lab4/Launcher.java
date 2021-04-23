/*
 * @File Launcher.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4;

import ch.heigvd.res.lab4.config.ConfigurationManager;
import ch.heigvd.res.lab4.config.Options;
import ch.heigvd.res.lab4.prank.PrankGenerator;
import ch.heigvd.res.lab4.prank.mail.Mail;
import ch.heigvd.res.lab4.prank.mail.Message;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Explication : si il lance le jar avec -noui, l'ui se lance pas et on fait directement le programme normal, sinon on
 * lui présente les options actuelles, et lui propose de les changer et redémarrer, ou de lancer avec go, et de
 * stopper avec stop
 */

public class Launcher {
   private static SMTPClient client;
   private static PrankGenerator generator;
   private static ConfigurationManager configurationManager;

   public static void main(String[] args) {
      configurationManager = new ConfigurationManager();
      generator = new PrankGenerator(configurationManager);
      if (args.length == 1 && args[0].equals("-noui")) { //sans ui
         client = new SMTPClient(configurationManager.getSmtpServerAddress(), configurationManager.getSmtpServerPort());
         ArrayList<Mail> mails = generator.generatePranks();
         for (Mail m : mails) {
            client.sendMail(m);
         }
      } else {//avec ui
         System.out.println("Welcome to the prank generator. This application can allow you to send prank mails to " +
                            "predifined victims. Bellow are your current options : ");
         System.out
                 .println(Options.SERVER_ADDRESS.getParamName() + " : " + configurationManager.getSmtpServerAddress());
         System.out.println(Options.SERVER_PORT.getParamName() + " : " + configurationManager.getSmtpServerPort());
         System.out.println(Options.NUMBER_OF_GROUPS.getParamName() + " : " + configurationManager.getNumberOfGroups());
         System.out.println(Options.WITNESS_TO_CC.getParamName() + " : " + configurationManager.getWitnessToCC());
         System.out.println("The victims you have chosen : ");
         for (String s : configurationManager.getVictims()) {
            System.out.println("\t" + s);
         }
         System.out.println("And the messages you want to send for your pranks : ");
         for (Message m : configurationManager.getMessages()) {
            System.out.println(m.getSubject() + "\n" + m.getBody() + "\n");
         }
         while (true) {
            System.out.println(
                    "If you would like to send your pranks, please type \"go\". Otherwise, if you would " + "like " +
                    "to change your options, you can stop the program and change them, then restart it again." +
                    " To stop the program, please type \"stop\".");
            String answer = "";
            while (true) {
               Scanner scanner = new Scanner(System.in);
               answer = scanner.next();
               if (answer.equals("stop")) {
                  System.out.println("Thank you for using our app, bye!");
                  return;
               } else if (answer.equals("go")) {
                  break;
               } else {
                  System.out.println(
                          "Wrong input, please type \"go\" to send your mails, or \"stop\" to stop the program");
               }
            }
            System.out.println("Starting to send the mails...");
            client = new SMTPClient(configurationManager.getSmtpServerAddress(),
                                    configurationManager.getSmtpServerPort());
            ArrayList<Mail> mails = generator.generatePranks();
            int mailCounter = 1;
            for (Mail m : mails) {
               System.out.println("Sending mail n°" + mailCounter + "...");
               client.sendMail(m);
               System.out.println("Mail " + mailCounter + " sent!");
               mailCounter++;
            }
            System.out.println("Mails sent!");
         }
      }
   }
}
