package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

public class Model {

	private int NMAX = 100;
	private int TMAX = 7;

	private int segreto; // numero da indovinare
	private int tentativi; // tentativi già fatti

	private boolean inGame = false;

	public Model() {
		this.inGame = false;

	}

	/**
	 * Avvia una nuova partita, generando un nuovo numero segreto.
	 */
	public void newGame() {
		//Genero il numero segreto
		this.segreto = (int) (Math.random() * NMAX) + 1;

		this.tentativi = 0;
		this.inGame = true;
	}

	/**
	 * Fai un tentativo per indovinare il numero segreto
	 * 
	 * @param t
	 *            valore numerico del tentativo
	 * @return 0 se hai indovinato, -1 se è troppo basso, +1 se è troppo alto
	 */
	public int tentativo(int t) {
		// Se non sei in partita, lancio un'eccezione
		if (!inGame)
			throw new IllegalStateException("Partita non attiva");
		if (!valoreValido(t))
			throw new InvalidParameterException("Tentativo fuori range");
		
		this.tentativi++ ;
		if(this.tentativi==this.TMAX) {
			partitaFinita();
		}

		if (t == this.segreto) {
			partitaFinita();
			return 0;
		}
		if (t < this.segreto)
			return -1;
		return +1;
	}
	
	/**
	 * COntrolla se il tentativo fornito rispetta le regole formali del gioco,
	 * cioè è nel range [1, NMAX]
	 * @param tentativo
	 * @return {@code true} se il tentativo è valido
	 */
	public boolean valoreValido(int tentativo) {
		return tentativo>=1 && tentativo<=this.NMAX ;
	}

	public boolean isInGame() {
		return inGame;
	}
	
	public int getTentativi()
	{
		return this.tentativi;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	public void partitaFinita()
	{
		this.inGame = false;
	}
	
	public int getSegreto()
	{
		return this.segreto;
	}
	

}
