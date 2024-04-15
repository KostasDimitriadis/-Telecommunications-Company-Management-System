package sceneCreators;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{

	// Stage
	static Stage primaryStage;
	// Scenes
	static Scene mainScene, TeleScene, PlanScene,clientsScene,contractScene;


    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        //Main Scene
        SceneCreator mainSceneCreator = new MainSceneCreator(650, 350);
    	mainScene = mainSceneCreator.createScene();

    	//Tele Scene
    	SceneCreator TeleSceneCreator = new TeleSceneCreator(1000,500);
    	TeleScene = TeleSceneCreator.createScene();
    	
    	//Plan Scene
    	SceneCreator PlanSceneCreator = new PlanSceneCreator(1390,650);
    	PlanScene = PlanSceneCreator.createScene();
    	
    	//Client Scene
    	SceneCreator  ClientsSceneCreator = new ClientsSceneCreator(1200,550);
    	clientsScene = ClientsSceneCreator.createScene();
    	
    	//ContractsScene
    	SceneCreator  ContractSceneCreator = new ContractSceneCreator(1500,750);
    	contractScene = ContractSceneCreator.createScene();

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Κατάστημα Παροχής Τηλεπικοινωνιακών Υπηρεσιών");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
    
}