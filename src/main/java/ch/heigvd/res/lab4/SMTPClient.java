/*
 * @File SMTPClient.java
 * @Authors : David González León
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
import java.util.ArrayList;
import java.util.logging.Logger;

public class SMTPClient {

   private final String serverAdress;
   private final int serverPort;
   private final Logger LOG = Logger.getLogger(SMTPClient.class.getName());


   public SMTPClient(String serverAdress, int serverPort) {
      this.serverAdress = serverAdress;
      this.serverPort = serverPort;
   }

   public void sendMail(Mail mail) {
      try {
         Socket socket = new Socket(serverAdress, serverPort);
         BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter os = new PrintWriter(socket.getOutputStream(), true);
         waitForResponse(is);
         send(os, "EHLO client");
         waitForResponse(is);
         send(os, "MAIL FROM: " + mail.getFrom());
         waitForResponse(is);
         Group to = mail.getTo();
         for (Person p : to.getMembers()) {
            send(os, "RCPT TO:" + p.getEmail());
            waitForResponse(is);
         }
         // TODO: 16 avr. 2021 add cc and bcc
         send(os,"DATA");
         waitForResponse(is);
         send(os,mail.getMailData());
         send(os,"\r\n.\r\n");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void waitForResponse(BufferedReader is) throws IOException {
      String message = is.readLine();
      while (message != null) {
         LOG.info(message);
         message = is.readLine();
      }
   }

   private void send(PrintWriter os, String message) {
      os.println(message);
      LOG.info(message);
   }
}
