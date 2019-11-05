package dad.javafx.CambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {
	
	private TextField primerDato;
	private TextField segundoDato;
	private ComboBox <String> primerCombo;
	private ComboBox <String> segundoCombo;
	private Button resultado;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primerDato = new TextField();
		primerDato.setPrefColumnCount(4);
		
		segundoDato = new TextField();
		segundoDato.setPrefColumnCount(4);
		segundoDato.setDisable(true);
		
		primerCombo = new ComboBox <String>();
		primerCombo.getItems().addAll("Euro", "Libra", "Dolar", "Yen");
		
		segundoCombo = new ComboBox <String>();
		segundoCombo.getItems().addAll("Euro", "Libra", "Dolar", "Yen");
		
		resultado = new Button("Cambiar");
		resultado.setDefaultButton(true);
		

		HBox primerHBox = new HBox(2, primerDato, primerCombo);
		primerHBox.setAlignment(Pos.CENTER);
		HBox segundoHBox = new HBox(2, segundoDato, segundoCombo);
		segundoHBox.setAlignment(Pos.CENTER);
		HBox botonHBox = new HBox(1, resultado);
		botonHBox.setAlignment(Pos.CENTER);
		VBox root = new VBox(3, primerHBox, segundoHBox, botonHBox);
		root.setAlignment(Pos.CENTER);
		
		Scene escena = new Scene(root, 320, 200);

		primaryStage.setScene(escena);
		primaryStage.setTitle("Cambiar Divisas");
		primaryStage.show();

		primerCombo.getSelectionModel().selectFirst();
		segundoCombo.getSelectionModel().select(2);
		resultado.setOnAction(e -> onCheckButtonAction(e));
		
	}
	
	 private void onCheckButtonAction(ActionEvent e) {
		 
				try {
					double cantidad = Double.parseDouble (primerDato.getText());
					String comboOrigen = primerCombo.getSelectionModel().getSelectedItem();
					String comboDestino = segundoCombo.getSelectionModel().getSelectedItem();
					
					Divisa euro = new Divisa("Euro", 1.0);
					Divisa libra = new Divisa("Libra", 0.8873);
					Divisa dolar = new Divisa("Dolar", 1.2007);
					Divisa yen = new Divisa("Yen", 133.59);
					Divisa origen = yen;
					Divisa destino = dolar; 
					
					switch (comboOrigen) {
					case "Euro":
						origen=euro;
						break;
					case "Libra":
						origen=libra;
						break;
					
					case "Dolar":
						origen=dolar;
						break;
					
					case "Yen":
						origen=yen;
						break;
					}
					
					switch (comboDestino) {
					case "Euro":
						destino=euro;
						break;
					
					case "Libra":
						destino=libra;
						break;

					case "Dolar":
						destino=dolar;
						break;
					
					case "Yen":
						destino=yen;
						break;
					}
					
					String cambio = ""+(Divisa.fromTo(origen,  destino,  cantidad));
					segundoDato.setText(cambio);
					
				} catch (NumberFormatException e1) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Cambiar Divisas");
					alert.setHeaderText("Error");
					alert.setContentText("El numero introducido no es válido.");
					alert.showAndWait();
				}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
