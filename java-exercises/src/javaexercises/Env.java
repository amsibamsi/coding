package javaexercises;

import java.net.URL;
import java.net.URLClassLoader;
 
public class Env {
 
  public static void main (String args[]) {
 
    ClassLoader cl = ClassLoader.getSystemClassLoader();
 
    URL[] urls = ((URLClassLoader)cl).getURLs();
 
    for(URL url: urls){
      System.out.println(url.getFile());
    }
 
  }

}
