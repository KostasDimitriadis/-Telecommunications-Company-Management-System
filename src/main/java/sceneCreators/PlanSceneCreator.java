package sceneCreators;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import Core.LandlinePlan;
import Core.MobilePlan;
import Core.Plan;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlanSceneCreator extends SceneCreator {

	FlowPane buttonFlowPane;

	GridPane rootGrid, inputFieldsGrid,searchGridPane;

	Button newPlanBtn, updatePlanBtn, deletePlanBtn, backBtn,search,resetTable;

	Label providerLbl, talkLbl, costLbl, searchLbl, searchType, searchProvider,typeLbl;
	Label smsLbl, gbLbl, speedLbl, lineTypeLbl, landline, mobile;

	TextField providerField, talkField, costField;
	TextField smsField, gbField, speedField, searchField;

	ComboBox<String> typeCombo;

	TableView<Plan> planTableView;

	ComboBox<String> sideComboBox, searchComboBox;

	/**
	 * @param width
	 * @param height
	 */

	public PlanSceneCreator(double width, double height) {

		super(width, height);

		//GridPane : root
		rootGrid = new GridPane();
		rootGrid.setPadding(new Insets(10, 10, 10, 10));

		rootGrid.setVgap(10);
		rootGrid.setHgap(10);

			//set rootGridPane height
			RowConstraints row1 = new RowConstraints();
			RowConstraints row2 = new RowConstraints();
			RowConstraints row3 = new RowConstraints();
			rootGrid.getRowConstraints().add(row1);
			rootGrid.getRowConstraints().add(row2);
			rootGrid.getRowConstraints().add(row3);
			row1.setPrefHeight(500);
			row2.setPrefHeight(50);
			row1.setPercentHeight(250);
			row2.setPercentHeight(25);
			row3.setPrefHeight(250);
			row3.setPercentHeight(125);

			//Root grid pane width
			ColumnConstraints column1 = new ColumnConstraints();
			ColumnConstraints column2 = new ColumnConstraints();
			rootGrid.getColumnConstraints().add(column1);
			rootGrid.getColumnConstraints().add(column2);
			column1.setPrefWidth(850);
			column2.setPrefWidth(400);
			column1.setPercentWidth(400);
			column2.setPercentWidth(200);

		// GridPane: Input Fields
		inputFieldsGrid = new GridPane();

			//Input fields GridPane width
			ColumnConstraints Column1 = new ColumnConstraints();
			ColumnConstraints Column2 = new ColumnConstraints();
			inputFieldsGrid.getColumnConstraints().add(Column1);
			inputFieldsGrid.getColumnConstraints().add(Column2);
			Column1.setPrefWidth(500);
			Column2.setPrefWidth(400);
			Column1.setPercentWidth(250);
			Column2.setPercentWidth(200);

		inputFieldsGrid.setAlignment(Pos.TOP_RIGHT);

		inputFieldsGrid.setVgap(10);
		inputFieldsGrid.setHgap(10);

		inputFieldsGrid.setPadding(new Insets(5, 5, 5, 5));

		rootGrid.add(inputFieldsGrid, 1, 0, 1, 1);

		//Search GridPane

		searchGridPane = new GridPane();
		searchGridPane.setPadding(new Insets(5, 5, 5, 5));
		searchGridPane.setHgap(10);
		searchGridPane.setVgap(10);
		searchGridPane.setAlignment(Pos.CENTER);

		//Search GridPane width
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();

		searchGridPane.getColumnConstraints().add(col1);
		searchGridPane.getColumnConstraints().add(col2);

		col1.setPrefWidth(40);
		col1.setPercentWidth(20);
		col2.setPrefWidth(90);
		col2.setPercentWidth(45);

		rootGrid.add(searchGridPane,0,2);

		//Button FlowPane

		buttonFlowPane = new FlowPane();
		buttonFlowPane.setHgap(10);
		rootGrid.add(buttonFlowPane, 0, 1, 1, 1);
		buttonFlowPane.setAlignment(Pos.CENTER);

		//Labels

		providerLbl = new Label("Πάροχος: ");
		talkLbl = new Label("Λεπτά δωρεάν κλήσεων:\n(σταθερά και κινητά)");
		costLbl = new Label("Κόστος προγράμματος: ");
		typeLbl = new Label("Τύπος προγράμματος:");

		//Extra Labels

			//Mobile labels

			smsLbl = new Label("Δωρεάν SMS: ");
			gbLbl = new Label("Δωρεάν GB:\n(ανά μήνα χρήσης)");
			//Landline labels

			speedLbl = new Label("Ταχύτητα Γραμμής: ");
			lineTypeLbl = new Label("Τύπος γραμμής:\n(ADSL ή VDSL)");

			mobile = new Label("Στοιχεία προγράμματος κινητής τηλεφωνίας:");
			mobile.setFont(Font.font("normal", FontWeight.BOLD, 12));

			landline = new Label("Στοιχεία προγράμματος σταθερής τηλεφωνίας:");
			landline.setFont(Font.font("normal", FontWeight.BOLD, 12));

		inputFieldsGrid.add(providerLbl,0,0);
		inputFieldsGrid.add(talkLbl,0,1);
		inputFieldsGrid.add(costLbl,0,2);
		inputFieldsGrid.add(typeLbl,0,3);

		//Fields

		providerField = new TextField();
		talkField = new TextField();
		costField = new TextField();

		inputFieldsGrid.add(providerField,1,0);
		inputFieldsGrid.add(talkField,1,1);
		inputFieldsGrid.add(costField,1,2);

			//Extra Fields

				//Mobile Fields

				smsField = new TextField();
				gbField = new TextField();

				//Landline Fields

				speedField = new TextField();
				typeCombo = new ComboBox<>();
				typeCombo.getItems().addAll("ADSL","VDSL");
				typeCombo.setPromptText("Τύπος γραμμής");


		//Searching

		searchLbl = new Label("Αναζητήστε πρόγραμμα:");
		searchLbl.setFont(Font.font("normal", FontWeight.BOLD, 12));
		searchGridPane.add(searchLbl,0,0);

		searchProvider = new Label("Πάροχος:");
		searchGridPane.add(searchProvider,0,1);

		searchField = new TextField();
		searchField.setPromptText("Πληκτρολογήστε τον πάροχο του προγράμματος που ψάχνετε...");
		searchField.setTooltip(new Tooltip("Πληκτρολογήστε τον πάροχο του προγράμματος που ψάχνετε..."));
		searchGridPane.add(searchField,1,1);

		searchType = new Label("Τύπος προγράμματος:");
		searchGridPane.add(searchType,0,2);

		searchComboBox = new ComboBox<>();
		searchComboBox.getItems().addAll("Σταθερής","Κινητής", "Καμία Επιλογή");
		searchComboBox.setPromptText("Επιλέξτε τύπο προγράμματος");

		searchGridPane.add(searchComboBox,1,2);

		search = new Button("Αναζήτηση");

		searchGridPane.add(search,2,1);

		search.setOnMouseClicked(e -> {

			if( ! (planList.isEmpty()) ){

				if ( !(searchField.getText().isBlank()) || searchComboBox.getValue()!=null) {
					// if at least one of them is filled

					searchPlan(searchComboBox.getValue(),searchField.getText());

				} else {

					Alert alertType = new Alert(Alert.AlertType.INFORMATION);
					alertType.setTitle("Μη έγκυρη αναζήτηση");
					alertType.setResizable(true);
					alertType.getDialogPane().setPrefSize(480, 100);
					alertType.setContentText("Παρακαλώ αναζητήστε πρόγραμμα με έναν από τους δύο τρόπους.");
					alertType.show();

				}

			}else {

				Alert alertType = new Alert(Alert.AlertType.INFORMATION);
				alertType.setTitle("Δεν υπάρχουν προγράμματα");
				alertType.setContentText("Δεν υπάρχουν καταχωρημένα προγράμματα στο σύστημα.");
				alertType.show();

			}


		});


		//Basic Buttons

		newPlanBtn = new Button("Δημιουργία Προγράμματος");
		newPlanBtn.setOnMouseClicked(e->{

			Random pass = new Random();

			String name = providerField.getText();
			String min = talkField.getText();
			String cost = costField.getText();

			if (sideComboBox.getValue()!=null) {

				if (sideComboBox.getValue().equals("Σταθερής")) {

					String speed = speedField.getText();
					String type = typeCombo.getValue();

					if(checkLandlineFields(name,min,cost,speed,type)){

						try {

							createLandlinePlan(pass,name,min,cost,speed,type);

						} catch (NumberFormatException event) {

							Alert alertType = new Alert(Alert.AlertType.ERROR);
							alertType.setTitle("Λάθος στοιχεία");
							alertType.setContentText("Ελέγξτε ξανά τα πεδία:\n\n'Λεπτά δωρεάν κλήσεων'\n" +
									"'Κόστος προγράμματος'\n'Ταχύτητα Γραμμής'");
							alertType.show();

						}
					}else {

						Alert alertType = new Alert(Alert.AlertType.INFORMATION);
						alertType.setTitle("Άδεια πεδία");
						alertType.setContentText("Παρακαλώ συμπληρώστε όλα τα στοιχεία.");
						alertType.show();

					}


				}else{

					String sms = smsField.getText();
					String mb = gbField.getText();

					if(checkMobileFields(name,min,cost,sms,mb)){

						try {

							createMobilePlan(pass,name,min,cost,sms,mb);

						} catch (NumberFormatException event) {

							Alert alertType = new Alert(Alert.AlertType.ERROR);
							alertType.setTitle("Λάθος στοιχεία");
							alertType.setContentText("Ελέγξτε ξανά τα πεδία:\n\n'Δωρεάν λεπτά ομιλίας'\n" +
									"'Κόστος προγράμματος'\n'Δωρεάν SMS'\n'Δωρεάν MB'");
							alertType.show();


						}
					}else {

						Alert alertType = new Alert(Alert.AlertType.INFORMATION);
						alertType.setTitle("Άδεια πεδία");
						alertType.setContentText("Παρακαλώ συμπληρώστε όλα τα στοιχεία.");
						alertType.show();

					}

				}

			}else{

				if(name.isBlank() || min.isBlank() || cost.isBlank()){

					Alert alertType = new Alert(Alert.AlertType.INFORMATION);
					alertType.setTitle("Άδεια πεδία");
					alertType.setContentText("Παρακαλώ συμπληρώστε όλα τα στοιχεία.");
					alertType.show();

				}else{

					Alert alertType = new Alert(Alert.AlertType.INFORMATION);
					alertType.setTitle("Λάθος στοιχεία");
					alertType.setContentText("Παρακαλώ επιλέξτε τύπο προγράμματος.");
					alertType.show();

				}

			}

		});


		updatePlanBtn = new Button("Ενημέρωση");
		updatePlanBtn.setOnMouseClicked(e->{

			String name = providerField.getText();
			String talk = talkField.getText();
			String cost = costField.getText();

			Plan selectedPlan = planTableView.getSelectionModel().getSelectedItem();

			if(selectedPlan!=null){

				if(selectedPlan instanceof MobilePlan){

					String sms = smsField.getText();
					String gb = gbField.getText();

					if(checkMobileFields(name,talk,cost,sms,gb)){

						updatePlan(selectedPlan,name,talk,cost);

					}else {

						Alert alertType = new Alert(Alert.AlertType.INFORMATION);
						alertType.setTitle("Άδεια πεδία");
						alertType.setContentText("Παρακαλώ συμπληρώστε όλα τα στοιχεία");
						alertType.show();

					}
				}else{

					String speed = speedField.getText();
					String type = typeCombo.getValue();

					if(checkLandlineFields(name,talk,cost,speed,type)){

						updatePlan(selectedPlan,name,talk,cost);

					}else {

						Alert alertType = new Alert(Alert.AlertType.INFORMATION);
						alertType.setTitle("Άδεια πεδία");
						alertType.setContentText("Παρακαλώ συμπληρώστε όλα τα στοιχεία");
						alertType.show();

					}

				}

			}else{

				Alert alertType = new Alert(Alert.AlertType.INFORMATION);
				alertType.setTitle("Μη επιλεγμένη πρόγραμμα");
				alertType.setContentText("Παρακαλώ επιλέξτε ένα πρόγραμμα από τον πίνακα.");
				alertType.show();

			}


		});

		deletePlanBtn = new Button("Διαγραφή");
		deletePlanBtn.setOnMouseClicked(e->{

			Plan selectedPlan = planTableView.getSelectionModel().getSelectedItem();

			if(selectedPlan!=null){

				deletePlan(selectedPlan);

			}else{

				Alert alertType = new Alert(Alert.AlertType.INFORMATION);
				alertType.setTitle("Επιλέξτε πρόγραμμα");
				alertType.setContentText("Παρακαλώ επιλέξτε πρόγραμμα απο τον πίνακα.");
				alertType.show();

			}

		});

		buttonFlowPane.getChildren().addAll(newPlanBtn,updatePlanBtn,deletePlanBtn);

		//Resetting the table

		resetTable = new Button("Επαναφορά πίνακα");
		buttonFlowPane.getChildren().add(resetTable);
		resetTable.setOnMouseClicked(e->{

			syncTable();
			clearFields();

		});

		//back button
		
		backBtn = new Button("Επιστροφή");
		backBtn.setOnMouseClicked(e->{

			App.primaryStage.setTitle("Κατάστημα Παροχής Τηλεπικοινωνιακών  Υπηρεσιών");
			App.primaryStage.setScene(App.mainScene);

		});

		rootGrid.add(backBtn,1,1);


		//input fields Grid Pane ComboBox

		sideComboBox = new ComboBox<>();
		sideComboBox.getItems().addAll("Σταθερής","Κινητής");
		sideComboBox.setPromptText("Τύπος προγράμματος");
		inputFieldsGrid.add(sideComboBox,1,3);

		sideComboBox.setOnAction(e ->{

			if(sideComboBox.getValue().equals("Σταθερής")) {
				
				speedField.clear();

				inputFieldsGrid.getChildren().removeAll(mobile,smsLbl,gbLbl,smsField,gbField);

				inputFieldsGrid.add(landline,0,4,2,1);
				inputFieldsGrid.add(speedLbl, 0, 5);
				inputFieldsGrid.add(lineTypeLbl, 0, 6);

				inputFieldsGrid.add(speedField, 1, 5);
				inputFieldsGrid.add(typeCombo, 1, 6);

				rootGrid.getChildren().remove(inputFieldsGrid);
				rootGrid.add(inputFieldsGrid, 1, 0);

			}else if(sideComboBox.getValue().equals("Κινητής")) {

				smsField.clear();
                gbField.clear();

				inputFieldsGrid.getChildren().removeAll(landline,speedLbl, lineTypeLbl,speedField,typeCombo);

				inputFieldsGrid.add(mobile,0,4,2,1);
				inputFieldsGrid.add(smsLbl, 0, 5);
				inputFieldsGrid.add(gbLbl, 0, 6);

				inputFieldsGrid.add(smsField, 1, 5);
				inputFieldsGrid.add(gbField, 1, 6);

				rootGrid.getChildren().remove(inputFieldsGrid);
				rootGrid.add(inputFieldsGrid, 1, 0);

			}

		});

		
		//TableView

		planTableView = new TableView<>();

		rootGrid.add(planTableView, 0, 0);

		planTableView.setPlaceholder(new Label("Δεν υπάρχουν καταχωρημένα προγράμματα στό σύστημα."));

		TableColumn<Plan, Integer> passColumn = new TableColumn<>("Κωδικός");
		passColumn.setCellValueFactory(new PropertyValueFactory<>("specialPass"));
		passColumn.setPrefWidth(60);

		TableColumn<Plan, String> providerColumn = new TableColumn<>("Πάροχος");
		providerColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
		providerColumn.setPrefWidth(70);


		TableColumn<Plan, String> freeTalkColumn = new TableColumn<>("Δωρεάν Λεπτά Ομιλίας");
		freeTalkColumn.setCellValueFactory(new PropertyValueFactory<>("freeTalk"));
		freeTalkColumn.setPrefWidth(150);

		TableColumn<Plan, String> planCostColumn = new TableColumn<>("Κόστος");
		planCostColumn.setCellValueFactory(new PropertyValueFactory<>("planCost"));
		planCostColumn.setPrefWidth(60);

		planTableView.getColumns().addAll(passColumn,providerColumn,freeTalkColumn,planCostColumn);

		//Organise mobilePlans and landlinePlans on planTableView
		TableColumn<Plan,String> planTypeStaticColumn = new TableColumn<>("Είδος Προγράμματος");
		TableColumn<Plan,String> landlineStaticColumn = new TableColumn<>("Landline");

		TableColumn<Plan,String> speedColumn = new TableColumn<>("Ταχύτητα Γραμμής (Mbps)");
		speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
		speedColumn.setPrefWidth(200);

		TableColumn<Plan,String> lineTypeColumn = new TableColumn<>("Τύπος Γραμμής");
		lineTypeColumn.setCellValueFactory(new PropertyValueFactory<>("lineType"));
		lineTypeColumn.setPrefWidth(100);

		landlineStaticColumn.getColumns().addAll(speedColumn,lineTypeColumn);

		TableColumn<Plan,String> mobileStaticColumn = new TableColumn<>("Mobile");
		TableColumn<Plan,String> freeSmsColumn = new TableColumn<>("Δωρεάν SMS");
		freeSmsColumn.setCellValueFactory(new PropertyValueFactory<>("freeSms"));
		freeSmsColumn.setPrefWidth(100);

		TableColumn<Plan,String> freeGbColumn = new TableColumn<>("Δωρεάν Δεδομένα Gb");
		freeGbColumn.setCellValueFactory(new PropertyValueFactory<>("freeGb"));
		freeGbColumn.setPrefWidth(150);

		mobileStaticColumn.getColumns().addAll(freeSmsColumn,freeGbColumn);

		planTypeStaticColumn.getColumns().addAll(landlineStaticColumn,mobileStaticColumn);

		planTableView.getColumns().addAll(planTypeStaticColumn);

		planTableView.setOnMouseClicked(e->{

			Plan selectedPlan = planTableView.getSelectionModel().getSelectedItem();
			if(selectedPlan!=null) {

				providerField.setText(selectedPlan.getProvider());
				talkField.setText(String.valueOf(selectedPlan.getFreeTalk()));
				costField.setText(String.valueOf(selectedPlan.getPlanCost()));
				if(selectedPlan instanceof MobilePlan){

					smsField.setText(String.valueOf(((MobilePlan) selectedPlan).getFreeSms()));
					gbField.setText(String.valueOf(((MobilePlan) selectedPlan).getFreeGb()));

					inputFieldsGrid.getChildren().removeAll(mobile,smsLbl,gbLbl,smsField,gbField,landline,speedLbl, lineTypeLbl,speedField,typeCombo);

					inputFieldsGrid.add(mobile,0,4,2,1);
					inputFieldsGrid.add(smsLbl, 0, 5);
					inputFieldsGrid.add(gbLbl, 0, 6);

					inputFieldsGrid.add(smsField, 1, 5);
					inputFieldsGrid.add(gbField, 1, 6);
					
					rootGrid.getChildren().remove(inputFieldsGrid);
					rootGrid.add(inputFieldsGrid, 1, 0);


				}
				else{

					speedField.setText(String.valueOf(((LandlinePlan) selectedPlan).getSpeed()));
					typeCombo.setValue(String.valueOf(((LandlinePlan) selectedPlan).getLineType()));

					inputFieldsGrid.getChildren().removeAll(mobile,smsLbl,gbLbl,smsField,gbField,landline,speedLbl, lineTypeLbl,speedField,typeCombo);

					inputFieldsGrid.add(landline,0,4,2,1);
					inputFieldsGrid.add(speedLbl, 0, 5);
					inputFieldsGrid.add(lineTypeLbl, 0, 6);

					inputFieldsGrid.add(speedField, 1, 5);
					inputFieldsGrid.add(typeCombo, 1, 6);

					rootGrid.getChildren().remove(inputFieldsGrid);
					rootGrid.add(inputFieldsGrid, 1, 0);

				}
			}

		});

	}//end of constructor


	//Methods

	private void createMobilePlan(Random pass, String name, String min, String cost, String sms, String mb) {

		Plan plan = new MobilePlan(pass.nextInt(9999999), name, Integer.parseInt(min),
				Double.parseDouble(cost), Integer.parseInt(sms), Integer.parseInt(mb));
		planList.add(plan);

		clearFields();
		syncTable();

	}

	private void createLandlinePlan(Random pass, String name, String min, String cost, String speed, String type) {

		Plan plan = new LandlinePlan(pass.nextInt(99999999), name, Integer.parseInt(min),
				Double.parseDouble(cost), Integer.parseInt(speed), type);

		planList.add(plan);

		clearFields();
		syncTable();

	}

	private void updatePlan(Plan plan,String name,String talk, String cost) {

		Boolean found = true;

		for(Plan item: planList){

			if(item.getProvider().equals(name) && plan.getSpecialPass()==item.getSpecialPass()){

				found = true;

				plan.setFreeTalk(Integer.parseInt(talk));
				plan.setPlanCost(Double.parseDouble(cost));

				if(item instanceof MobilePlan){
					try{

						((MobilePlan) plan).setFreeSms(Integer.parseInt(smsField.getText()));
						((MobilePlan) plan).setFreeGb(Double.parseDouble(gbField.getText()));

						break;


					}catch (NumberFormatException event) {

						Alert alertType = new Alert(Alert.AlertType.ERROR);
						alertType.setTitle("Λάθος στοιχεία");
						alertType.setContentText("Η τιμή που δώθηκε ως <Δωρεάν SMS> ή <Δωρεάν Gb> δεν είναι έγκυρη.\n"+ event.getMessage());
						alertType.show();
						break;

					}
				}else if (item instanceof LandlinePlan){

					try{

						((LandlinePlan) plan).setSpeed(Integer.parseInt(speedField.getText()));
						((LandlinePlan) plan).setLineType(typeCombo.getValue());

						break;

					}catch (NumberFormatException event) {

						Alert alertType = new Alert(Alert.AlertType.ERROR);
						alertType.setTitle("Λάθος στοιχεία");
						alertType.setContentText("Η τιμή που δώθηκε ως τηλέφωνο δεν είναι έγκυρη.\n"+ event.getMessage());
						alertType.show();
						break;

					}

				}

			}

		}

		if(!found ){

			Alert alertType = new Alert(Alert.AlertType.ERROR);
			alertType.setTitle("Λάθος στοιχεία");
			alertType.setContentText("Δεν μπορείτε να επεξεργαστείτε την επωνυμία του παρόχου.");
			alertType.show();

		}

		clearFields();
		syncTable();

	}

	public void searchPlan(String searchComboChoice, String searchName) {

        List<Plan> plans = planTableView.getItems();

        planTableView.setPlaceholder(new Label("Δεν βρέθηκαν προγράμματα με χαρακτηριστικά:\n<Τύπο Προγράματος> = "
                + searchComboChoice + "\n<Πάροχο> = " + searchName));

        plans.clear();
            
        if((searchComboChoice == null || searchComboChoice.equals("Καμία Επιλογή")) && !(searchName.isBlank()) ) {

            //search by provider

            for (Plan p : planList) {

                if (p.getProvider().equals(searchName)) {

                    plans.add(p);
                    planTableView.getSelectionModel().select(p);
                    searchField.clear();

                }

            }

        }

        //user has only selected a value from search combo box

        if(searchComboChoice !="Καμία Επιλογή" && searchComboChoice!=null && searchName.isBlank() ) {

            // search by plan type

            if (searchComboChoice == "Κινητής") {

                for (Plan p : planList) {

                    if (p instanceof MobilePlan) {

                        plans.add((MobilePlan) p);
						planTableView.getSelectionModel().select(p);
						searchField.clear();

                    }

                }

            } else if (searchComboChoice == "Σταθερής") {

                for (Plan p : planList) {

                    if (p instanceof LandlinePlan) {

                        plans.add((LandlinePlan) p);
						planTableView.getSelectionModel().select(p);
						searchField.clear();

                    }

                }

            }

        }

        //user has used both the combo box and search field
		//date must match

        if (searchComboChoice != null && !(searchName.isBlank()) && !(searchComboChoice.equals("Καμία Επιλογή")) ){
            
                // search by provider AND by plan type

                if (searchComboChoice == "Κινητής") {

                    for (Plan p : planList) {

                        if (p instanceof MobilePlan && p.getProvider().equals(searchName)) {

                            plans.add((MobilePlan) p);
							planTableView.getSelectionModel().select(p);
							searchField.clear();

                        }

                    }

                } else if (searchComboChoice == "Σταθερής") {

                    for (Plan p : planList) {

                        if (p instanceof LandlinePlan && p.getProvider().equals(searchName)) {

                            plans.add((LandlinePlan) p);
							planTableView.getSelectionModel().select(p);
							searchField.clear();

                        }

                    }

                }  
        
        }
        
	}

	private boolean checkMobileFields(String name, String min, String cost, String sms, String mb) {

		if(name.isBlank() || min.isBlank() || cost.isBlank() || sms.isBlank() || mb.isBlank()){

			return false;

		}else {

			return true;

		}

	}

	private boolean checkLandlineFields(String name, String min, String cost, String speed, String type) {

		if(name.isBlank() || min.isBlank() || cost.isBlank() || speed.isBlank() || type==null){

			return false;

		}else {

			return true;

		}

	}

	public void deletePlan(Plan selectedPlan){

		if(contractList.isEmpty()){

			// the are no contracts
			//the plan can be deleted if it's found

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Διαγραφή προγράμματος;");
			alert.setContentText("Είστε σιγουροί ότι θέλετε να διαγράψετε αυτό το πρόγραμμα;");

			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				planList.remove(selectedPlan);

				clearFields();
				syncTable();

			}

		}else{

			Alert alertType = new Alert(Alert.AlertType.ERROR);
			alertType.setTitle("Σφάλμα");
			alertType.setHeaderText("Δεν μπορείτε να διαγράψετε προγράμματα!");
			alertType.setContentText("Υπάρχουν καταχωρημένα συμβόλαια στο σύστημα.");
			alertType.show();

		}

	}
	
	public void clearFields(){

		providerField.clear();
		talkField.clear();
		costField.clear();
		if(sideComboBox.getValue()=="Σταθερής") {
			speedField.clear();
		}
		else if(sideComboBox.getValue()=="Κινητής") {
			gbField.clear();
			smsField.clear();
		}
		searchField.clear();

	}

	public void syncTable(){

		List<Plan> items = planTableView.getItems();

		items.clear();

		for (Plan item : planList){

			if(item instanceof Plan){

				items.add((Plan) item);

			}

		}

	}

	@Override
	Scene createScene() {
		return new Scene(rootGrid, width, height);
	}


}
