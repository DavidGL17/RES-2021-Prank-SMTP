/*
 * @File Message.java
 * @Authors : David González León, Jade Gröli
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.prank.mail;

import lombok.Getter;

/**
 * A message contained in a mail
 */
public class Message {
   @Getter private final String subject;
   @Getter private final String body;

   /**
    * Instantiates a new Message.
    *
    * @param subject the subject
    * @param body    the body
    */
   public Message(String subject, String body) {
      this.subject = subject;
      this.body = body;
   }
}
