/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem.ui.addBook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import librarymanagementsystem.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Sapthaka
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField publisher;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;

    DatabaseHandler databaseHandler;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = new DatabaseHandler();
    }    

    @FXML
    private void addBook(ActionEvent event) {
        String bookID = id.getText();
        String bookTitle = title.getText();
        String bookAuthor = author.getText();
        String bookPublisher = publisher.getText();
    
        if (bookAuthor.isEmpty() || bookID.isEmpty() || bookPublisher.isEmpty() || bookTitle.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter in all fields");
            alert.showAndWait();
            return;
        }
        /**
         * stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        +"id varchar(200) primary key,\n"
                        +"title varchar(200),\n"
                        +"author varchar(200),\n"
                        +"publisher varchar(100),\n"
                        +"isAvail boolean default true"
                        +")");
         */
        String qu = "INSERT INTO BOOK VALUES ("+
                "'" + bookID + "',"+
                "'" + bookTitle + "',"+
                "'" + bookAuthor + "',"+
                "'" + bookPublisher + "',"+
                "" + true + ""+
                ")";
        System.out.println(qu);
        if(databaseHandler.execAction(qu)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        } else{//Error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
}
