package org.example.fileexploserapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.example.fileexploserapp.ControllerTableView.Fx2;
import static org.example.fileexploserapp.ControllerTilesView.Fx3;

public class Controller implements Initializable {
    @FXML private Button btn;
    @FXML private Pane secPane;
    @FXML private TreeView<String> treeview;
    @FXML private Label label;
    private int count;
    static ClassTreeView Fx1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        count = 0;
        Fx1 = new ClassTreeView();

        Fx1.CurrDirFile = new File("./");
        Fx1.CurrDirStr = Fx1.CurrDirFile.getAbsolutePath();
        Fx1.lbl = label;
        Fx2.lbl = label;
        label.setText(Fx1.CurrDirStr);
        try{
            Pane newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene2.fxml")));
            secPane.getChildren().add(newLoadedPane);
        } catch(NullPointerException x){
            x.printStackTrace();
        } catch(IOException x) {
            x.printStackTrace();
        }
        Fx1.CreateTreeView(treeview);
        Fx2.CreateTableView();// okhan theke dile error dey :/
    }


    @FXML
    private void handleMouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 1){
            try{
                TreeItem<String> item = treeview.getSelectionModel().getSelectedItem();
                Fx1.CurrDirName = item.getValue();
                System.out.println("Selected Text : " + item.getValue());
                Fx1.CurrDirFile = new File(Fx1.FindAbsolutePath(item,item.getValue()));
                Fx1.CurrDirStr = Fx1.CurrDirFile.getAbsolutePath();
                label.setText(Fx1.CurrDirStr);
                Fx2.tableview.getItems().clear();
                Fx2.CreateTableView();
                Fx3.CreateTiles();
                /**tableview.getItems().clear();
                 CreateTableView();
                 /**call some other function to activate createtableview() in corres controller */
            }catch(Exception x){
                System.out.println(x.getMessage());
            }
        }
        Fx2.tableview.getItems().clear();
        Fx2.CreateTableView();
    }


    @FXML private void loadFxml (ActionEvent event) throws IOException {
        count = ( count+1 )%2;
        Pane newLoadedPane;
        secPane.getChildren().clear();
        if(count==0){newLoadedPane =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene2.fxml")));}
        else        {newLoadedPane =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene3.fxml")));}
        secPane.getChildren().add(newLoadedPane);
    }
}