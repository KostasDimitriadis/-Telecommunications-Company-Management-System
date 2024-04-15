package sceneCreators;

import Core.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ContractSceneCreator extends SceneCreator{

    FlowPane buttonFlowPane;

    GridPane rootGrid, inputFieldsGrid, bottomGrid;

    Button newContractBtn, cancelContract ,backBtn, searchBtn, resetTable;

    Label selectPlanLbl,startingDateLbl,afmLbl, phoneNumLbl,searchLbl,durationLbl,billTypeLbl,paymentLbl;

    TextField afmField, phoneNumField, searchField;

    DatePicker startingDate;

    ComboBox<String> durationCombo ,billTypeCombo, paymentMethodCombo, searchCombo;

    RadioButton mobileRadio,landlineRadio;

    ToggleGroup planTypeGroup;

    TableView<Contract> contractTableView;

    TableView<Plan> selectMobileTable,selectLandLineTable;

    public ContractSceneCreator(double width, double height){

        super(width, height);

        // GridPane: root

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
        row1.setPrefHeight(300);
        row2.setPrefHeight(50);
        row1.setPercentHeight(150);
        row2.setPercentHeight(25);
        row3.setPrefHeight(300);
        row3.setPercentHeight(150);

        //Root grid pane width

        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        rootGrid.getColumnConstraints().add(column1);
        rootGrid.getColumnConstraints().add(column2);
        column1.setPrefWidth(1200);
        column2.setPrefWidth(400);
        column1.setPercentWidth(495);
        column2.setPercentWidth(200);

        // GridPane: Input Fields

        inputFieldsGrid = new GridPane();
        inputFieldsGrid.setPadding(new Insets(10, 10, 10, 10));
        inputFieldsGrid.setVgap(10);
        inputFieldsGrid.setHgap(10);

        //adding input fields GridPane to root GridPane
        rootGrid.add(inputFieldsGrid, 1, 0,1,2);

        //Input fields GridPane width

        ColumnConstraints Column1 = new ColumnConstraints();
        ColumnConstraints Column2 = new ColumnConstraints();
        inputFieldsGrid.getColumnConstraints().add(Column1);
        inputFieldsGrid.getColumnConstraints().add(Column2);
        Column1.setPrefWidth(140);
        Column2.setPrefWidth(130);
        Column1.setPercentWidth(70);
        Column2.setPercentWidth(65);

        //Bottom right gridPane

        bottomGrid = new GridPane();
        bottomGrid.setPadding(new Insets(10, 10, 10, 10));
        bottomGrid.setVgap(10);
        bottomGrid.setHgap(10);

        //Bottom right gridPane width
        ColumnConstraints Col1 = new ColumnConstraints();
        ColumnConstraints Col2 = new ColumnConstraints();
        bottomGrid.getColumnConstraints().add(Col1);
        bottomGrid.getColumnConstraints().add(Col2);
        Col1.setPrefWidth(200);
        Col2.setPrefWidth(150);
        Col1.setPercentWidth(100);
        Col2.setPercentWidth(75);

        //adding bottom GridPane to root GridPane
        rootGrid.add(bottomGrid,1,2,2,1);

        //Button FlowPane

        buttonFlowPane = new FlowPane();
        buttonFlowPane.setAlignment(Pos.CENTER_LEFT);
        buttonFlowPane.setHgap(10);

        //adding button FlowPane to root GridPane
        rootGrid.add(buttonFlowPane, 0, 1);


        //Input fields GridPane nodes

        afmLbl = new Label("ΑΦΜ: ");
        phoneNumLbl = new Label("Τηλέφωνο: ");

        startingDateLbl = new Label("Ημερομηνία επιθυμητής έναρξης:");
        startingDate = new DatePicker();

        durationLbl = new Label("Διάρκεια συμβολάιου:\n(σε μήνες)");
        billTypeLbl = new Label("Τύπος λογαριασμού:");
        paymentLbl = new Label("Τρόπος πληρωμής:");

        //adding the Labels

        inputFieldsGrid.add(afmLbl,0,0);
        inputFieldsGrid.add(phoneNumLbl,0,1);
        inputFieldsGrid.add(startingDateLbl,0,2);
        inputFieldsGrid.add(durationLbl,0,3);
        inputFieldsGrid.add(billTypeLbl,0,4);
        inputFieldsGrid.add(paymentLbl,0,5);

        afmField = new TextField();
        phoneNumField = new TextField();

        //adding the text fields
        inputFieldsGrid.add(afmField,1,0);
        inputFieldsGrid.add(phoneNumField,1,1);
        inputFieldsGrid.add(startingDate,1,2);

        durationCombo = new ComboBox<>();
        durationCombo.getItems().addAll("12 μήνες","24 μήνες");
        durationCombo.setPromptText("Διάρκεια συμβολάιου");

        billTypeCombo = new ComboBox<>();
        billTypeCombo.getItems().addAll("Έντυπος","Ηλεκτρονικός");
        billTypeCombo.setPromptText("Τύπος λογαριασμού");

        paymentMethodCombo = new ComboBox<>();
        paymentMethodCombo.getItems().addAll("Πιστωτική κάρτα","Μετρητά");
        paymentMethodCombo.setPromptText("Τρόπος πληρωμής");

        //adding combo boxes
        inputFieldsGrid.add(durationCombo,1,3);
        inputFieldsGrid.add(billTypeCombo,1,4);
        inputFieldsGrid.add(paymentMethodCombo,1,5);

        //FlowPane nodes

        //plan type radio buttons

        selectPlanLbl = new Label("Επιλέξτε πρόγραμμα: ");
        selectPlanLbl.setFont(Font.font("normal", FontWeight.BOLD, 12));

        mobileRadio = new RadioButton("Κινητής");
        landlineRadio = new RadioButton("Σταθερής");

        planTypeGroup = new ToggleGroup();
        mobileRadio.setToggleGroup(planTypeGroup);

        mobileRadio.setOnMouseClicked(e->{

            clearGrid();
            rootGrid.add(selectMobileTable,0,2);
            syncMobilePlanTable();

        });

        landlineRadio.setToggleGroup(planTypeGroup);

        landlineRadio.setOnAction(e->{

            clearGrid();
            rootGrid.add(selectLandLineTable,0,2);
            syncLandlineTable();

        });

        newContractBtn = new Button("Δημιουργία Συμβολαίου");
        newContractBtn.setOnMouseClicked(e->{

            String afm = afmField.getText();
            String phone = phoneNumField.getText();
            LocalDate date = startingDate.getValue();
            String duration = durationCombo.getValue();
            String type = billTypeCombo.getValue();
            String pay = paymentMethodCombo.getValue();

            if(mobileRadio.isSelected() || landlineRadio.isSelected()){

                if(mobileRadio.isSelected()){

                    Plan selectedPlan = selectMobileTable.getSelectionModel().getSelectedItem();

                    if(selectedPlan!=null){

                        Boolean found = false;

                        if(checkFields(afm,phone,date,duration,type,pay)){

                            if(validatePhone(phone,selectedPlan)){

                                for (Client client : clientList) {

                                    if (Integer.parseInt(afm) == client.getAfm() &&
                                            (Long.parseLong(phone)) == client.getPhoneNum()) {

                                        found = true;

                                        String status = client.getStatus();

                                        createContract(selectedPlan, afm, phone, date, duration, type,
                                                pay, status);

                                        tableSync();
                                        clearFields();

                                        break;

                                    }

                                }

                                if(!found){

                                    Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                                    alertType.setTitle("Μη έγκυρα στοιχεία");
                                    alertType.setResizable(true);
                                    alertType.getDialogPane().setPrefSize(480, 100);
                                    alertType.setContentText("Δεν βρέθηκε πελάτης με αυτό το τηλέφωνο και αυτό το Α.Φ.Μ..");
                                    alertType.show();

                                }

                            }else{

                                Alert alertType = new Alert(Alert.AlertType.ERROR);
                                alertType.setTitle("Λάθος στοιχεία");
                                alertType.setContentText("Ελέγξτε ξανά το τηλέφωνο που δηλώσατε.");
                                alertType.show();

                            }

                        }else{

                            Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                            alertType.setTitle("Άδεια πεδία");
                            alertType.setContentText("Παρακαλώ συμπληρώστε ολα τα στοιχεία.");
                            alertType.show();

                        }


                    }else{

                        Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                        alertType.setTitle("Μη επιλεγμένο πρόγραμμα");
                        alertType.setContentText("Παρακαλώ επιλέξτε ένα πρόγραμμα από τον πίνακα.");
                        alertType.show();

                    }

                }else{

                    Plan selectedPlan = selectLandLineTable.getSelectionModel().getSelectedItem();

                    if(selectedPlan!=null) {

                        Boolean found = false;

                        if(checkFields(afm,phone,date,duration,type,pay)) {

                            if(validatePhone(phone,selectedPlan)){

                                for (Client client : clientList) {

                                    if (Integer.parseInt(afm) == client.getAfm() &&
                                            (Long.parseLong(phone)) == client.getPhoneNum()) {

                                        found = true;

                                        String status = client.getStatus();

                                        createContract(selectedPlan, afm, phone,date, duration, type,
                                                pay, status);

                                        tableSync();
                                        clearFields();

                                        break;

                                    }
                                }

                                if(!found){

                                    Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                                    alertType.setTitle("Μη έγκυρα στοιχεία");
                                    alertType.setResizable(true);
                                    alertType.getDialogPane().setPrefSize(480, 100);
                                    alertType.setContentText("Δεν βρέθηκε πελάτης με αυτό το τηλέφωνο και αυτό το Α.Φ.Μ..");
                                    alertType.show();

                                }

                            }else{

                                Alert alertType = new Alert(Alert.AlertType.ERROR);
                                alertType.setTitle("Λάθος στοιχεία");
                                alertType.setContentText("Ελέγξτε ξανά το τηλέφωνο που δηλώσατε.");
                                alertType.show();

                            }

                        }else{
                            Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                            alertType.setTitle("Άδεια πεδία");
                            alertType.setContentText("Παρακαλώ συμπληρώστε όλα τα στοιχεία.");
                            alertType.show();
                        }

                    }else{
                        Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                        alertType.setTitle("Μη επιλεγμένο πρόγραμμα");
                        alertType.setContentText("Παρακαλώ επιλέξτε ένα πρόγραμμα από τον πίνακα.");
                        alertType.show();
                    }

                }

            }else{

                Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                alertType.setTitle("Μη επιλεγμένο πρόγραμμα");
                alertType.setContentText("Παρακαλώ επιλέξτε τύπο προγράμματος.");
                alertType.show();

            }

        });

        cancelContract = new Button("Ακύρωση Συμβολαίου");
        cancelContract.setOnAction(e->{

            Contract selectedContract = contractTableView.getSelectionModel().getSelectedItem();

            if(selectedContract!=null){

                cancelContract(selectedContract);

            }else{

                Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                alertType.setTitle("Μη επιλεγμένο πρόγραμμα");
                alertType.setContentText("Παρακαλώ επιλέξτε ένα συμβόλαιο από τον πίνακα.");
                alertType.show();

            }
        });

        buttonFlowPane.getChildren().addAll(newContractBtn,selectPlanLbl,mobileRadio,landlineRadio,
                cancelContract);

        //Bottom right GridPane

        // Searching

        searchLbl = new Label("Αναζητήστε συμβόλαιο:");
        searchLbl.setFont(Font.font("normal", FontWeight.BOLD, 12));

        searchField = new TextField();
        searchField.setPromptText("Πληκτρολογήστε τηλέφωνο...");

        searchBtn = new Button("Αναζήτηση");

        searchCombo = new ComboBox<>();
        searchCombo.getItems().addAll("Σταθερής","Κινητής","Καμία Επιλογή");
        searchCombo.setPromptText("Τύπος προγράμματος");

        //Handling SearchBtn Event

        searchBtn.setOnMouseClicked(e -> {

            if (!(searchField.getText().isBlank()) || searchCombo.getValue() != null) { // if at least one of them is
                // filled

                searchContract(searchField.getText(),searchCombo.getValue());
                clearFields();

            } else {

                Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                alertType.setTitle("Μη έγκυρη αναζήτηση");
                alertType.setResizable(true);
                alertType.getDialogPane().setPrefSize(480, 100);
                alertType.setContentText("Παρακαλώ αναζητήστε συμβόλαιο με έναν από τους δύο τρόπους.");
                alertType.show();

            }

        });

        //Reset table button

        resetTable = new Button("Επαναφορά πίνακα");

        resetTable.setOnMouseClicked(e->{

            tableSync();

        });

        //adding nodes to bottom right GridPane
        bottomGrid.add(searchLbl,0,7);
        bottomGrid.add(searchField, 0, 8);
        bottomGrid.add(searchBtn, 1, 8);
        bottomGrid.add(searchCombo, 0, 9);
        bottomGrid.add(resetTable,1,9);


        //Back Btn

        backBtn = new Button("Επιστροφή");
        bottomGrid.add(backBtn,1,16);
        backBtn.setOnMouseClicked(e->{

            App.primaryStage.setTitle("Κατάστημα Παροχής Τηλεπικοινωνιακών Υπηρεσιών");
            App.primaryStage.setScene(App.mainScene);

        });

        //Tables

        //Mobile plans table
        selectMobileTable = new TableView<>();

        selectMobileTable.setPlaceholder(new Label("Δεν υπάρχουν καταχωρημένα προγράμματα κινητής στο σύστημα."));

        TableColumn<Plan, Integer> passColumn = new TableColumn<>("Κωδικός");
        passColumn.setCellValueFactory(new PropertyValueFactory<>("specialPass"));
        passColumn.setPrefWidth(70);

        TableColumn<Plan, String> providerColumn = new TableColumn<>("Πάροχος");
        providerColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
        providerColumn.setPrefWidth(100);

        TableColumn<Plan, Integer> freeTalkColumn = new TableColumn<>("Δωρεάν Λεπτά Ομιλίας");
        freeTalkColumn.setCellValueFactory(new PropertyValueFactory<>("freeTalk"));
        freeTalkColumn.setPrefWidth(150);

        TableColumn<Plan, Double> planCostColumn = new TableColumn<>("Κόστος");
        planCostColumn.setCellValueFactory(new PropertyValueFactory<>("planCost"));
        planCostColumn.setPrefWidth(100);

        TableColumn<Plan,Integer> freeSmsColumn = new TableColumn<>("Δωρεάν SMS");
        freeSmsColumn.setCellValueFactory(new PropertyValueFactory<>("freeSms"));
        freeSmsColumn.setPrefWidth(100);

        TableColumn<Plan,Double> freeGbColumn = new TableColumn<>("Δωρεάν Δεδομένα Gb");
        freeGbColumn.setCellValueFactory(new PropertyValueFactory<>("freeGb"));
        freeGbColumn.setPrefWidth(200);

        selectMobileTable.getColumns().addAll(passColumn,providerColumn,freeTalkColumn,planCostColumn,
                freeGbColumn,freeSmsColumn);

        //Landline plans table
        selectLandLineTable = new TableView<>();

        selectLandLineTable.setPlaceholder(new Label("Δεν υπάρχουν καταχωρημένα προγράμματα σταθερής στο σύστημα."));

        TableColumn<Plan, Integer> passLandlineColumn = new TableColumn<>("Κωδικός");
        passLandlineColumn.setCellValueFactory(new PropertyValueFactory<>("specialPass"));
        passLandlineColumn.setPrefWidth(70);

        TableColumn<Plan, String> providerLandlineColumn = new TableColumn<>("Πάροχος");
        providerLandlineColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
        providerLandlineColumn.setPrefWidth(100);

        TableColumn<Plan, Integer> freeTalkLandlineColumn = new TableColumn<>("Δωρεάν Λεπτά Ομιλίας");
        freeTalkLandlineColumn.setCellValueFactory(new PropertyValueFactory<>("freeTalk"));
        freeTalkLandlineColumn.setPrefWidth(150);

        TableColumn<Plan, Double> planCostLandlineColumn = new TableColumn<>("Κόστος");
        planCostLandlineColumn.setCellValueFactory(new PropertyValueFactory<>("planCost"));
        planCostLandlineColumn.setPrefWidth(100);

        TableColumn<Plan,String> speedColumn = new TableColumn<>("Ταχύτητα Γραμμής (Mbps)");
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        speedColumn.setPrefWidth(200);

        TableColumn<Plan,String> lineTypeColumn = new TableColumn<>("Τύπος Γραμμής");
        lineTypeColumn.setCellValueFactory(new PropertyValueFactory<>("lineType"));
        lineTypeColumn.setPrefWidth(100);

        selectLandLineTable.getColumns().addAll(passLandlineColumn,providerLandlineColumn,freeTalkLandlineColumn,
                planCostLandlineColumn,speedColumn,lineTypeColumn);

        //Contract Table

        contractTableView = new TableView<>();

        rootGrid.add(contractTableView, 0, 0);

        TableColumn<Contract, String> specialPassColumn = new TableColumn<>("Κωδικός");
        specialPassColumn.setCellValueFactory(new PropertyValueFactory<>("specialPass"));
        specialPassColumn.setPrefWidth(60);

        TableColumn<Contract, String> afmColumn = new TableColumn<>("Α.Φ.Μ. ");
        afmColumn.setCellValueFactory(new PropertyValueFactory<>("afm"));
        afmColumn.setPrefWidth(60);

        TableColumn<Contract, String> phoneNumColumn = new TableColumn<>("Τηλ.");
        phoneNumColumn.setPrefWidth(60);
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

        TableColumn<Contract, String> startingDateColumn = new TableColumn<>("Ημ. έναρξης");
        startingDateColumn.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
        startingDateColumn.setPrefWidth(90);

        TableColumn<Contract, String> durationColumn = new TableColumn<>("Διάρκεια Συμβ.");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn.setPrefWidth(100);

        TableColumn<Contract, String> discountColumn = new TableColumn<>("Έκπτωση");
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        discountColumn.setPrefWidth(60);

        TableColumn<Contract, String> costWithDiscountColumn = new TableColumn<>("Κόστος (με έκπτωση)");
        costWithDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("costWithDiscount"));
        costWithDiscountColumn.setPrefWidth(120);

        TableColumn<Contract, String> billTypeColumn = new TableColumn<>("Τύπος Λογαριασμού");
        billTypeColumn.setCellValueFactory(new PropertyValueFactory<>("billType"));
        billTypeColumn.setPrefWidth(125);

        TableColumn<Contract, String> paymentMethodColumn = new TableColumn<>("Τρόπος Πληρωμής");
        paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        paymentMethodColumn.setPrefWidth(120);

        TableColumn<Contract, String> costOfCancellationColumn = new TableColumn<>("Κόστος Ακύρωσης");
        costOfCancellationColumn.setCellValueFactory(new PropertyValueFactory<>("costOfCancellation"));
        costOfCancellationColumn.setPrefWidth(120);

        TableColumn<Contract, String> stateColumn = new TableColumn<>("Κατάσταση Συμβ.");
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        stateColumn.setPrefWidth(120);

        contractTableView.getColumns().addAll(specialPassColumn,afmColumn,phoneNumColumn,startingDateColumn,durationColumn,
                discountColumn,costWithDiscountColumn,billTypeColumn,paymentMethodColumn,costOfCancellationColumn,stateColumn);

        contractTableView.setOnMouseClicked(e->{

            Contract selectedContract = contractTableView.getSelectionModel().getSelectedItem();

            if(selectedContract!=null){

                Plan contractPlan = selectedContract.getPlan();

                for(Plan plan : planList){

                    if(contractPlan==plan){

                        if(plan instanceof MobilePlan){
                            clearGrid();
                            rootGrid.add(selectMobileTable,0,2);
                            syncMobilePlanTable();
                            mobileRadio.setSelected(true);
                            selectMobileTable.getSelectionModel().select(selectedContract.getPlan());
                            contractTableView.getSelectionModel().select(selectedContract);

                        }else if(plan instanceof LandlinePlan){

                            clearGrid();
                            rootGrid.add(selectLandLineTable,0,2);
                            syncLandlineTable();
                            landlineRadio.setSelected(true);
                            selectLandLineTable.getSelectionModel().select(selectedContract.getPlan());
                            contractTableView.getSelectionModel().select(selectedContract);

                        }
                    }
                }

                afmField.setText(String.valueOf(selectedContract.getAfm()));
                phoneNumField.setText(String.valueOf(selectedContract.getPhoneNum()));
                startingDate.setValue(selectedContract.getStartingDate());

                durationCombo.setValue(String.valueOf(selectedContract.getDuration()));
                billTypeCombo.setValue(selectedContract.getBillType());
                paymentMethodCombo.setValue(selectedContract.getPaymentMethod());

            }

        });

    }//end of constructor

    private void clearFields() {

        phoneNumField.clear();
        afmField.clear();
        startingDate.setValue(null);
        durationCombo.getSelectionModel().clearSelection();
        billTypeCombo.getSelectionModel().clearSelection();
        paymentMethodCombo.getSelectionModel().clearSelection();

    }

    private void createContract(Plan selectedPlan, String afm, String phone, LocalDate date,
                                String duration, String type, String pay, String status) {

        //calculate discount
        double discount = 0.0;

        if (status.equals("Επαγγελματίας")){
            discount += 0.10;
        }else if(status.equals("Φοιτητής")){
            discount += 0.15;
        }

        if(selectedPlan.getFreeTalk()>1000){

            if(selectedPlan instanceof LandlinePlan){
                discount+= 0.08;
            }else{
                discount+= 0.11;
            }
        }

        if(pay.equals("Πιστωτική κάρτα")){
            discount+=0.05;
        }

        if(type.equals("Ηλεκτρονικός")){
            discount+=0.02;
        }

        double costWithDiscount = selectedPlan.getPlanCost() - selectedPlan.getPlanCost()*discount;

        double temp = discount*100;

        String stringDiscount = String.valueOf( Math.round(temp) + "%");

        //special pass

        String formattedDate = date.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

        String pass = "";

        if(selectedPlan instanceof MobilePlan){
            pass = ( formattedDate + "-<" + afm + ">-" + "-Mobile");

        }else{
            pass = (formattedDate + "-<" + afm + ">-" + "-Landline");
        }

        try{

            Contract contract = new Contract(pass, Integer.parseInt(afm),Long.parseLong(phone),
                    date,duration, stringDiscount,costWithDiscount,type,pay,
                    0,"Ενεργό",true,selectedPlan);

            contractList.add(contract);
            tableSync();
            clearFields();

            for(Contract item:contractList){

            		if(item.getPhoneNum()==contract.getPhoneNum() && item.isContractActive()){

            		    //if there is a different active contract with the same number, cancel it

                        Alert alertType = new Alert(Alert.AlertType.WARNING);

                        alertType.setTitle("Ακύρωση Συμβολαίου");
                        alertType.setContentText("Δεν επιτρέπεται να υπάρχουν δύο ενεργά συμβόλαια που να αντιστοιχούν " +
                                "στον ίδιο αριθμό τηλεφώνου. Το συμβόλαιο θα ακυρωθεί.");

                        alertType.show();

                        contract.setContractActive(false);
                        contract.setState("Ανενεργό");

                        LocalDate today = LocalDate.now();

                        int diff = (int) ChronoUnit.MONTHS.between(contract.getStartingDate(),today);

                        if(diff<=3) {

                            double tempCost = contract.getCostWithDiscount();
                            contract.setCostOfCancellation(tempCost * 0.10);

                        }

            		}

            }

        }catch (NumberFormatException event){

            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Λάθος στοιχεία");
            alertType.setContentText("Ελέγξτε ξανά το Α.Φ.Μ. που εισάγατε.");
            alertType.show();

        }

    }

    public void cancelContract(Contract contract) {

        if(contract.isContractActive()){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ακύρωση συμβολαίου;");
            alert.setContentText("Είστε σίγουροι ότι θέλετε να ακυρώσετε αυτό το συμβόλαιο;");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                contract.setContractActive(false);
                contract.setState("Ανενεργό");

                LocalDate today = LocalDate.now();

                int diff = (int) ChronoUnit.MONTHS.between(contract.getStartingDate(),today);

                if(diff<=3){

                    double tempCost = contract.getCostWithDiscount();
                    contract.setCostOfCancellation(tempCost * 0.10);
                    tableSync();

                }


            }

        }else{

            Alert alertType = new Alert(Alert.AlertType.INFORMATION);
            alertType.setTitle("Ανενεργό συμβόλαιο");
            alertType.setContentText("Το συμβόλαιο αυτό είναι ήδη ανενεργό.");
            alertType.show();

        }

    }

    public void searchContract(String phone, String type) {

        List<Contract> contracts = contractTableView.getItems();
        contractTableView.setPlaceholder(new Label("Δεν βρέθηκαν συμβόλαια με χαρακτηριστικά:\n<Τύπος Προγράματος> = "
                + type + "\n<Τηλέφωνο> = " + phone));

        contracts.clear();

        if ((type == null || type.equals("Καμία Επιλογή")) && !(phone.isBlank())) {

            // search by phone
            for (Contract c : contractList) {

                if (String.valueOf(c.getPhoneNum()).equals(phone)) {

                    contracts.add(c);

                }

            }

        }

        if (type != "Καμία Επιλογή" && type != null && phone.isBlank()) {

            // search by type here

            if (type == "Κινητής") {

                // add only mobile-related contracts

                for (Contract c : contractList) {

                    if (c.getPlan() instanceof MobilePlan) {

                        contracts.add(c);

                    }
                }

            } else if (type == "Σταθερής") {

                // add only landline-related contracts

                for (Contract c : contractList) {

                    if (c.getPlan() instanceof LandlinePlan) {

                        contracts.add(c);
                    }
                }
            }
        }

        if (type != null && !phone.isBlank() && !type.equals("Καμία Επιλογή")) {

            // search by phone AND by type here

            if (type == "Κινητής") {

                for (Contract c : contractList) {

                    if (c.getPlan() instanceof MobilePlan && String.valueOf(c.getPhoneNum()).equals(phone)) {

                        contracts.add(c);

                    }

                }

            } else if (type == "Σταθερής") {

                for (Contract c : contractList) {

                    if (c.getPlan() instanceof LandlinePlan && String.valueOf(c.getPhoneNum()).equals(phone)) {

                        contracts.add(c);

                    }
                }
            }

        }

    }

    public boolean validatePhone(String phone, Plan plan){

        long longPhone = Long.parseLong(phone);

        int count = 0;
        while (longPhone!=0) {

            longPhone/= 10;
            ++count;
        };

        if(count==10){

            return true;

//            long firstDigit = longPhone/1000000000;
//
//            if(plan instanceof MobilePlan && firstDigit==6){
//
//                return true;
//
//            }else if(plan instanceof LandlinePlan && firstDigit==2){
//
//                return true;
//
//            }else {
//
//                return false;
//            }


        }else{

            return false;

        }

    }

    private boolean checkFields(String afm, String phone, LocalDate date, String duration,
                                String type, String pay) {

        if(afm.isBlank() || phone.isBlank() || date==null || duration==null || type==null || pay==null ){

            //if at least one of them is empty

            return false;

        }else{

            return true;


        }
    }

    private void clearGrid() {

        rootGrid.getChildren().clear();
        rootGrid.add(contractTableView, 0, 0);
        rootGrid.add(buttonFlowPane, 0, 1);
        rootGrid.add(inputFieldsGrid, 1, 0);
        rootGrid.add(bottomGrid,1,2);

        tableSync();

    }

    public void tableSync() {

        List<Contract> items = contractTableView.getItems();
        items.clear();
        for (Contract c : contractList) {
            items.add((Contract) c);
        }

    }

    public void syncMobilePlanTable(){

        List<Plan> items = selectMobileTable.getItems();
        items.clear();
        for (Plan item : planList){
            if(item instanceof MobilePlan){
                items.add((Plan) item);
            }
        }
    }

    public void syncLandlineTable(){

        List<Plan> items = selectLandLineTable.getItems();
        items.clear();
        for (Plan item : planList){
            if(item instanceof LandlinePlan){
                items.add((Plan) item);
            }
        }
    }

    @Override
    Scene createScene() {
        return new Scene(rootGrid, width, height);
    }
}
