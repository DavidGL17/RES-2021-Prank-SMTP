/*
 * @File Message.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.prank.mail;

import lombok.Getter;

public class Message {
   @Getter private final String subject;
   @Getter private final String body;

   public Message(String subject, String body) {
      this.subject = subject;
      this.body = body;
   }
}
