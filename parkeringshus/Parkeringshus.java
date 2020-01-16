public class Parkeringshus{
  Parkeringsplass<E>[] etasje1 = new Parkeringsplass<E>[10];
  Parkeringsplass<E>[] etasje2 = new Parkeringsplass<E>[10];

  public void set1(E e, int para){
    etasje1[para] = e;
  }
  public void set2(E e, int para){
    etasje2[para] = e;
  }
}
