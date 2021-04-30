/*
 * @File Group.java
 * @Authors : David González León
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.mail;

import java.util.ArrayList;

public class Group {
   private final ArrayList<Person> members = new ArrayList<>();

   public void addMember(Person p) {
      members.add(p);
   }

   public ArrayList<Person> getMembers() {
      return new ArrayList<>(members);
   }
}
