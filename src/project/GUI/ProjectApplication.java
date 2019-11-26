/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.LOGIC.DomainController;

/**
 *
 * @author astridvermeulen
 */
public class ProjectApplication extends Application {
    private DomainController dc;
    
    @Override
    public void start(Stage stage) throws Exception {
        dc = new DomainController();
        Parent root = FXMLLoader.load(getClass().getResource("/project/GUI/startWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
