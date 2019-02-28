package it.polito.tdp.indonumero;

public class TestModel {
	public static void main(String[] args) {

		Model model = new Model();

		model.newGame();

		int min = 1;
		int max = model.getNMAX();

		while (model.isInGame()) {
			int t = (min + max) / 2;
			System.out.format("Tra %d e %d provo %d\n", min, max, t);
			int tentativo = model.tentativo(t);

			if (tentativo > 0) {
				max = t - 1;
			} else {
				min = t + 1;
			}
			String ris = (tentativo < 0) ? "troppo basso" : "troppo alto";
			if (tentativo == 0) {
				ris = "riuscito correttamente!Hai vinto!";
			}

			System.out.println("Tentativo " + ris + "\n");
		}
	}

}
