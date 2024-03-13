package com.example;

/**
 * Greets someone.
 */
public class Greeter {

  /**
   * Creates a new greeter.
   */
  public Greeter() {
  }

  /**
   * Returns a greeting message for the given {@code name}.
   *
   * @param name the name to greet
   * @return a greeting message
   */
  public String greet(String name) {
    return String.format("Hello, %s!", name);
  }

}
