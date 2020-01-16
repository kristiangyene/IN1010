public class Stabel<T> extends Lenkeliste<T>{
  //Last in, first out


  public void leggPaa(T x){
    leggTil(x);
  }
  public T taAv(){
    return fjern(stoerrelse() - 1);
  }
}
