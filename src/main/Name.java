package main;

import java.util.*;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import tool.*;

public class Name extends Application {
	private Button bt;
	private Label lb;
	private Random rand = new Random();
	public static void main(String[] args) {
		launch(args);
	}

    public void start(Stage stage) throws Exception {
		bt = new Button("生成");
		lb = new Label("0");

		BorderPane bp = new BorderPane();
		bp.setTop(bt);
		bp.setCenter(lb);
		bt.setOnAction(new CountUpEventHandler());

		Scene sc = new Scene(bp, 640, 360);
		stage.setScene(sc);
		stage.setTitle("名前生成");
		stage.show();
    }

    class CountUpEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			ReadSqlite family = new ReadSqlite("family.db");
		    lb.setText(family.readSql(0));
		}
    }
}