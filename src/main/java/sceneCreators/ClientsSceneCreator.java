package sceneCreators;

import Core.Contract;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import Core.Client;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ClientsSceneCreator extends SceneCreator {


	FlowPane buttonFlowPane;

	GridPane rootGrid, inputFieldsGrid;

	Button newClientBtn, searchClientBtn, updateClientBtn, deleteClientBtn, backBtn, resetTable;

	Label idLbl, afmLbl, fullNameLbl, statusLbl, addressLbl, phoneLbl, emailLbl, searchLbl;

	TextField idField, afmField, fullNameField, addressField, phoneField, emailField, searchAfm, searchId;

	TableView<Client> clientsTableView;

	ComboBox<String> statusCombo;

	public ClientsSceneCreator(double width, double height) {

		super(width, height);

		// GridPane: root

		rootGrid = new GridPane();

		rootGrid.setPadding(new Insets(10, 10, 10, 10));

		rootGrid.setVgap(10);
		rootGrid.setHgap(10);

			//set rootGridPane height
			RowConstraints row1 = new RowConstraints();
			RowConstraints row2 = new RowConstraints();
			rootGrid.getRowConstraints().add(row1);
			rootGrid.getRowConstraints().add(row2);
			row1.setPrefHeight(500);
			row2.setPrefHeight(50);
			row1.setPercentHeight(250);
			row2.setPercentHeight(25);

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


		//Button FlowPane

		buttonFlowPane = new FlowPane();

		buttonFlowPane.setHgap(10);
		buttonFlowPane.setAlignment(Pos.CENTER);

		rootGrid.add(buttonFlowPane, 0, 1);


		// Labels

		idLbl = new Label("Αριθμός Δελτίου Ταυτότητας: ");
		afmLbl = new Label("ΑΦΜ: ");
		fullNameLbl = new Label("Ονοματεπώνυμο: ");
		statusLbl = new Label("Κατάσταση: ");
		addressLbl = new Label("Διεύθυνση: ");
		phoneLbl = new Label("Τηλέφωνο: ");
		emailLbl = new Label("Email: ");


		// Fields

		idField = new TextField();
		afmField = new TextField();
		fullNameField = new TextField();

		statusCombo = new ComboBox<>();
		statusCombo.getItems().addAll("Επαγγελματίας","Φοιτητής","Ιδιώτης");
		statusCombo.setPromptText("Κατάσταση");

		addressField = new TextField();
		phoneField = new TextField();
		emailField = new TextField();


			inputFieldsGrid.add(idLbl, 0, 0);
			inputFieldsGrid.add(idField, 1, 0);
			inputFieldsGrid.add(afmLbl, 0, 1);
			inputFieldsGrid.add(afmField, 1, 1);
			inputFieldsGrid.add(fullNameLbl, 0, 2);
			inputFieldsGrid.add(fullNameField, 1, 2);
			inputFieldsGrid.add(statusLbl, 0, 3);
			inputFieldsGrid.add(statusCombo, 1, 3);
			inputFieldsGrid.add(addressLbl, 0, 4);
			inputFieldsGrid.add(addressField, 1, 4);
			inputFieldsGrid.add(phoneLbl, 0, 5);
			inputFieldsGrid.add(phoneField, 1, 5);
			inputFieldsGrid.add(emailLbl, 0, 6);
			inputFieldsGrid.add(emailField, 1, 6);


        //Searching

		searchLbl = new Label("Αναζητήστε πελάτη:");
		searchLbl.setFont(Font.font("normal", FontWeight.BOLD, 12));

		inputFieldsGrid.add(searchLbl,0,9);

		searchId = new TextField();
		searchId.setPromptText("Αριθμός ταυτότητάς...");

		searchAfm = new TextField();
		searchAfm.setPromptText("Α.Φ.Μ...");

		inputFieldsGrid.add(searchId,0,10);
		inputFieldsGrid.add(searchAfm,0,11);

		searchClientBtn = new Button("Ανάζητηση");
		inputFieldsGrid.add(searchClientBtn,1,11);

		searchClientBtn.setOnMouseClicked(e->{

			syncTable();

			Boolean foundAfm = false;
			Boolean foundId = false;
			Boolean match = false;

			String afm = searchAfm.getText();
			String id = searchId.getText();

			if(afm.isBlank() && id.isBlank()){

				Alert alertType = new Alert(Alert.AlertType.INFORMATION);
				alertType.setTitle("Άδεια πεδία");
				alertType.setContentText("Παρακαλώ συμπληρώστε έστω ένα πεδίο.");
				alertType.show();

			}else if(id.isBlank()) {

				for (Client item : clientList) {

					if (Integer.parseInt(afm) == item.getAfm()) {

						updateTable(item);

						foundAfm = true;

					}

				}

				if (!foundAfm) {

					Alert alertType = new Alert(Alert.AlertType.INFORMATION);
					alertType.setTitle("Δεν βρέθηκε πελάτης");
					alertType.setContentText("Δεν υπάρχει πελάτης με αυτό το Α.Φ.Μ στο σύστημα.");
					alertType.show();

				}

			}else if(afm.isBlank()) {

				for (Client item : clientList) {

					if (id.equals(item.getIdNum())) {

						updateTable(item);
						foundId = true;

					}

				}

				if (!foundId) {

					Alert alertType = new Alert(Alert.AlertType.INFORMATION);
					alertType.setTitle("Δεν βρέθηκε πελάτης");
					alertType.setContentText("Δεν υπάρχει πελάτης με αυτό τον Α.Τ.");
					alertType.show();

				}

			}else{ // both search fields are full, the data must match to a certain client's data

				for(Client item : clientList){

					if(Integer.parseInt(afm) == item.getAfm() && id.equals(item.getIdNum())){

						updateTable(item);
						match = true;
						break;

					}

				}

				if(!match){

					Alert alertType = new Alert(Alert.AlertType.INFORMATION);
					alertType.setTitle("Λανθασμένα Στοιχεία");
					alertType.setContentText("Ο Α.Τ και ο Α.Φ.Μ δεν ταιριάζουν.");
					alertType.show();

				}
			}

		});


		//Basic Buttons

		newClientBtn = new Button("Καταχώρηση νέου πελάτη");

		newClientBtn.setOnMouseClicked(e->{

			String id = idField.getText();
			String afm = afmField.getText();
			String fullName = fullNameField.getText();
			String address = addressField.getText();
			String status = statusCombo.getValue();
			String phone = phoneField.getText();
			String email = emailField.getText();

			if(checkFields(id,afm,fullName,address,status,phone,email)){

				if(validatePhone(phone)){

					createClient(id,afm,fullName,address,status,phone,email);

				}else{

					Alert alertType = new Alert(Alert.AlertType.ERROR);
					alertType.setTitle("Λάθος στοιχεία");
					alertType.setContentText("Ελέγξτε ξανά το τηλέφωνο που δηλώσατε.");
					alertType.show();

				}

			}else {

				Alert alertType = new Alert(Alert.AlertType.INFORMATION);
				alertType.setTitle("Άδεια πεδία");
				alertType.setContentText("Παρακαλώ συμπληρώστε όλα τα στοιχεία.");
				alertType.show();

			}

		});


		updateClientBtn = new Button("Ενημέρωση Πελάτη");

		updateClientBtn.setOnMouseClicked(e->{

			String id = idField.getText();
			String afm = afmField.getText();
			String fullName = fullNameField.getText();
			String address = addressField.getText();
			String status = statusCombo.getValue();
			String phone = phoneField.getText();
			String email = emailField.getText();

			Client selectedClient = clientsTableView.getSelectionModel().getSelectedItem();

			if(selectedClient!=null){

				if(checkFields(id,afm,fullName,address,status,phone,email)){

					if(validatePhone(phone)){

						updateClient(selectedClient,id,afm,fullName,address,status,phone,email);

					}else{

						Alert alertType = new Alert(Alert.AlertType.ERROR);
						alertType.setTitle("Λάθος στοιχεία");
						alertType.setContentText("Ελέγξτε ξανά το τηλέφωνο που δηλώσατε.");
						alertType.show();

					}


				}else {

					Alert alertType = new Alert(Alert.AlertType.INFORMATION);
					alertType.setTitle("Άδεια πεδία");
					alertType.setContentText("Παρακαλώ συμπληρώστε όλα τα στοιχεία.");
					alertType.show();

				}

			}else{

				Alert alertType = new Alert(Alert.AlertType.INFORMATION);
				alertType.setTitle("Επιλέξτε πελάτη");
				alertType.setContentText("Παρακαλώ επιλέξτε πελάτη απο τον πίνακα.");
				alertType.show();

			}


		});

		deleteClientBtn = new Button("Διαγραφή Πελάτη");

		deleteClientBtn.setOnMouseClicked(e->{

			Client selectedClient = clientsTableView.getSelectionModel().getSelectedItem();

			if(selectedClient!=null){

				if(contractList.isEmpty()){

					deleteClient(selectedClient);

				}else{

					for(Contract contract : contractList){

						if(selectedClient.getAfm() == contract.getAfm()){

							Alert alertType = new Alert(Alert.AlertType.ERROR);
							alertType.setTitle("Σφάλμα");
							alertType.setHeaderText("Δεν μπορείτε να διαγράψετε αυτόν τον πελάτη!");
							alertType.setContentText("Υπάρχουν καταχωρημένα συμβόλαια στο σύστημα στο όνομά του.");
							alertType.show();

						}else {

							deleteClient(selectedClient);

							clearFields();
							syncTable();

							break;

						}
					}
				}

				clearFields();
				syncTable();

			}else{

				Alert alertType = new Alert(Alert.AlertType.INFORMATION);
				alertType.setTitle("Επιλέξτε πελάτη");
				alertType.setContentText("Παρακαλώ επιλέξτε πελάτη απο τον πίνακα.");
				alertType.show();

			}

		});


			//Adding the buttons to the FlowPane
			buttonFlowPane.getChildren().addAll(newClientBtn,updateClientBtn,deleteClientBtn);


        //Back button

		backBtn = new Button("Επιστροφή");
		backBtn.setOnMouseClicked(e->{

			App.primaryStage.setTitle("Κατάστημα Παροχής Τηλεπικοινωνιακών Υπηρεσιών");
			App.primaryStage.setScene(App.mainScene);

		});

		rootGrid.add(backBtn, 1, 1);


		//Resetting the table view

		resetTable = new Button("Επαναφορά πίνακα");
		buttonFlowPane.getChildren().add(resetTable);
		resetTable.setOnMouseClicked(e->{

			syncTable();
			clearFields();

		});


		// TableView

		clientsTableView = new TableView<>();

		rootGrid.add(clientsTableView, 0, 0);

		clientsTableView.setPlaceholder(new Label("Δεν υπάρχουν καταχωρημένοι πελάτες στόν σύστημα."));

		TableColumn<Client, String> idColumn = new TableColumn<>("Αριθμός Δελτίου Ταυτότητας");
		idColumn.setPrefWidth(180);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("idNum"));

		TableColumn<Client, String> afmColumn = new TableColumn<>("Α.Φ.Μ. ");
		afmColumn.setCellValueFactory(new PropertyValueFactory<>("afm"));

		TableColumn<Client, String> fullNameColumn = new TableColumn<>("Ονοματεπώνυμο");
		fullNameColumn.setPrefWidth(120);
		fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

		TableColumn<Client, String> statusColumn = new TableColumn<>("Κατάσταση");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

		TableColumn<Client, String> addressColumn = new TableColumn<>("Διεύθυνση");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

		TableColumn<Client, String> phoneColumn = new TableColumn<>("Τηλέφωνο");
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

		TableColumn<Client, String> emailColumn = new TableColumn<>("E-mail");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailColumn.setPrefWidth(150);

		clientsTableView.getColumns().addAll(idColumn,afmColumn,fullNameColumn,statusColumn,
				addressColumn,phoneColumn,emailColumn);

		clientsTableView.setOnMouseClicked(e->{

			Client selectedClient = clientsTableView.getSelectionModel().getSelectedItem();
			if (selectedClient != null) {

				idField.setText(selectedClient.getIdNum());
				afmField.setText(Integer.toString(selectedClient.getAfm()));
				fullNameField.setText(selectedClient.getFullName());
				statusCombo.setValue(selectedClient.getStatus());
				addressField.setText(selectedClient.getAddress());
				phoneField.setText(String.valueOf(selectedClient.getPhoneNum()));
				emailField.setText(selectedClient.getEmail());

			}

		});

	}//end of constructor

	private void updateTable(Client item) {

		syncTable();
		List<Client> items = clientsTableView.getItems();
		items.clear();
		items.add((Client) item);
		clientsTableView.getSelectionModel().select(item);

	}

	//Methods

	@Override
	Scene createScene() {
		return new Scene(rootGrid, width, height);
	}


	// create a new Client object and add it to client list
	public void createClient(String id, String afm, String fullName, String address, String status,String phone,String email) {

		try{

			Client client = new Client(id, Integer.parseInt(afm), fullName, address, status, Long.parseLong(phone), email);
			clientList.add(client);
			clientsTableView.getSelectionModel().select(client);

		}catch (NumberFormatException event){

			Alert alertType = new Alert(Alert.AlertType.ERROR);
			alertType.setTitle("Λάθος στοιχεία");
			alertType.setContentText("Ελέγξτε ξανά το Α.Φ.Μ που εισάγατε.");
			alertType.show();

		}

		clearFields();
		syncTable();


	}

	// Update Client by searching the clientsList (by id) and then update the rest of the fields
	public void updateClient(Client client ,String idnum, String afm, String fullName, String status, String address, String phoneNum,
							 String email) {

		Boolean found = false;

		for (Client c : clientList) {

			try {

				if ((c.getIdNum()).equals(idnum) && c.getAfm()==(Integer.parseInt(afm))) {

					found = true;

					client.setFullName(fullName);
					client.setStatus(status);
					client.setAddress(address);
					client.setPhoneNum(Long.parseLong(phoneNum));
					client.setEmail(email);

					clearFields();
					syncTable();

					break;

				}

			}catch (NumberFormatException event){

				Alert alertType = new Alert(Alert.AlertType.ERROR);
				alertType.setTitle("Λάθος στοιχεία");
				alertType.setContentText("Ελέγξτε ξανά το Α.Φ.Μ που εισάγατε.");
				alertType.show();

			}

		}

		if(!found){

			Alert alertType = new Alert(Alert.AlertType.ERROR);
			alertType.setTitle("Λάθος στοιχεία");
			alertType.setResizable(true);
			alertType.getDialogPane().setPrefSize(480, 100);
			alertType.setContentText("Δεν μπορείτε να επεξεργαστείτε τον αριθμό ταυτότητας ή το Α.Φ.Μ ενός πελάτη.");
			alertType.show();

		}

	}

	// Deletes clients and confirms deletion
	public void deleteClient(Client item) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Διαγραφή προγράμματος;");
		alert.setContentText("Είστε σιγουροί ότι θέλετε να διαγράψετε αυτό τον πελάτη;");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK) {

			clientList.remove(item);

		}

	}

	public Boolean checkFields(String id, String afm, String name, String status, String address, String phone, String email){

		if (id.isBlank() || afm.isBlank() || name.isBlank() || status==null || address.isBlank()|| phone.isBlank() || email.isBlank()){

			return false;

		}
		else {

			return true;

		}

	}

	public boolean validatePhone(String phone){

		long longPhone = Long.parseLong(phone);

		int count = 0;
		while (longPhone!=0) {

			longPhone/= 10;
			++count;
		};

		if(count==10){

			return true;

//			long firstDigit = longPhone/1000000000;
//
//			if(firstDigit==6 || firstDigit==2){
//
//				return true;
//
//			}else{
//
//				return false;
//
//			}

		}else{

			return false;

		}

	}

	// set to empty strings to clear the textFields
	public void clearFields() {
		idField.clear();
		afmField.clear();
		fullNameField.clear();
		addressField.clear();
		phoneField.clear();
		emailField.clear();

		statusCombo.getSelectionModel().clearSelection();
	}

	//Syncs client list's objects with objects in Table
	public void syncTable() {

		List<Client> items = clientsTableView.getItems();
		items.clear();
		for (Client c : clientList) {
			items.add((Client) c);
		}

	}


}//end of class
