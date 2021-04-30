/*
 * @File Mail.java
 * @Authors : David González León, Jade Gröli
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.prank.mail;

import lombok.Getter;

/**
 * Represents a mail that can be sent
 */
public class Mail {
   @Getter private final Person from;
   @Getter private final Group to;
   @Getter private final Group cc;
   @Getter private final Group bcc;
   @Getter private final Message message;

   /**
    * Instantiates a new Mail.
    *
    * @param from    the from
    * @param to      the to
    * @param cc      the cc
    * @param bcc     the bcc
    * @param message the message
    */
   public Mail(Person from, Group to, Group cc, Group bcc, Message message) {
      this.from = from;
      this.to = to;
      this.cc = cc;
      this.bcc = bcc;
      this.message = message;
   }

   /**
    * Rhis function returns the content of the mail as it would be sent in a conversation with a server after sending
    * DATA
    *
    * @return the mail data
    */
   public String getMailData() {
      StringBuilder data = new StringBuilder();
      data.append("From: ").append(from.getEmail()).append("\n");
      data.append("To: ");
      addDest(data, to);
      if (cc != null) {
         data.append("Cc: ");
         addDest(data, cc);
      }
      data.append(message.getSubject()).append(message.getBody());
      return data.toString();
   }

   private void addDest(StringBuilder data, Group group) {
      for (int i = 0; i < group.getMembers().size(); ++i) {
         data.append(group.getMembers().get(i).getEmail());
         if (i != group.getMembers().size() - 1) {
            data.append(",");
         } else {
            data.append("\n");
         }
      }
   }

}
