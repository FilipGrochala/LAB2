package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import db.MysqlCon;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class MyController {

	//testowa lista stanowiks
	ObservableList<String> stanowiska = FXCollections.observableArrayList("Druid", "Mag", "Wojownik", "£otrzyk");
	//zmienna pomocnicza do walidacji danych
	boolean poprawne_dane = true;

	@FXML
	private PasswordField pf_haslo;

	@FXML
	private PasswordField pf_powtorz;

	@FXML
	private TextField tf_login;

	@FXML
	private TextField tf_imie;

	@FXML
	private TextField tf_nazwisko;

	@FXML
	private TextField tf_email;

	@FXML
	private ComboBox<String> combo_stanowisko;
	@FXML
	private javafx.scene.control.Label label_imie;

	@FXML
	private Button bt_ok;

	@FXML
	private ToggleGroup plec;
	private String PLEC = "M";

	@FXML
	private void initialize() {
		combo_stanowisko.setItems(stanowiska);
	}

	@FXML
	void handleButtonAction(ActionEvent event) {

		//walidacja danych
		if (tf_login.getText().length() < 5) {
			poprawne_dane = false;
			showAlert("Uwaga!", "Wprowadzi³eœ niepoprawne dane!", 
					"Sprawdz czy login posiada co najmniej 5 znaków ");

		}
		
		if (tf_imie.getText().length() < 2 || tf_imie.getText().length() < 2) {
			poprawne_dane = false;
			showAlert("Uwaga!", "Wprowadzi³eœ niepoprawne dane!", 
					"Sprawdz czy nazwiko i imiê posiadaj¹ co najmniej 2 znaki ");

		}
		
		if (tf_email.getText() =="") {
			poprawne_dane = false;
			showAlert("Uwaga!", "Wprowadzi³eœ niepoprawne dane!", 
					"Pole Email nie mo¿e byæ puste");
		}
		
		//sprawdzenie hase³ z jakiegoœ powodu nie dzia³a wiêc zostawiam w komentarzu
		
		//if (pf_haslo.getText()!=pf_powtorz.getText()) {
		//poprawne_dane = false;
		//showAlert("Uwaga!", "Has³a nie s¹ takie same",
		//		"Has³a w rubryce 'Has³o' i 'Powtórz Has³o' musz¹ byæ takie same!");
		
		//}
		
		if (poprawne_dane) {
			MysqlCon db = new MysqlCon();
			String sqlString = "INSERT INTO users (NAZWISKO,IMIE,STANOWISKO,EMAIL,HASLO,LOGIN,DATA_REJSETRACJI,PLEC) values "
					+ "('" + tf_nazwisko.getText() + "','" + tf_imie.getText() + "','" + combo_stanowisko.getValue()
					+ "','" + tf_email.getText() + "','" + pf_haslo.getText() + "','" + tf_login.getText() + "','"
					+ LocalDate.now() + "','" + PLEC + "');";
			System.out.println(pf_haslo.getText());
			System.out.println(pf_powtorz.getText());

			db.insert(sqlString);

		}
	}

	//funkcja do wybierania plci uzytkownika
	@FXML
	void zmien_plec(ActionEvent event) {
		if (PLEC == "M")
			PLEC = "K";
		else if (PLEC == "K")
			PLEC = "M";
	}

	//fuckjca pozwalajaca na wywolanie okienka z bledem
	public void showAlert(String tytul, String naglowek, String wiadomosc) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(tytul);
		alert.setHeaderText(naglowek);
		alert.setContentText(wiadomosc);
		alert.showAndWait();
	}

}
