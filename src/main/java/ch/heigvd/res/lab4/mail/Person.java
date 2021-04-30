/*
 * @File Person.java
 * @Authors : David González León, Jade Gröli
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.mail;

import lombok.Getter;

/**
 * A person in a mail
 */
public class Person {
   @Getter private final String email;

   /**
    * Instantiates a new Person.
    *
    * @param email the email of this person
    */
   public Person(String email) {
      this.email = email;
   }
}
