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

class AddTask extends Application {
    @Override
    public void start(Stage stage) {};
    public void show() {
        Stage stage = new Stage();

        Text task = new Text("Nume task");
        Text deadline = new Text("Deadline");
        Text resp = new Text("Mail responsabil");

        Alert a = new Alert(Alert.AlertType.NONE);
        TextField taskText = new TextField();
        TextField deadlineText = new TextField();
        TextField respText = new TextField();

        Button add = new Button("Adauga");
        Button button2 = new Button("Sterge tot");

        GridPane gridPane = new GridPane();

        gridPane.setMinSize(400, 200);

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(task, 0, 0);
        gridPane.add(taskText, 1, 0);
        gridPane.add(deadline, 0, 1);
        gridPane.add(deadlineText, 1, 1);
        gridPane.add(resp, 0, 2);
        gridPane.add(respText, 1, 2);

        gridPane.add(add, 0, 5);
        gridPane.add(button2, 1, 5);

        add.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        task.setStyle("-fx-font: normal bold 20px 'serif' ");
        deadline.setStyle("-fx-font: normal bold 20px 'serif' ");
        resp.setStyle("-fx-font: normal bold 20px 'serif' ");

        gridPane.setStyle("-fx-background-color: BEIGE;");

        Scene scene = new Scene(gridPane);

        stage.setTitle("Adauga task");

        stage.setScene(scene);

        stage.show();

        add.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                MongoDatabase database = mongo.getDatabase("admin");
                //database.createCollection("users");
                Document document = new Document();
                document.append("task", taskText.getText());
                document.append("deadline", deadlineText.getText());
                document.append("responsabil", respText.getText());

                //Inserting the document into the collection
                database.getCollection("tasks").insertOne(document);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Task-ul a fost adaugat cu succes!");
                alert.showAndWait();
                stage.hide();
                HomePage m = new HomePage();
                m.show();

            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                taskText.setText("");
                deadlineText.setText("");
                respText.setText("");
            }
        });
    }
}