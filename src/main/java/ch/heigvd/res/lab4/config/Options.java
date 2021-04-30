/*
 * @File Options.java
 * @Authors : David González León, Jade Gröli
 * @Date 22 avr. 2021
 */
package ch.heigvd.res.lab4.config;

import lombok.Getter;

/**
 * An enumeration to store the different options available in the config.properties file
 */
public enum Options {
   SERVER_ADDRESS("smtpServerAddress"), SERVER_PORT("smtpServerPort"), NUMBER_OF_GROUPS("numberOfGroups"),
   WITNESS_TO_CC("witnessToCC");
   @Getter private final String paramName;

   Options(String paramName) {
      this.paramName = paramName;
   }
}
