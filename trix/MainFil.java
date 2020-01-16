import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
class MainFil{
  static ArrayList<Hund> hunder = new ArrayList<Hund>();
  static ArrayList<Person> personer = new ArrayList<Person>();
  int index = 0;


  public static void main(String[] args) {
    MainFil main = new MainFil();
    main.readFile("navn.txt");
  }
  public MainFil(){
  }

  private void readFile(String filen){
    try{
    File fil = new File(filen);
    Scanner scan = new Scanner(fil);
    while (scan.hasNextLine()){
      String[] biter = new String[2];
      String a, b;
      biter = scan.nextLine().split(" ");
      a = biter[0];
      b = biter[1];
      if(a.equals("P")){
        Person person = new Person(b);
        personer.add(person);
      }
      else if(a.equals("H")){
        Hund hund = new Hund(b);
        hunder.add(hund);
      }
    }
  }
  catch(FileNotFoundException e){
    System.out.println("Kunne ikke finne filen");
    }
    for(Hund hund: hunder){
      System.out.println(hund.hentNavn());
    }
    System.out.println();
    for(Person person: personer){
      System.out.println(person.hentNavn());
    }
  }
}
