package com.github.javafxd3.api.time;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
  public static void main(String[] argv) throws Exception {

    Format formatter = new SimpleDateFormat("MMMM dd, YYYY HH:mm:ss"); 
    String s = formatter.format(new Date());
    System.out.println(s);
  }
}
