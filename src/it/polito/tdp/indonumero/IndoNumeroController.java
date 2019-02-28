/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btnNuova"
	private Button btnNuova; // Value injected by FXMLLoader

	@FXML // fx:id="txtCurr"
	private TextField txtCurr; // Value injected by FXMLLoader

	@FXML // fx:id="txtMax"
	private TextField txtMax; // Value injected by FXMLLoader

	@FXML // fx:id="boxGioco"
	private HBox boxGioco; // Value injected by FXMLLoader

	@FXML // fx:id="txtTentativo"
	private TextField txtTentativo; // Value injected by FXMLLoader

	@FXML // fx:id="txtLog"
	private TextArea txtLog; // Value injected by FXMLLoader

	public void setModel(Model model) {
		this.model = model;
	}

	@FXML
	void handleNuova(ActionEvent event) {
		/*
		 * Nella classe Controller, cerco di evitare di fare copie personali di
		 * variabili che mi fornisce l'oggetto modello Così cerco di disaccoppiare più
		 * possibile ed in più ho la possibilità di sfruttare il richiamo alle
		 * variabili, tramite i metodi get(), in modo tale che se la logica del MODEL
		 * viene modificata i metodi GET() [ getTMAX() o getNMAX() o etc ] mi
		 * restituiscono i valori appropriati secondo la logica impletmentata e non
		 * variabili statiche (per esempio se vengono implementati 3 livelli di
		 * difficoltà)
		 */

		model.newGame();

		btnNuova.setDisable(true);
		boxGioco.setDisable(false);

		txtCurr.setText(String.format("%d", model.getTentativi()));
		txtMax.setText(String.format("%d", model.getTMAX()));
		// Pulizia quando inizia di nuovo una partita
		txtLog.clear();
		txtTentativo.clear();

		txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, model.getNMAX()));

	}

	@FXML
	void handleProva(ActionEvent event) {

		String numS = txtTentativo.getText();

		if (numS.length() == 0) {
			txtLog.appendText("Devi inserire un numero\n");
			return;
		}

		try {
			int num = Integer.parseInt(numS);
			// il numero è un intero
			if (!model.valoreValido(num)) {
				txtLog.appendText("Numero inserito fuori range!\n");
				return;
			}

			int risultato = model.tentativo(num);
			txtCurr.setText(String.format("%d", model.getTentativi()));

			if (risultato == 0) {
				// hai indovinato
				txtLog.appendText("Hai vinto!\n");
			} else if (risultato < 0) {
				txtLog.appendText("Troppo basso \n");
			} else {
				txtLog.appendText("Troppo alto \n");
			}

			if (!model.isInGame()) {
				// partita è finita
				if (risultato != 0) {
					txtLog.appendText("Hai perso :(\n");
					txtLog.appendText(String.format("Il numero segreto era: %d", model.getSegreto()));
				}
				model.partitaFinita();
				// "chiudi" partita
				btnNuova.setDisable(false);
				boxGioco.setDisable(true);
			}

		} catch (

		NumberFormatException ex) {
			txtLog.appendText("Il dato inserito non è numerico\n");
			return;
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtCurr != null : "fx:id=\"txtCurr\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

	}
}
