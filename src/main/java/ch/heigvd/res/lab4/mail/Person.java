/*
 * @File Person.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.mail;

import lombok.Getter;

public class Person {
   @Getter private final String email;

   public Person(String email) {
      this.email = email;
   }
}
