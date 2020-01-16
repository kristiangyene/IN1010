class ListeMedStatus <T extends NavnOgPris> {
  Node head = null;
  Node tail = null;
  private int stoerrelse = 0;
  private int length = 0;


  class Node {
    Node neste = null;
    T data;
    boolean markert = false;
    Node (T data) {
      this.data = data;
    }

    public Lenkelisteiterator iterator() {
      return new Lenkelisteiterator();
    }

    public class Lenkelisteiterator implements Iterator<T> {
      Node temp = start;
      public boolean hasNext() {
        return temp!=null;
      }
      public T next() {
        T returner = temp.data;
        temp = temp.neste;
        return returner;
      }
      public void remove() {
        int i = 0;
      }
    }
    //Element blir lagt til paa slutten av lenken, og slutt pekeren vil peke pa denne nye noden
    public void leggTil(T x) {
      Node newNode = new Node(x);
        if (length == 0) {
          tail = newNode;
          head = newNode;
        }
        else {
          newNode.neste = tail;
          tail = newNode;
        }
        length++;
    }
    public boolean sjekk(String varenavn) {
      Node iter = tail;
      while(iter != null) {
        if (iter.data.hentNavn().equals(varenavn) && !iter.markert) {
          iter.markert = true;
          return true;
        }
        iter = iter.neste;
      }
      //kunden proever aa snike med seg en vare
      return false;
    }

    public double totalPris() {
      double totalPris = 0;
      Node iter = tail;
      while(iter != null) {
        totalPris += iter.data.hentPris();
        iter = iter.neste;
      }
      return totalPris;
    }

    public ListeMedStatus<T> lagListeAvAlleUmarkerte() {
      ListeMedStatus<T> liste = new ListeMedStatus<T>();
      Node iter = tail;
      while(iter != null) {
        if (!iter.markert) {
          liste.leggTil(iter);
        }
      }
      return liste;
    }
  }
}
