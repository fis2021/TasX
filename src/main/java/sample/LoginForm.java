package sample;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

class LoginForm extends Application {

    @Override
    public void start(Stage stage) {};
    public void show() {
        Stage stage = new Stage();

        Text text1 = new Text("Email");
        Text text2 = new Text("Parola");

        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();

        Button button1 = new Button("Intra");
        Button button2 = new Button("Sterge tot");

        GridPane gridPane = new GridPane();

        gridPane.setMinSize(400, 200);

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(textField2, 1, 1);
        gridPane.add(button1, 0, 2);
        gridPane.add(button2, 1, 2);

        button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        Scene scene = new Scene(gridPane);

        stage.setTitle("Logare");

        stage.setScene(scene);

        stage.show();

        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int userType;
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                MongoDatabase database = mongo.getDatabase("admin");

                MongoCollection<Document> collection = database.getCollection("users");
                Document myDoc = collection.find(Filters.eq("mail", textField1.getText())).first();

                String pass = "" + myDoc.get("parola");
                int tip = Integer.parseInt(myDoc.get("admin").toString());
                if (tip == 1)
                    Main.admin = true;
                Alert alert;
                if (pass.equals(textField2.getText())) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION, "Utilizatorul a fost logat cu succes!");
                    Main.mail = textField2.getText();
                    stage.hide();

                    HomePage hp = new HomePage();
                    hp.show();

                }
                else
                    alert = new Alert(Alert.AlertType.ERROR, "Eroare la logare!");
                alert.showAndWait();

            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textField1.setText("");
                textField2.setText("");
            }
        });
    }
}