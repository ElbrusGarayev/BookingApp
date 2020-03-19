package bookingProject.io;

import java.util.Scanner;

public class UnixConsole implements Console {

  private final Scanner scanner;

  public UnixConsole(Scanner scanner) {
    this.scanner = scanner;
  }

  @Override
  public void print(String line) {
    System.out.print(line);
  }

  @Override
  public String readLn() {
    return scanner.nextLine();
  }

//  @Override
//  public String read() {
//    return scanner.next();
//  }
}
