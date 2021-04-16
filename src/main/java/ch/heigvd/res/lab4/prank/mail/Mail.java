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
}
