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

class PasientTabell extends PasientAdm {
    Pasient[] senger;
    int letPos = 0;

    PasientTabell(int ant) {
	senger = new Pasient[ant];
    }

    void settInnPasient(Pasient p) {
	int startPos = letPos;
	while (true) {
	    if (senger[letPos] == null) {
		senger[letPos] = p;  p.sengNr = letPos;
		return;
	    }
	    letPos++;
	    if (letPos >= senger.length) letPos = 0;
	    if (letPos == startPos) return;
	}
    }

    Pasient hentUt(Pasient p) {
	senger[p.sengNr] = null;  p.sengNr = -1;
	return p;
    }

    Pasient hentUt(int i) {
	for (int ix = 0;  ix < senger.length;  ix++)
	    if (senger[ix]!=null && senger[ix].prioritet==i) {
		Pasient px = senger[ix];  px.sengNr = -1;
		senger[ix] = null;
		return px;
	    }
	return null;
    }
}
