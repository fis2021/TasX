package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dizitart.no2.Nitrite;

public class Main extends Application {
    static boolean admin = false;
    static String mail = "";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath("/tmp/test.db")
                .openOrCreate("user", "password");
        //launch(args);
    }
}
