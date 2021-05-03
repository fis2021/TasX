package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.sun.istack.internal.NotNull;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.rmi.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Welcome to my application");
        Button reg = new Button();
        reg.setLayoutX(50);
        reg.setLayoutY(250);
        reg.setText("Inregistrare");
        Button log = new Button();
        log.setText("Logare");
        log.setLayoutX(170);
        log.setLayoutY(250);
        reg.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                RegisterForm r = new RegisterForm();
                r.show();

            }
        });

        log.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                LoginForm l = new LoginForm();
                l.show();

            }
        });

        Text text = new Text();
        text.setFont(new Font(45));
        text.setX(50);
        text.setY(150);
        text.setText("Buna ziua!");
        Group root = new Group(text, reg, log);
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {

//        MongoClient mongoClient = new MongoClient("localhost");
//
//        List<String> databases = mongoClient.getDatabaseNames();
//
//        for (String dbName : databases) {
//            System.out.println("- Database: " + dbName);
//
//            DB db = mongoClient.getDB(dbName);
//
//            Set<String> collections = db.getCollectionNames();
//            for (String colName : collections) {
//                System.out.println("\t + Collection: " + colName);
//            }
//        }
//
//        mongoClient.close();

        //        MongoClient mongo = new MongoClient("127.0.0.1", 27017);
//        MongoDatabase db =  mongo.getDatabase("trello");
//        Map<String, Object> commandArguments = new HashMap<>();
//        commandArguments.put("createUser", "adela");
//        commandArguments.put("pwd", "adela12345");
//        String[] roles = { "readWrite" };
//        commandArguments.put("roles", roles);
//        BasicDBObject command = new BasicDBObject(commandArguments);
//        db.runCommand(command);
//        MongoClient mongo = new MongoClient( "localhost" , 27017 );
//
//        MongoCredential credential;
//        credential = MongoCredential.createCredential("sampleUser", "trello",
//                "password".toCharArray());
//        System.out.println("Connected to the database successfully");
//
//        MongoDatabase database = mongo.getDatabase("trello");
//        System.out.println("Credentials ::"+ credential);
        launch(args);
    }
}