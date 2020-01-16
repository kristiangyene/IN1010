import java.util.Scanner;

class Ruritania{
  public static void main(String[] args) {
    double inntekt = 0;
    double skatt = 0;
    double skatt2 = 0;

    Scanner scanner = new Scanner(System.in);
    System.out.print("Hvor mye er inntekten din på?\n>");
    inntekt += scanner.nextDouble();

    if (inntekt < 10000){
      skatt += inntekt*0.10;
      System.out.println("Du må betale: " + skatt + " i skatt");
    }else{
      skatt += 10000*0.10;
      skatt2 = (inntekt-10000)*0.30;
      skatt += skatt2;
      System.out.println("Du må betale: " + skatt + " i skatt");
  }
  }
}
