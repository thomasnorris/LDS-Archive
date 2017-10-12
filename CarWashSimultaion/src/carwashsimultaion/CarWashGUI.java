// GUI for the CarWashSimulation program

package carwashsimultaion;
import java.awt.event.ActionEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class CarWashGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public TextField hours = new TextField();
    public TextField minutes = new TextField();
    public TextField customers = new TextField();
    public Button runSimulation = new Button("Run Simulation");
    public Alert simComplete = new Alert(AlertType.INFORMATION);
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane mainGrid = new GridPane();
        mainGrid.setHgap(7);
        mainGrid.setVgap(7);
        mainGrid.setPadding(new Insets(10,10,10,10));
        
        mainGrid.add(new Label("Welcome! This program simulates the "
                             + "operation of Ethan's car wash."), 0, 0);
        mainGrid.add(new Label("Hours of the car wash operation to "
                             + "simulate:"), 0, 1);
        mainGrid.add(hours, 1, 1);
        mainGrid.add(new Label("Minutes of the car wash operation to "
                             + "simulate:"), 0, 2);
        mainGrid.add(minutes, 1, 2);
        mainGrid.add(new Label("Initial customers in line at the beginning "
                             + "of the simulation:"), 0, 3);
        mainGrid.add(customers, 1, 3);
        mainGrid.add(runSimulation, 0, 4);
        
        Scene mainScene = new Scene(mainGrid, 500, 150);
        
        simComplete.setTitle("Simulation Complete");
        simComplete.setHeaderText("The simulation was completed "
                                + "successfully.\nResults are below.");
        
        primaryStage.setTitle("Car Wash Simulation");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        runSimulation.setOnAction(new EventHandler<javafx.event.ActionEvent>() 
        {
            public void handle(ActionEvent event) {
                try {
                    CarWashSimulation.runSimulation(hours, minutes, customers,
                                                    simComplete);
                } catch (Exception ex) {
                    System.out.println("There was a problem.");
                }
            }
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    CarWashSimulation.runSimulation(hours, minutes, customers,
                                                    simComplete);
                } catch (Exception ex) {
                    System.out.println("There was a problem.");
                }
            }
        });
    }
}
