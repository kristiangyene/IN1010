import java.util.LinkedList;
import java.util.concurrent.locks.*;

abstract class Pasient {
    final String fnr;
    final String navn;
    Pasient neste;
    final static int MAXPASPRIO = 10;
    int prioritet; // 0 <= prioritet <= MAXPASPRIO
    int sengNr = -1;

    abstract boolean iFaresonen();
    abstract void kanHaSykdom();

    Pasient (String navn, String fnr, int prio) {
	this.navn = navn;  this.fnr = fnr;
	prioritet = prio;
    }
}

class KvinneligPasient extends Pasient {
    KvinneligPasient(String navn, String fnr, int prio) {
	super(navn, fnr, prio);
    }

    boolean iFaresonen() {
	return Sykehus.iFaresonenKvinne(this);
    }

    void kanHaSykdom() {
	Sykehus.kanHaSykdomKvinne(this);
    }
}

class MannligPasient extends Pasient {
    MannligPasient(String navn, String fnr, int prio) {
	super(navn, fnr, prio);
    }

    boolean iFaresonen() {
	return Sykehus.iFaresonenMann(this);
    }

    void kanHaSykdom() {
	Sykehus.kanHaSykdomMann(this);
    }
}

class Sil implements Runnable {
    PasientPrio pasienter;
    int prioritet;
    AnalyseBuffer tilAnalyse;

    Sil(PasientPrio pas, int prio, AnalyseBuffer a) {
	pasienter = pas;  prioritet = prio;  tilAnalyse = a;
    }

    @Override
    public void run() {
	Pasient p = pasienter.hentUt(prioritet);
	while (p != null) {
	    if (p.iFaresonen()) {
		try {
		    tilAnalyse.leggInn(p);
		} catch (InterruptedException e) {}
	    }
	    p = pasienter.hentUt(prioritet);
	}
    }
}

class AnalyseBuffer {
    LinkedList<Pasient> buf = new LinkedList<>();
    Lock laas = new ReentrantLock();
    Condition ikkeTom = laas.newCondition();

    void leggInn(Pasient pas) throws InterruptedException {
	laas.lock();
	try {
	    buf.add(pas);
	    ikkeTom.signalAll();
	} finally {
	    laas.unlock();
	}
    }

    Pasient hentUt() throws InterruptedException {
	laas.lock();
	try {
	    while (buf.size() == 0) ikkeTom.await();
	    return buf.removeFirst();
	} finally {
	    laas.unlock();
	}
    }
}

class Analyse implements Runnable {
    AnalyseBuffer buf;

    Analyse(AnalyseBuffer b) {
	buf = b;
    }

    @Override
    public void run() {
	while (true) {
	    try {
		Pasient pas = buf.hentUt();
		pas.kanHaSykdom();
	    } catch (InterruptedException e) {}
	}
    }
}

class Sykehus {
    static boolean iFaresonenKvinne(Pasient pas) {
	return true;
    }
    static boolean iFaresonenMann(Pasient pas) {
	return true;
    }

    static void kanHaSykdomKvinne(Pasient pas) {
    }

    static void kanHaSykdomMann(Pasient pas) {
    }

    void utforAnalyse(PasientPrio pas, int antAnalyser) {
	AnalyseBuffer buf = new AnalyseBuffer();
	for (int i = 0;  i <= Pasient.MAXPASPRIO;  i++) {
	    new Thread(new Sil(pas, i, buf)).start();
	}
	for (int i = 1;  i <= antAnalyser;  i++) {
	    new Thread(new Analyse(buf)).start();
	}
    }
}


// --------------------------------------------------------------
// Kopi fra tidligere oppgaver.

abstract class PasientAdm {
    abstract void settInnPasient(Pasient p);
    abstract Pasient hentUt(Pasient p);
    abstract Pasient hentUt(int i);
}

class PasientPrio extends PasientAdm {
    Pasient[] prioStart = new Pasient[Pasient.MAXPASPRIO+1],
	prioSist = new Pasient[Pasient.MAXPASPRIO+1];

    void settInnPasient(Pasient p) {
	int i = p.prioritet;
	if (prioStart[i] == null)
	    prioStart[i] = p;
	else
	    prioSist[i].neste = p;

	prioSist[i] = p;  p.neste = null;
    }

    Pasient hentUt(Pasient p) {
	int i = p.prioritet;
	Pasient px = prioStart[i];
	if (px == null) return null;
	if (px == p) {
	    prioStart[i] = p.neste;
	    if (prioStart[i] == null)
		prioSist[i] = null;
	    return p;
	}

	while (true) {
	    Pasient px2 = px.neste;
	    if (px2 == null) return null;
	    if (px2 == p) {
		px.neste = px2.neste;
		if (prioSist[i] == px2) prioSist[i] = px;
		return px2;
	    }
	    px = px2;
	}
    }

    Pasient hentUt(int i) {
	return hentUt(prioStart[i]);
    }
}
