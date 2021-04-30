/*
 * @File SMTPClient.java
 * @Authors : David González León, Jade Gröli
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4;

import ch.heigvd.res.lab4.prank.mail.Group;
import ch.heigvd.res.lab4.prank.mail.Mail;
import ch.heigvd.res.lab4.prank.mail.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * A class implementing a SMTP client
 */
public class SMTPClient {

   private final String serverAdress;
   private final int serverPort;
   private final Logger LOG = Logger.getLogger(SMTPClient.class.getName());


   /**
    * Instantiates a new Smtp client.
    *
    * @param serverAdress the adress of the destination server
    * @param serverPort   the port of the server
    */
   public SMTPClient(String serverAdress, int serverPort) {
      this.serverAdress = serverAdress;
      this.serverPort = serverPort;
   }

   /**
    * Sends a mail to the given server
    *
    * @param mail the mail to send
    */
   public void sendMail(Mail mail) {
      try {
         Socket socket = new Socket(serverAdress, serverPort);
         BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter os = new PrintWriter(socket.getOutputStream(), true);
         waitForResponse(is);
         send(os, "EHLO client");
         waitForResponse(is);
         send(os, "MAIL FROM: " + mail.getFrom().getEmail());
         waitForResponse(is);
         Group to = mail.getTo();
         for (Person p : to.getMembers()) {
            send(os, "RCPT TO: " + p.getEmail());
            waitForResponse(is);
         }
         if (mail.getCc() != null) {
            for (Person p : mail.getCc().getMembers()) {
               send(os, "RCPT TO: " + p.getEmail());
               waitForResponse(is);
            }
         }
         if (mail.getBcc() != null) {
            for (Person p : mail.getBcc().getMembers()) {
               send(os, "RCPT TO: " + p.getEmail());
               waitForResponse(is);
            }
         }
         // TODO: 16 avr. 2021 add cc and bcc
         send(os, "DATA");
         waitForResponse(is);
         send(os, mail.getMailData());
         send(os, "\r\n.\r\n");
         socket.close();
         is.close();
         os.close();
      } catch (IOException e) {
         System.out.println("Error while connecting to the server, could not correctly send a mail");
         e.printStackTrace();
      }
   }

   /**
    * Waits for an answer from the server. Function used to debug the app, using logging to check the message sent by
    * the server
    *
    * @param is the input strem
    *
    * @throws IOException
    */
   private void waitForResponse(BufferedReader is) throws IOException {
      String message;
      do {
         message = is.readLine();
         //LOG.info(message);
      } while (message != null && !(message.contains("220 ") || message.contains("250 ") || message.contains("354 ")));
   }

   /**
    * Sends a message to the server. Function used to debug the app, using logging to check the message sent to the
    * server
    *
    * @param os      the output stream
    * @param message the message to send
    */

   private void send(PrintWriter os, String message) {
      os.println(message);
      //LOG.info(message);
   }
}
