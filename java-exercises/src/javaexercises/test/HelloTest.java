package javaexercises.test;

import javaexercises.Hello;
import org.junit.*;
import static org.junit.Assert.*;

public class HelloTest {

  @Test
  public void testHello() {
    assertEquals(Hello.hello(), "hello");
  }

}
