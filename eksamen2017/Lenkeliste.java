import java.util.*;
// 2veis lenket liste
//First come firstserved
//stacken gaar mot start. Legg til paa slutt --> hent fra start
//forrige er foran i koen
//Neste er bak i koen
class Lenkeliste<T> implements Liste<T> {
   protected Node start;
   protected Node slutt;
   protected int length = 0;

    class Node {
        Node neste = null;
        Node forrige = null;
        T data;

        Node(T data) {
            this.data = data;
        }
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

    //itererer gjennom lenken, og returnerer stoerrelsen paa listen
    public int stoerrelse() {
        Node p = start;
        int stoerrelse = 0;
        while (p != null) {
            stoerrelse++;
            p = p.neste;
        }
        return stoerrelse;
    }

    //Hjelpemetode som sjekker om opgitt indeks er gyldig
    protected void sjekkIndeks(int pos) {
        if (pos < 0 || pos >= length) {
            throw new UgyldigListeIndeks(pos);
        }
    }

    //Legger til node paa opgitt indeks fra.
    //Elementet og resten av lenken blir skjovet ett hakk i retning slutt.
    public void leggTil(int pos, T x){
        if (pos < 0 || pos > length) {
            throw new UgyldigListeIndeks(pos);
        }
        if (pos == length) {
            leggTil(x);
        }
        else if (pos == 0) {
            Node nyNode = new Node(x);
            nyNode.neste = start;
            start.forrige = nyNode;
            start = nyNode;
            length++;
        }
        else {
            Node ogNode = hentNode(pos);
            Node nyNode = new Node(x);

            Node forrigeNode = ogNode.forrige;
            forrigeNode.neste = nyNode;
            nyNode.neste = ogNode;
            nyNode.forrige = forrigeNode;
            ogNode.forrige = nyNode;
            length++;

        }
    }

    //Element blir lagt til paa slutten av lenken, og slutt pekeren vil peke pa denne nye noden
    public void leggTil(T x) {
        if (length == 0) {
            Node newNode = new Node(x);
            start = newNode;
            slutt = newNode;
        }
        else {
            Node newNode = new Node(x);
            newNode.forrige = slutt;
            slutt.neste = newNode;
            slutt = newNode;
        }
        length++;
    }


    //Endrer data verdien paa en node paa opgitt pos
    public void sett(int pos, T x) {
        sjekkIndeks(pos);
        Node noden = hentNode(pos);
        noden.data = x;
    }

    //Henter daten paa en opgitt indeks
    public T hent(int pos) {
        return hentNode(pos).data;
    }

    //Henter noden paa en opgitt indeks
    protected Node hentNode(int pos) {
        sjekkIndeks(pos);
        Node node = start;
        for (int i=0; i < pos; i++) {
            node = node.neste;
        }
        return node;
    }

    //fjerner element pa oppgitt plass
    public T fjern(int pos) {
        sjekkIndeks(pos);
        if (pos == 0) {
            return fjern();
        }
        else if (pos == length - 1) {
            Node goneNode = slutt;
            Node newLastNode = goneNode.forrige;
            newLastNode.neste = null;
            slutt = newLastNode;
            length--;
            return goneNode.data;
        }

        Node goneNode = hentNode(pos);
        Node forrigeNode = goneNode.forrige;
        Node nesteNode = goneNode.neste;
        forrigeNode.neste = nesteNode;
        nesteNode.forrige = forrigeNode;
        length--;
        return goneNode.data;

    }

    //Fjerner forste elementet i listen
    public T fjern() {
        if (length == 0) {
            throw new UgyldigListeIndeks(1);
        }
        if (length == 1) {
            Node node = start;
            start = null;
            slutt = null;
            length--;
            return node.data;

        }
        else {
            Node node = start;
            start = node.neste;
            start.forrige = null;
            node.neste = null;
            length--;
            return node.data;
        }
    }
}
