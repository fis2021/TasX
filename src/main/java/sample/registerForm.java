package sample;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.sun.istack.internal.NotNull;
import javafx.application.Application;
import org.bson.Document;
import static javafx.application.Application.launch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

class RegisterForm extends Application {
    @Override
    public void start(Stage stage) {};
    public void show() {
        Stage stage = new Stage();

        Text nume = new Text("Nume");
        Text prenume = new Text("Prenume");
        Text email = new Text("Email");
        Text pass = new Text("Parola");
        Text tipUser = new Text("Tip utilizator");

        Alert a = new Alert(Alert.AlertType.NONE);
        TextField numeText = new TextField();
        TextField prenumeText = new TextField();
        TextField mailText = new TextField();
        PasswordField parolaText = new PasswordField();
        ToggleGroup tip = new ToggleGroup();
        RadioButton admin = new RadioButton("Admin");
        admin.setToggleGroup(tip);
        RadioButton simple = new RadioButton("Utilizator simplu");
        simple.setToggleGroup(tip);

        Button button1 = new Button("Inregistrare");
        Button button2 = new Button("Sterge tot");

        GridPane gridPane = new GridPane();

        gridPane.setMinSize(400, 200);

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(nume, 0, 0);
        gridPane.add(numeText, 1, 0);
        gridPane.add(prenume, 0, 1);
        gridPane.add(prenumeText, 1, 1);
        gridPane.add(email, 0, 2);
        gridPane.add(mailText, 1, 2);
        gridPane.add(pass, 0, 3);
        gridPane.add(parolaText, 1, 3);
        gridPane.add(tipUser, 0, 4);
        gridPane.add(admin, 1, 4);
        gridPane.add(simple, 2, 4);
        gridPane.add(button1, 0, 5);
        gridPane.add(button2, 1, 5);

        button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        nume.setStyle("-fx-font: normal bold 20px 'serif' ");
        prenume.setStyle("-fx-font: normal bold 20px 'serif' ");
        email.setStyle("-fx-font: normal bold 20px 'serif' ");
        pass.setStyle("-fx-font: normal bold 20px 'serif' ");
        tipUser.setStyle("-fx-font: normal bold 20px 'serif' ");

        gridPane.setStyle("-fx-background-color: BEIGE;");

        Scene scene = new Scene(gridPane);

        stage.setTitle("Inregistreaza");

        stage.setScene(scene);

        stage.show();

        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int userType;
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                MongoDatabase database = mongo.getDatabase("admin");
                //database.createCollection("users");
                Document document = new Document();
                document.append("nume", numeText.getText());
                document.append("prenume", prenumeText.getText());
                document.append("mail", mailText.getText());
                document.append("parola", parolaText.getText());
                if (admin.isSelected())
                    userType = 1;
                else
                    userType = 2;
                document.append("admin", userType);
                //Inserting the document into the collection
                database.getCollection("users").insertOne(document);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Utilizatorul a fost inregistrat cu succes!");
                alert.showAndWait();
                stage.hide();
                Main m = new Main();
                Stage st = new Stage();
                try {
                    m.start(st);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               numeText.setText("");
               prenumeText.setText("");
               mailText.setText("");
               parolaText.setText("");
               admin.setSelected(false);
               simple.setSelected(false);
            }
        });
    }
}