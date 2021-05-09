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

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

class HomePage extends Application {
    @Override
    public void start(Stage stage) {};
    public void show() {
        Stage stage = new Stage();

        Button addTask = new Button("Adauga task");
        addTask.setLayoutX(100);
        addTask.setLayoutY(350);
        if (!Main.admin)
        {
            addTask.setLayoutX(500);
            addTask.setLayoutY(500);
        }
        Button myTask = new Button();
        myTask.setLayoutX(50);
        myTask.setLayoutY(250);
        myTask.setText("Task-urile mele");
        Button oversTask = new Button();
        oversTask.setText("Neatribuite");
        oversTask.setLayoutX(170);
        oversTask.setLayoutY(250);
        myTask.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                MyTask myT = new MyTask();
                myT.show();
                stage.hide();

            }
        });

        oversTask.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


            }
        });

        Text text = new Text();
        text.setFont(new Font(30));
        text.setX(50);
        text.setY(150);
        text.setText("Alege categoria\nde task-uri");
        Group root = new Group(text, myTask, oversTask, addTask);
        stage.setScene(new Scene(root, 300, 500));
        stage.show();
    }
}