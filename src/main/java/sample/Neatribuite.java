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

class Neatribuite extends Application {

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

//        StringBuilder txt = new StringBuilder();
//
//        for (Document cur : collection.find()) {
//            txt.append(cur.toJson());
//        }

        String txt = "";
        String txt2 = "";
        String linie = "";
        String name = "";
        int index;
        int index_name;
        int index_name_2;
        Text text = new Text();
        Text tasks = new Text();
        try {
            while (cursor.hasNext()) {
                txt2 = cursor.next().toJson();
                index_name = txt2.lastIndexOf(':');
                index_name_2 = txt2.lastIndexOf('"');
                name = txt2.substring(index_name + 3, index_name_2);

                if (name.equals("")) {
                    index = txt2.lastIndexOf(',');
                    txt += txt2.substring(54, index) + "\n";
                }
            }
        } finally {
            cursor.close();
        }

        tasks.setText(txt.toString());
        tasks.setFont(new Font(20));
        tasks.setX(50);
        tasks.setY(150);
        text.setFont(new Font(20));
        text.setX(50);
        text.setY(130);
        text.setText("Task-uri neatribuite!");
        Group root = new Group(text, tasks, back);
        stage.setScene(new Scene(root, 600, 500));
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