package proapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PlaceController {
    Text text = null;
    @FXML
    private ListView<String> list;
    @FXML
    private Button aladdin;
    public static ArrayList<String> place = filereader.Reader_place();
    public void initialize() {
        text = new Text("");
        // アイテムに入れる文字列を用意する
        ObservableList<String> lm = FXCollections.observableArrayList(place);
        // アイテムをセット
        list.setItems(lm);
        // イベント設定
        list.setOnMouseClicked((MouseEvent event) -> {
            listClicked(event);
        });
    }

    private void listClicked(MouseEvent e) {
        ListView<String> l = (ListView) e.getSource();
        String s = (String) l.getSelectionModel().getSelectedItem();
        if (s == null) {
            return;
        }
        try{
            ShowThirdWindow();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        if(checkController.check.equals("true")){
            checkController.check = "false";
            place.remove(s);
            filewrite.write_place(place);
            ObservableList<String> lm = FXCollections.observableArrayList(place);
            list.setItems(lm);
        }
    }

    @FXML
    private void OnaddClick(ActionEvent e)  {
        try {
            ShowSecondWindow();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        filewrite.write_place(place);
        ObservableList<String> lm = FXCollections.observableArrayList(place);
        list.setItems(lm);
    }
    void ShowSecondWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add2.fxml"));
        Pane root = (Pane) loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }

    void ShowThirdWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("check.fxml"));
        Pane root = (Pane) loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }
}
