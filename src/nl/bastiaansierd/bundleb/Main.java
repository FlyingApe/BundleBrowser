package nl.bastiaansierd.bundleb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nl.bastiaansierd.bundleb.logic.BundelBEnvironment;
import nl.bastiaansierd.bundleb.logic.objects.BundelBEnvironmentObject;
import nl.bastiaansierd.datalogger.logic.Logger;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/nl/bastiaansierd/bundleb/ui/views/MainWindow.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("BundleB - An HTML and Markdown page bundling Browser");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/bundel2.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Logger.log("Main.main","BundleB opgestart");

        //omgevingsvariabelen laden
        BundelBEnvironment env = BundelBEnvironment.getInstance();
        try {
            env.initialize();
        } catch (Exception e){
            Logger.log("Main.main", "omgeving kon niet geinitialiseerd worden, nieuw omgevingsobject aangemaakt en opgeslagen in een bundlebEnv.dat");
            env.setEnvironmentObject(new BundelBEnvironmentObject());
        }

        //applicatie opstarten
        launch(args);

        //omgeving afsluiten
        env.close();
        System.out.println();
    }
}
