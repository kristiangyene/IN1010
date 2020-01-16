import java.util.Scanner;
class HeiVerden{
  public static void main(String[] args) {
    String navn;
    System.out.println("Hei! Hva heter du?");
    Scanner inp = new Scanner(System.in);
    navn = inp.nextLine();
    System.out.println("Hei " + navn + "! Velkommen til IN1010");
  }
}
