package sample;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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

class MyTask extends Application {

    @Override
    public void start(Stage stage) {};
    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Task-urile mele");
        Button back = new Button("Inapoi");
        back.setLayoutX(50);
        back.setLayoutY(250);

        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("admin");

        MongoCollection<Document> collection = database.getCollection("tasks");
        MongoCursor<Document> cursor = collection.find().iterator();


        Text text = new Text();
        try {
            while (cursor.hasNext()) {
               // JSONObject obj = cursor.next().toJson();

                text.setText(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }

        text.setFont(new Font(20));
        text.setX(50);
        text.setY(150);
        text.setText("Task-urile mele!");
        Group root = new Group(text, back);
        stage.setScene(new Scene(root, 300, 500));
        stage.show();

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.hide();
                HomePage hp = new HomePage();
                hp.show();
            }
        });
    }
}