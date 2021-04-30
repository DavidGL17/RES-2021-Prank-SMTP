/*
 * @File Group.java
 * @Authors : David González León, Jade Gröli
 * @Date 16 avr. 2021
 */
package ch.heigvd.res.lab4.mail;

import java.util.ArrayList;

/**
 * Represents a group of Persons
 */
public class Group {
   private final ArrayList<Person> members = new ArrayList<>();

   /**
    * Adds a member to the group.
    *
    * @param p the person to add
    */
   public void addMember(Person p) {
      members.add(p);
   }

   /**
    * Gets the members of the group.
    *
    * @return the members of the group
    */
   public ArrayList<Person> getMembers() {
      return new ArrayList<>(members);
   }
}
