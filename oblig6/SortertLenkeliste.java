class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

  @Override
  public void leggTil(T x){
    Node s = start;
    Node nyNode = new Node(x);
    Node forrige = null;

    if (stoerrelse() == 0){
      start = nyNode;
      return;
    }

    else if (stoerrelse() > 1){
      for(int i = 0; i < stoerrelse()-1; i++){
        if(x.compareTo(hent(i)) <= 0){
          //Legge forran
          if(i == 0){
            nyNode.neste = s;
            start = nyNode;
          }
          //Legge imellom
          else{
          nyNode.neste = s;
          forrige.neste = nyNode;
        }
          return;
        }
        //Flytter pekere frem
        forrige = s;
        s = s.neste;
      }

      if(x.compareTo(s.data) < 0){
        nyNode.neste = s;
        forrige.neste = nyNode;

      }else{
        s.neste = nyNode;
      }
      return;
    }
    //Legger til på slutten
    if(x.compareTo(s.data) < 0){
      nyNode.neste = s;
      start = nyNode;

    }else{
      s.neste = nyNode;
    }
  }


  @Override
  public T fjern(){
    return fjern(stoerrelse()-1);
    }

  @Override
  public void sett(int pos, T x){
    throw new UnsupportedOperationException();
  }

  @Override
  public void leggTil(int pos, T x){
    throw new UnsupportedOperationException();
  }
}
