import java.util.*;
public class Dyr{
  public static final double FAVNUMBER = 1,6180;
  private String name;
  private int vekt;
  private boolean harEier = False;
  private byte age;
  private long unikID;
  private char favorittKar;
  private double fart;
  private float høyde;

  protected static int antallDyr = 0;

  static Scanner userInput = new Scanner(System.in);

  public Dyr(){
    antallDyr++;

    int sumAvNummer = 5 + 1;
    System.out.println("5 + 1 = " + sumAvNummer);
    int diffAvNummer = 5 - 1;
    System.out.println("5 - 1 = " + diffAvNummer);
    int prodAvNummer = 5 * 1;
    System.out.println("5 * 1 = " + prodAvNummer);
    int divAvNummer = 5 / 1;
    System.out.println("5 / 1 = " + divAvNummer);

    System.out.println("Skriv inn navn: \n");
    if(userInput.hasNextLine()){
      this.setName(userInput.nextLine());

    }
  }
    public static void main(String[] args) {
      Dyr dyret = new Dyr;

  }
}
