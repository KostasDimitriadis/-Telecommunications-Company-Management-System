package sceneCreators;

import Core.Plan;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import Core.TelecommunicationCompany;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TeleSceneCreator extends SceneCreator {


   FlowPane buttonFlowPane;

   GridPane rootGridPane, inputFieldsPane;

   Button newCompBtn, updateCompBtn, deleteCompBtn, backBtn, searchBtn , resetTable;

   Label nameLbl, phoneLbl, emailLbl, searchLbl;

   TextField nameField, phoneField, emailField,searchField;

   TableView<TelecommunicationCompany> compTableView;

   public TeleSceneCreator(double width, double height) {

       super(width, height);

       //GridPane: root

       rootGridPane = new GridPane();

       ColumnConstraints column1 = new ColumnConstraints();
       ColumnConstraints column2 = new ColumnConstraints();
       rootGridPane.getColumnConstraints().add(column1);
       rootGridPane.getColumnConstraints().add(column2);
       column1.setPrefWidth(500);
       column2.setPrefWidth(300);
       column1.setPercentWidth(250);
       column2.setPercentWidth(150);

       rootGridPane.setVgap(10);
       rootGridPane.setHgap(10);

       rootGridPane.setPadding(new Insets(10, 10, 10, 10));

       // GridPane: Input Fields
       inputFieldsPane = new GridPane();

       ColumnConstraints Column1 = new ColumnConstraints();
       ColumnConstraints Column2 = new ColumnConstraints();
       inputFieldsPane.getColumnConstraints().add(Column1);
       inputFieldsPane.getColumnConstraints().add(Column2);
       Column1.setPrefWidth(500);
       Column2.setPrefWidth(400);
       Column1.setPercentWidth(250);
       Column2.setPercentWidth(200);

       inputFieldsPane.setVgap(10);
       inputFieldsPane.setHgap(10);

       inputFieldsPane.setAlignment(Pos.TOP_RIGHT);

       inputFieldsPane.setPadding(new Insets(5, 5, 5, 5));
       rootGridPane.add(inputFieldsPane, 1, 0, 1, 1);

       //Button FlowPane

       buttonFlowPane = new FlowPane();

       buttonFlowPane.setHgap(10);
       rootGridPane.add(buttonFlowPane, 0, 1, 1, 1);
       buttonFlowPane.setAlignment(Pos.CENTER);

       //Labels

       nameLbl = new Label("Επωνυμία: ");
       phoneLbl = new Label("Τηλέφωνο: ");
       emailLbl = new Label("Email: ");

       inputFieldsPane.add(nameLbl,0,0);
       inputFieldsPane.add(phoneLbl,0,1);
       inputFieldsPane.add(emailLbl,0,2);

       // Fields

       nameField = new TextField();
       phoneField = new TextField();
       emailField = new TextField();

       inputFieldsPane.add(nameField,1,0);
       inputFieldsPane.add(phoneField,1,1);
       inputFieldsPane.add(emailField,1,2);

       //Basic buttons
       newCompBtn = new Button("Νέα Εταιρία");
       newCompBtn.setOnMouseClicked(e->{

           Random pass = new Random();

           String name = nameField.getText();
           String phone = (phoneField.getText());
           String email = emailField.getText();

           if(checkFields(name,phone,email)){

               if(validatePhone(phone)){

                   try {

                       createCompany(pass,name,phone,email);


                   } catch (NumberFormatException event) {

                       Alert alertType = new Alert(Alert.AlertType.ERROR);
                       alertType.setTitle("Λάθος στοιχεία");
                       alertType.setContentText("Η τιμή που δώθηκε ως τηλέφωνο δεν είναι έγκυρη.\n"+ event.getMessage());
                       alertType.show();

                   }

               }else{

                   Alert alertType = new Alert(Alert.AlertType.ERROR);
                   alertType.setTitle("Λάθος στοιχεία");
                   alertType.setContentText("Ελέγξτε ξανά το τηλέφωνο που δηλώσατε.");
                   alertType.show();

               }

           }else {
               Alert alertType = new Alert(Alert.AlertType.INFORMATION);
               alertType.setTitle("Άδεια πεδία");
               alertType.setContentText("Παρακαλώ συμπληρώστε ολα τα πεδία.");
               alertType.show();
           }


       });
       updateCompBtn = new Button("Ενημέρωση");
       updateCompBtn.setOnMouseClicked(e->{

           String name = nameField.getText();
           String phone = phoneField.getText();
           String email = emailField.getText();

           TelecommunicationCompany selectedCompany = compTableView.getSelectionModel().getSelectedItem();

           if(selectedCompany!=null){

               if(checkFields(name,phone,email)){

                   if(validatePhone(phone)){

                       try {

                           updateCompany(selectedCompany,name,phone,email);

                       }catch (NumberFormatException event){

                           Alert alertType = new Alert(Alert.AlertType.ERROR);
                           alertType.setTitle("Λάθος στοιχεία");
                           alertType.setContentText("Η τιμή που δώθηκε ως τηλέφωνο δεν είναι έγκυρη.\n"+ event.getMessage());
                           alertType.show();

                       }

                   }else{

                       Alert alertType = new Alert(Alert.AlertType.ERROR);
                       alertType.setTitle("Λάθος στοιχεία");
                       alertType.setContentText("Ελέγξτε ξανά το τηλέφωνο που δηλώσατε.");
                       alertType.show();

                   }


               }else {

                   Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                   alertType.setTitle("Άδεια πεδία");
                   alertType.setContentText("Παρακαλώ συμπληρώστε ολα τα πεδία.");
                   alertType.show();

               }

           }else{

               Alert alertType = new Alert(Alert.AlertType.INFORMATION);
               alertType.setTitle("Μη επιλεγμένη εταιρία");
               alertType.setContentText("Παρακαλώ επιλέξτε μια εταιρία από τον πίνακα.");
               alertType.show();

           }


       });

       deleteCompBtn = new Button("Διαγραφή");

       deleteCompBtn.setOnMouseClicked(e->{

           TelecommunicationCompany selectedCompany = compTableView.getSelectionModel().getSelectedItem();

           if(selectedCompany!=null){

               Boolean found = false;

               for(TelecommunicationCompany item : teleCompsList){

                   if(selectedCompany.getCompanyName().equals(item.getCompanyName())){

                       found = true;

                       if(planList.isEmpty()){

                           delete(item);
                           break;

                       }else{

                           for(Plan planItem : planList){

                               if(selectedCompany.getCompanyName().equals(planItem.getProvider())){

                                   // if there is a program with that provider

                                   Alert alertType = new Alert(Alert.AlertType.ERROR);
                                   alertType.setResizable(true);
                                   alertType.getDialogPane().setPrefSize(480, 200);
                                   alertType.setTitle("Σφάλμα");
                                   alertType.setHeaderText("Η εταιρία δεν μπορεί να διαγραφεί!");
                                   alertType.setContentText("Υπάρχει καταχωρημένο πρόγραμμα στο σύστημα που σχετίζεται με αυτή.");
                                   alertType.show();
                                   break;

                               }else{

                                   delete(item);
                                   break;

                               }
                           }
                       }

                   }
               }

               if(!found){

                   Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                   alertType.setTitle("Δεν βρέθηκε");
                   alertType.setContentText("Αυτή η εταιρία δεν είναι καταχωρημένη στο σύστημα");
                   alertType.show();

               }
           }else{

               Alert alertType = new Alert(Alert.AlertType.INFORMATION);
               alertType.setTitle("Επιλέξτε εταιρία");
               alertType.setContentText("Παρακαλώ επιλέξτε εταιρία απο τον πίνακα.");
               alertType.show();

           }



       });

       buttonFlowPane.getChildren().addAll(newCompBtn,updateCompBtn,deleteCompBtn);

       //Back button

       backBtn = new Button("Επιστροφή");
       backBtn.setOnMouseClicked(e->{

           App.primaryStage.setTitle("Κατάστημα Παροχής Τηλεπικοινωνιακών  Υπηρεσιών");
           App.primaryStage.setScene(App.mainScene);

       });

       rootGridPane.add(backBtn, 1, 1);

       //TableView

       compTableView = new TableView<>();
       rootGridPane.add(compTableView, 0, 0);

       compTableView.setPlaceholder(new Label("Δεν υπάρχουν καταχωρημένες εταιρίες στο σύστημα."));

       TableColumn<TelecommunicationCompany, String> specialPassColumn = new TableColumn<>("Κωδικός");
       specialPassColumn.setCellValueFactory(new PropertyValueFactory<>("specialPass"));
       specialPassColumn.setMinWidth(150);

       TableColumn<TelecommunicationCompany, String> companyNameColumn = new TableColumn<>("Επωνυμία");
       companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
       companyNameColumn.setMinWidth(150);

       TableColumn<TelecommunicationCompany, String> phoneNumColumn = new TableColumn<>("Τηλέφωνο");
       phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
       phoneNumColumn.setMinWidth(100);

       TableColumn<TelecommunicationCompany, String> emailColumn = new TableColumn<>("E-mail");
       emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
       emailColumn.setMinWidth(200);

       compTableView.getColumns().addAll(specialPassColumn,companyNameColumn,phoneNumColumn,emailColumn);

       compTableView.setOnMouseClicked(e->{

           TelecommunicationCompany selectedCompany = compTableView.getSelectionModel().getSelectedItem();
           if(selectedCompany!=null) {

               nameField.setText(selectedCompany.getCompanyName());
               phoneField.setText(String.valueOf(selectedCompany.getPhoneNum()));
               emailField.setText(selectedCompany.getEmail());

           }

       });

       //Searching

       searchLbl = new Label("Αναζητήστε εταιρία:");
       searchLbl.setFont(Font.font("normal", FontWeight.BOLD, 12));
       inputFieldsPane.add(searchLbl,0,8);

       searchField = new TextField();
       searchField.setPromptText("Επωνυμία εταιρίας ...");
       searchField.setTooltip(new Tooltip("Πληκτρολογήστε την επωνυμία της εταιρίας που ψάχνετε..."));
       inputFieldsPane.add(searchField,0,10);

       searchBtn = new Button("Αναζήτηση εταιρίας");
       inputFieldsPane.add(searchBtn, 1, 10);
       searchBtn.setOnMouseClicked(e -> {

           if(!teleCompsList.isEmpty()){

               boolean found = false;

               if(!(searchField.getText().isBlank())){

                   List<TelecommunicationCompany> items = compTableView.getItems();


                   for (TelecommunicationCompany company : teleCompsList){

                       if(company.getCompanyName().equals(searchField.getText())){

                           items.clear();
                           items.add((TelecommunicationCompany)company);
                           compTableView.getSelectionModel().select(company);
                           found = true;

                       }
                   }

                   if(!found){
                       Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                       alertType.setTitle("Δεν βρέθηκε εταιρία");
                       alertType.setContentText("Δεν υπάρχει εταιρία με αυτή την επωνυμία στο σύστημα.");
                       alertType.show();
                   }

               }else {
                   Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                   alertType.setTitle("Μη έγκυρη αναζήτηση");
                   alertType.setResizable(true);
                   alertType.getDialogPane().setPrefSize(480, 100);
                   alertType.setContentText("Παρακαλώ πληκτρολογήστε την επωνυμία της εταιρίας που ψάχνετε.");
                   alertType.show();
               }

           }else{

               Alert alertType = new Alert(Alert.AlertType.INFORMATION);
               alertType.setTitle("Δεν υπάρχουν εταιρίες");
               alertType.setContentText("Δεν υπάρχουν καταχωρημένες εταιρίες στο σύστημα.");
               alertType.show();

           }


       });

       //Resetting the table

       resetTable = new Button("Επαναφορά πίνακα");
       inputFieldsPane.add(resetTable,1,11);
       resetTable.setOnMouseClicked(e->{

           syncTable();
           clearFields();

       });

   }//end of constructor


    //Methods

    private void createCompany(Random pass, String name, String phone, String email) {

        TelecommunicationCompany company = new TelecommunicationCompany(pass.nextInt(9999999),name,Long.parseLong(phone),email);
        teleCompsList.add(company);

        clearFields();
        syncTable();

    }

    private void updateCompany(TelecommunicationCompany company,String name, String phone, String email) {

       Boolean found = false;

        for(TelecommunicationCompany item: teleCompsList){

            if(item.getCompanyName().equals(name) && company.getSpecialPass()== item.getSpecialPass()){

                found = true;

                company.setPhoneNum(Long.parseLong(phone));
                company.setEmail(email);

                clearFields();
                syncTable();

                break;

            }

        }

        if(!found){

            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Λάθος στοιχεία");
            alertType.setContentText("Δεν μπορείτε να επεξεργαστείτε την επωνυμία της εταιρίας.");
            alertType.show();


        }
    }

    public void delete(TelecommunicationCompany item){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Διαγραφή της \"" + item.getCompanyName() + "\";");
        alert.setHeaderText("Διαγραφή της \"" +item.getCompanyName() + "\";");
        alert.setContentText("Είστε σιγουροί ότι θέλετε να διαγράψετε αυτή την εταιρία;");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            teleCompsList.remove(item);

            clearFields();
            syncTable();

        }

    }

    private boolean checkFields(String name, String phone, String email) {
        //Checks if any of the fields is empty
        if (name.isBlank() || phone.isBlank() || email.isBlank()){

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
//            long firstDigit = longPhone/1000000000;
//
//
//            if(firstDigit==6 || firstDigit==2){
//
//                return true;
//
//            }else{
//
//                return false;
//
//            }

        }else {

            return false;

        }

    }

    public void clearFields(){
        nameField.clear();
        phoneField.clear();
        emailField.clear();
    }

    public void syncTable(){
        List<TelecommunicationCompany> items = compTableView.getItems();
        items.clear();
        for (TelecommunicationCompany item : teleCompsList){
            if(item instanceof TelecommunicationCompany){
                items.add((TelecommunicationCompany) item);
            }
        }
    }

    @Override
	Scene createScene() {
		return new Scene(rootGridPane, width, height);
	}
}
