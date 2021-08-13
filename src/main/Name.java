package main;

import java.util.*;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import tool.*;

public class Name extends Application {
	private Button bt;
	private Label lb;
	private RadioButton rb1, rb2;
	private ToggleGroup tg;

	private boolean isMale = true;

	private Random rand = new Random();


	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		//ボタン
		bt = new Button("生成");

		//ラベル
		lb = new Label("");

		//ラジオボタン
		rb1 = new RadioButton("男性");
		rb2 = new RadioButton("女性");

		//ラジオボタングループ化
		tg = new ToggleGroup();
		rb1.setToggleGroup(tg);
		rb2.setToggleGroup(tg);
		rb1.setSelected(true);

		//ペインの作成
		BorderPane bp = new BorderPane();
		HBox hb = new HBox();

		//ペインへの追加
		hb.getChildren().add(rb1);
		hb.getChildren().add(rb2);
		hb.getChildren().add(bt);

		hb.setAlignment(Pos.CENTER);

		bp.setTop(hb);
		bp.setCenter(lb);

		//イベント
		bt.setOnAction(new CreateNameEventHandler());
		rb1.setOnAction(new SelectGenderEventHandler());
		rb2.setOnAction(new SelectGenderEventHandler());

		//シーンの作成
		Scene sc = new Scene(bp, 640, 360);
		stage.setScene(sc);
		stage.setTitle("名前生成");
		stage.show();
    }

	//名前生成
	class CreateNameEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			ReadSqlite family = new ReadSqlite("family.db");
		    lb.setText(family.readSql(rand.nextInt(family.getSize())) + " ");
		}
	}

	class SelectGenderEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

		}
	}
}