package bookingProject.io;

public interface Console {

  default void printLn() {
    print("\n");
  }
  default void printLn(String line) {
    print(line);
    printLn();
  }
  void print(String line);
  String readLn();
//  String read();
}
