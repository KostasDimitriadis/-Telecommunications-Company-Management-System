package sceneCreators;

import Core.Client;
import Core.Contract;
import Core.Plan;
import Core.TelecommunicationCompany;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

import java.util.ArrayList;

public abstract class SceneCreator {
	double width;
	double height;

	static ArrayList<TelecommunicationCompany> teleCompsList;
	static ArrayList<Plan> planList;
	static ArrayList<Client> clientList;
	static ArrayList<Contract> contractList;

	public SceneCreator(double width, double height) {

		teleCompsList = new ArrayList<>();
		planList = new ArrayList<>();
		clientList = new ArrayList<>();
		contractList = new ArrayList<>();

		this.width = width;
		this.height = height;
	}
	
	abstract Scene createScene();

}
