package keti.iot;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FXMLMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("IoT-GUI");

		MainController root = new MainController();
		root.parentStage = primaryStage;
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) throws Exception {
		ModelController model = new ModelController();
		model.getDevice();
		model.getSensor();
		launch(args);
	}
}
