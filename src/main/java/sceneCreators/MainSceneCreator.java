package sceneCreators;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;


public class MainSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {
	
	// Flow Pane (root node)
	FlowPane rootFlowPane;
	// Main scene buttons
	Button TeleBtn, PlanBtn, ClientBtn, ContractBtn;

	public MainSceneCreator(double width, double height) {
		super(width, height);
		
		rootFlowPane = new FlowPane();
		TeleBtn = new Button("Εταιρίες Τηλεπικοινωνιών");
		PlanBtn = new Button("Προγράμματα Κινητής & Σταθερής Τηλεφωνίας");
		ClientBtn = new Button("Πελάτες");
		ContractBtn = new Button("Συμβόλαια");
    	
    	// attach handle event to buttons
		TeleBtn.setOnMouseClicked(this);
		PlanBtn.setOnMouseClicked(this);
		ClientBtn.setOnMouseClicked(this);
		ContractBtn.setOnMouseClicked(this);
    	
    	// set up Flow pane
    	rootFlowPane.setHgap(10);
    	rootFlowPane.setAlignment(Pos.CENTER);
    	
    	// add dog, cat, human buttons to rootFlowPane
    	rootFlowPane.getChildren().add(TeleBtn);
    	rootFlowPane.getChildren().add(PlanBtn);
    	rootFlowPane.getChildren().add(ClientBtn);
    	rootFlowPane.getChildren().add(ContractBtn);
		
	}
	
	@Override
	public void handle(MouseEvent event) {
		if(event.getSource() == TeleBtn) {
			App.primaryStage.setScene(App.TeleScene);
			App.primaryStage.setTitle("Διαχείριση Εταιριών Τηλεπικοινωνιών");
		}	
		if(event.getSource() == PlanBtn) {
			App.primaryStage.setScene(App.PlanScene);
			App.primaryStage.setTitle("Διαχείριση Προγραμμάτων");
		}
		if(event.getSource() == ClientBtn) {
			App.primaryStage.setScene(App.clientsScene);
			App.primaryStage.setTitle("Διαχείριση Πελατών");
		}
		if(event.getSource()==ContractBtn) {
			App.primaryStage.setScene(App.contractScene);
			App.primaryStage.setTitle("Διαχείριση Συμβολαίων Κινητής ή Σταθερής Τηλεφωνίας");
		}
	}
	
	@Override
	Scene createScene() {
		return new Scene(rootFlowPane, width, height);
	}
	
}
