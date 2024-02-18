protected abstract class FitxerReproduible extends FitxerMultimedia{
	
	//atributs
	protected float durada;
	protected String codec;
	//costructor
	protected FitxerReproduible(String cami, String nom, String codec, float durada, Reproductor r){
	Super(cami, nom);
	this.codec=codec;
	this.durada=durada;
	this.r=r;
	}	
	//mètodes
	protected abstract void reproduir();
}

public class Video extends FitxerReproduible{
	//atributos
	int alcada, amplada;
	float fps;
	//constructor
	public Video(String cami, String nom, String codec, float durada, int alcada, int amplada, float fps, Reproductor r){
		Super(cami, nom, codec, durada, r);
		this.alcada=alcada;
		this.amplada=amplada;
		this.fps=fps;
	}
	//metodos
	public void reproduir();{
	}
}

public class Audio extends FitxerReproduible{
	//atributos
	File fitxerImatge;
	float kbps;
	//constructor
	public Video(String cami, File fitxerImatge, String nom, String codec, float durada, float kbps, Reproductor r){
		Super(cami, nom, codec, durada, r);
		this.fitxerImatge=fitxerImatge;
		this.kbps=kbps;
	}
	//metodos
	public void reproduir();{
	}
}
