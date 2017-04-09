/**
 * Sample Skeleton for 'Game.fxml' Controller Class
 */

package it.polito.tdp.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class GameController {
	private int margine =10;
	
	private int numeroInserito;
	private int numeroSegreto ;
	private int difficolta;
	private int totaleTentativi;
	private int numTentativo;
	private boolean inGame = false ;// Mi tiene traccia dello stato di gioco


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbDifficolta"
    private ComboBox<Integer> cmbDifficolta; // Value injected by FXMLLoader

    @FXML // fx:id="btnGioca"
    private Button btnGioca; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumeroInserito"
    private TextField txtNumeroInserito; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private Label txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtInfo"
    private Label txtInfo; // Value injected by FXMLLoader

    @FXML // fx:id="pgrBar"
    private ProgressBar pgrBar; // Value injected by FXMLLoader


    @FXML
    void doGioca(ActionEvent event) {
    	if(inGame){
    		inGame = false ;
    		btnGioca.setText("Gioca!");
    		
    		// Rendi non visibile l'interfaccia
    		
    		makeGuiVisible(false);
    		cmbDifficolta.setDisable(false);
    		
    	} else {
    		inGame = true ;
    		btnGioca.setText("Abbandona!");
    		
    		// Rendi visibile l'interfaccia
    		makeGuiVisible(true);
    		cmbDifficolta.setDisable(true);
    		
    		txtNumeroInserito.setDisable(false);
    		btnProva.setDisable(false);
    		txtNumeroInserito.setText("");
    		txtResult.setText("Inserisci numero");
    		
    		
    	}
    	
    	if (cmbDifficolta.getValue() == null){
    		return ;
    	}
    	difficolta = cmbDifficolta.getValue();
    	numeroSegreto = (int) (Math.random()*difficolta) + 1;
    	totaleTentativi = (int) (Math.log(difficolta)/Math.log(2.0)+ margine);
    	
    	numTentativo = 0;
    	txtInfo.setText(String.format("Tentativo: %d/%d", numTentativo,totaleTentativi));
    	pgrBar.setProgress(0.0);
    	//pgrBar.setProgress((double)numTentativo/totaleTentativi);

    		

    }

    private void makeGuiVisible(boolean visible) {
    	txtNumeroInserito.setVisible(visible);
    	btnProva.setVisible(visible);
    	txtResult.setVisible(visible);
    	txtInfo.setVisible(visible);
    	pgrBar.setVisible(visible);
		
	}

	@FXML
    void doProva(ActionEvent event) {
    	try{
    		numeroInserito = Integer.parseInt(txtNumeroInserito.getText());
    	   } catch (NumberFormatException e) {
			// TODO: handle exception
    		   txtResult.setText("Inserisci un numero");
    		   return ;
		}
    	numTentativo++;
    	//String a = "Tentativo : "+ numTentativo+"/"+totaleTentativi;
    	txtInfo.setText(String.format("Tentativo: %d/%d", numTentativo,totaleTentativi));
    	pgrBar.setProgress((double)numTentativo/totaleTentativi);
    	
    	if(numTentativo > totaleTentativi){
    		// Ho perso
    		txtResult.setText("Hai perso! Era "+numeroSegreto);
    		txtNumeroInserito.setDisable(true);
    		btnProva.setDisable(true);
    		return ;
    	}
    	
    	if(numTentativo == numeroSegreto ){
    		// Ho vinto
    		txtResult.setText("Hai vinto!");
    		txtNumeroInserito.setDisable(true);
    		btnProva.setDisable(true);
    		return ;
    	}
    	
    	if(numeroInserito < numeroSegreto){
    		// Troppo piccole
    		txtResult.setText("Troppo piccole!");
    		return ;
    	}
    	
    	if(numeroInserito > numeroSegreto){
    		// Troppo grande
    		txtResult.setText("Troppo grande!");
    		return ;
    	}
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbDifficolta != null : "fx:id=\"cmbDifficolta\" was not injected: check your FXML file 'Game.fxml'.";
        assert btnGioca != null : "fx:id=\"btnGioca\" was not injected: check your FXML file 'Game.fxml'.";
        assert txtNumeroInserito != null : "fx:id=\"txtNumeroInserito\" was not injected: check your FXML file 'Game.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Game.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Game.fxml'.";
        assert txtInfo != null : "fx:id=\"txtInfo\" was not injected: check your FXML file 'Game.fxml'.";
        assert pgrBar != null : "fx:id=\"pgrBar\" was not injected: check your FXML file 'Game.fxml'.";
        
        cmbDifficolta.getItems().addAll(10, 100, 1000);// Il metodo toString di Integer simplicemente stampa il valore
        //new Integer(10);
        if(cmbDifficolta.getItems().size()>0)
        	cmbDifficolta.setValue(cmbDifficolta.getItems().get(0));

    }
}
