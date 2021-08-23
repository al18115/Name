package main;

import java.util.*;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;

import tool.*;

public class Name extends Application {
	private Button bt;
	private Label nameFamily;
	private Label rubyFamily;
	private Label nameFirst;
	private Label rubyFirst;
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
		nameFamily = new Label("");
	    nameFamily.setFont(Font.font("SansSerif", 50));
		nameFirst = new Label("");
	    nameFirst.setFont(Font.font("SansSerif", 50));

	    rubyFamily = new Label("");
	    rubyFamily.setFont(Font.font("SansSerif", 25));
	    rubyFirst = new Label("");
	    rubyFirst.setFont(Font.font("SansSerif", 25));

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
		GridPane gp = new GridPane();

		//ペインへの追加
		hb.getChildren().add(rb1);
		hb.getChildren().add(rb2);
		hb.getChildren().add(bt);

		gp.add(rubyFamily, 0, 0);
		gp.add(new Label("  "), 1, 0);
		gp.add(rubyFirst, 2, 0);
		gp.add(nameFamily, 0, 1);
		gp.add(new Label("   "), 1, 1);
		gp.add(nameFirst, 2, 1);

		hb.setAlignment(Pos.CENTER);
		gp.setAlignment(Pos.CENTER);

		bp.setTop(hb);
		bp.setCenter(gp);

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
			ReadSqlite nameSql = new ReadSqlite("name.db");
			String tableFamily = "FAMILY";
			String tableFirst = "";
			NameRuby familyRuby = new NameRuby();
			NameRuby firstRuby = new NameRuby();

			if (isMale) {
				//男性
				tableFirst = "MALE";

			}else {
				//女性
				tableFirst = "FEMALE";
			}
			//乱数生成
			int firstRand =  rand.nextInt(nameSql.getSize(tableFirst));
			int familyRand = rand.nextInt(nameSql.getSize(tableFamily));

			//データベース読み出し
			familyRuby = nameSql.readSql(tableFamily, familyRand);
			firstRuby = nameSql.readSql(tableFirst, firstRand);

			//ラベル更新
		    nameFamily.setText(familyRuby.getName());
		    nameFirst.setText(firstRuby.getName());
		    rubyFamily.setText(familyRuby.getRuby());
		    rubyFirst.setText(firstRuby.getRuby());
		}
	}

	class SelectGenderEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			RadioButton tmp = (RadioButton) e.getSource();
			if (tmp.getText().equals("男性")) {
				isMale = true;
			}
			else {
				isMale = false;
			}
		}
	}
}