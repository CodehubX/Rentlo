package com.chamodshehanka.rentLioClient.view.controller;

import com.chamodshehanka.rentLioClient.controller.AdminController;
import com.chamodshehanka.rentLioClient.controller.ReceptionController;
import com.chamodshehanka.rentLioCommon.dto.AdminDTO;
import com.chamodshehanka.rentLioCommon.dto.ReceptionDTO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author chamodshehanka on 4/2/2018
 * @project RentLio
 **/
public class LoginUIController implements Initializable {

    @FXML
    private AnchorPane rootLogin;

    @FXML
    private JFXTextField txtAdminUserName;

    @FXML
    private JFXPasswordField txtAdminPassword;

    @FXML
    private JFXTextField txtReceptionUserName;

    @FXML
    private JFXPasswordField txtReceptionPassword;

    private List<AdminDTO> adminDTOList;

    private List<ReceptionDTO> receptionDTOList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAdminList();
    }

    @FXML
    private void adminLoginAction(){
        String userName = txtAdminUserName.getText();
        String password = txtAdminPassword.getText();

        if (!userName.isEmpty() && !password.isEmpty()){
            for (AdminDTO adminDTO: adminDTOList
                 ) {
                if (adminDTO.getAdminName().equals(userName) && adminDTO.getPassword().equals(password)){
                    loadDashBoardUI();
                }else {
                    Alert adminLoginFailedAlert = new Alert(Alert.AlertType.ERROR);
                    DialogPane dialogPane = adminLoginFailedAlert.getDialogPane();
                    dialogPane.getStylesheets().add(
                            getClass().getResource("/com/chamodshehanka/rentLioClient/view/css/dialog-pane-styles.css")
                                    .toExternalForm());
                    dialogPane.getStyleClass().add("myDialog");
                    adminLoginFailedAlert.setTitle("Admin Login");
                    adminLoginFailedAlert.setHeaderText("Admin Login failed");
                    adminLoginFailedAlert.setContentText("Please check your user name or password again.");
                    adminLoginFailedAlert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void receptionLoginAction(){
        String userName = txtReceptionUserName.getText();
        String password = txtReceptionPassword.getText();

        if (!userName.isEmpty() && !password.isEmpty()){
            for (ReceptionDTO receptionDTO: receptionDTOList
                 ) {
                if (receptionDTO.getName().equals(userName)){
                    System.out.println();
                }
            }
        }
    }

    private void loadDashBoardUI(){
        try {
            AnchorPane menuAnchorPane = FXMLLoader
                    .load(getClass().getResource("/com/chamodshehanka/rentLioClient/view/fxml/DashBoardUI.fxml"));
            rootLogin.getChildren().setAll(menuAnchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadReceptionList(){
        try {
            receptionDTOList = ReceptionController.getAllReceptions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAdminList(){
        try {
            adminDTOList = AdminController.getAllAdmins();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}