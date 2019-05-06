package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;


import db.MysqlCon;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		
		Parent root = loader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SalvAction");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
	}
	

	
	public static void main(String[] args) {
		
		launch(args);
	}
}