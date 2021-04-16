/*
 * @File Mail.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.prank.mail;

import lombok.Getter;

public class Mail {
   @Getter private final Person from;
   @Getter private final Group to;
   @Getter private final Group cc;
   @Getter private final Group bcc;
   @Getter private final Message message;

   public Mail(Person from, Group to, Group cc, Group bcc, Message message) {
      this.from = from;
      this.to = to;
      this.cc = cc;
      this.bcc = bcc;
      this.message = message;
   }

   public String getMailData() {
      StringBuilder data = new StringBuilder();
      data.append("From: ").append(from.getEmail()).append("\n");
      data.append("To: ");
      for (int i = 0; i < to.getMembers().size(); ++i) {
         data.append(to.getMembers().get(i));
         if (i != to.getMembers().size() - 1) {
            data.append(",");
         } else {
            data.append("\n");
         }
      } data.append(message.getSubject()).append(message.getBody());
      return data.toString();
   }

}
