class Main{
  //Oppretter alle tråder som trengs.
  public static void main(String[] args) {
    int antallTelegrafister = 3;
    int antallKryptografer = 10;
    Operasjonssentral ops = new Operasjonssentral(antallTelegrafister);
    Kanal[] kanaler = ops.hentKanalArray();

    KryptertMonitor monitor1 = new KryptertMonitor(kanaler.length);
    DekryptertMonitor monitor2 = new DekryptertMonitor(antallKryptografer);
    //Oppretter telegrafister.
    for(int i = 0; i < kanaler.length; i++){
      Thread telegrafister = new Thread(new Telegrafist(kanaler[i], monitor1));
      telegrafister.start();
    }
    //Oppretter kryptografer.
    for(int i = 0; i < antallKryptografer; i++){
      Thread kryptografer = new Thread(new Kryptograf(monitor1, monitor2));
      kryptografer.start();
    }
    //Oppretter operasjonsleder.
    Thread opLeder = new Thread(new Operasjonsleder(monitor2, antallTelegrafister));
    opLeder.start();
  }
}
