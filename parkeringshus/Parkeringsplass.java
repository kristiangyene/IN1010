public class Parkeringsplass<E>{
  public E kjoeretoy;

  public Parkeringsplass(){}

  public void parker(E kjoeretoy){
    this.kjoeretoy = kjoeretoy;

  }
  public E kjoerUt(){
    E tmp = this.kjoeretoy;
    this.kjoeretoy = null;
    return tmp;
  }
}
