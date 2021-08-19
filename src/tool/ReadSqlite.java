package tool;

import java.sql.*;


public class ReadSqlite {

	private String dbName;

	public ReadSqlite(String name) {
		this.dbName = "jdbc:sqlite:database/" + name;
	}

	//データを取得
	public String readSql(int number) {
		String result = "";

		try {
			// JDBCドライバーの指定
			Class.forName("org.sqlite.JDBC");

			// データベースに接続する なければ作成される
			Connection con = DriverManager.getConnection(dbName);

			String sql = "SELECT NAME FROM NAME WHERE ID = " + number;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result = rs.getString("NAME");
			}

			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			// Connection の例外が発生した時

			e.printStackTrace();
		}
		return result;
	}

	//レコード数を取得
	public int getSize() {
		int result = 0;
		try {
			// JDBCドライバーの指定
			Class.forName("org.sqlite.JDBC");

			// データベースに接続する なければ作成される
			Connection con = DriverManager.getConnection(dbName);

			String sql = "SELECT count(*) FROM NAME";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result = rs.getInt("count(*)");
			}

			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			// Connection の例外が発生した時

			e.printStackTrace();
		}
		return result;
	}
}