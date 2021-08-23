package tool;

import java.sql.*;


public class ReadSqlite {

	private String dbName;

	public ReadSqlite(String name) {
		this.dbName = "jdbc:sqlite:database/" + name;
	}

	//データを取得
	public NameRuby readSql(String table , int number) {
		NameRuby result = new NameRuby();
		try {
			// JDBCドライバーの指定
			Class.forName("org.sqlite.JDBC");

			// データベースに接続する なければ作成される
			Connection con = DriverManager.getConnection(dbName);

			String sql = "SELECT * FROM " + table + " WHERE ID = " + number;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result.insert(rs.getString("NAME"), rs.getString("RUBY"));
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
	public int getSize(String table) {
		int result = 0;
		try {
			// JDBCドライバーの指定
			Class.forName("org.sqlite.JDBC");

			// データベースに接続する なければ作成される
			Connection con = DriverManager.getConnection(dbName);

			String sql = "SELECT count(*) FROM " + table;

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