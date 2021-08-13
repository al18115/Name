package tool;

import java.sql.*;


public class ReadSqlite {

	private String dbName;

	public ReadSqlite(String name) {
		this.dbName = "jdbc:sqlite:database/" + name;
	}

	//データを取得
	public String readSql(int number) {
		try {
			// JDBCドライバーの指定
			Class.forName("org.sqlite.JDBC");

			// データベースに接続する なければ作成される
			Connection con = DriverManager.getConnection(dbName);

			String sql = "SELECT NAME FROM NAME WHERE ID = " + number;
			Statement stmt = con.createStatement();




			con.close();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			// Connection の例外が発生した時

			e.printStackTrace();
		}
		return "";
	}

	//テーブル数を取得
	public int getSize() {
		try {
			// JDBCドライバーの指定
			Class.forName("org.sqlite.JDBC");

			// データベースに接続する なければ作成される
			Connection con = DriverManager.getConnection(dbName);

			String sql = "SELECT count(*) FROM NAME";


			con.close();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			// Connection の例外が発生した時

			e.printStackTrace();
		}
		return 0;
	}
}