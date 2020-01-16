public class Lenkeliste<T> implements Liste<T>{
  class Node{
    Node neste = null;
    T data;

    Node(T x){
      data = x;
    }
  }
  Node start = null;

  public int stoerrelse(){
    Node s = start;
    int teller = 0;

    while (s != null) {
      s = s.neste;
      teller++;
    }
    return teller;
  }

  public T hent(int pos){
    Node s = start;
    if (pos < 0){
      throw new UgyldigListeIndeks(pos);
    }
    if (stoerrelse() <= pos || stoerrelse() == 0) {
      throw new UgyldigListeIndeks(pos);
    }
    else{
      for (int i = 0; i < pos; i++){
        s = s.neste;
      }
      return s.data;
    }
  }

  public void sett(int pos, T x){
    Node s = start;
    if(stoerrelse() == 0 || pos < 0){
      throw new UgyldigListeIndeks(pos);
    }
    if(stoerrelse() <= pos){
      throw new UgyldigListeIndeks(pos);
    }
    for (int i = 0; i < pos; i++){
      s = s.neste;
    }
    s.data = x;
  }

  public void leggTil(T x){
    Node s = start;
    Node nyNode = new Node(x);
    if(stoerrelse() == 0){
      start = nyNode;
    }
    /*else if (stoerrelse() == 1){
      s.neste = nyNode;
    }*/
    else{
      while(s.neste != null){
        s = s.neste;
      }
      s.neste = nyNode;
    }
  }

  public void leggTil(int pos, T x){
    Node s = start;
    Node nyNode = new Node(x);
    Node forrige = null;
    if (pos > stoerrelse() || pos < 0) {
      throw new UgyldigListeIndeks(pos);
    }
    //legg til bak
    if(pos == stoerrelse()){
      leggTil(x);
    }
    //legg til forran
    else if(pos == 0){
      nyNode.neste = s;
      start = nyNode;
    }
    //legg til mellom
    else{
      for (int i = 0; i < pos; i++){
        forrige = s;
        s = s.neste;
      }
      //Node temp = s;
      nyNode.neste = s;
      forrige.neste = nyNode;
      //nyNode.neste = s.neste;

    }
  }


  public T fjern(int pos){
    Node s = start;
    Node forrige = null;
    if(pos >= stoerrelse() || pos < 0){
      throw new UgyldigListeIndeks(pos);
    }
    if(stoerrelse() == 0){
      throw new UgyldigListeIndeks(pos);
    }
    else if(pos == 0){
      Node temp = start;
      start = start.neste;
      return temp.data;
    }
    else{
      for (int i = 0; i < pos; i++) {
        forrige = s;
        s = s.neste;
      }

      if(s.neste != null){
        forrige.neste = s.neste;
        //s.neste = forrige.neste;
      }else{
        forrige.neste = null;
      }
      return s.data;
  }
}

  public T fjern(){
    return fjern(0);
  }

}

interface Liste<T> {
  public int stoerrelse();
  public void leggTil(int pos, T x);
  public void leggTil(T x);
  public void sett(int pos, T x);
  public T hent(int pos);
  public T fjern(int pos);
  public T fjern();
}
class UgyldigListeIndeks extends RuntimeException {
  UgyldigListeIndeks(int indeks) {
      super("Ugyldig indeks: " + indeks);
  }
}
