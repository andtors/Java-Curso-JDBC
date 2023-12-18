package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {
	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			//Criamos o autocommit para caso de algum erro em algum statement a mudança não seja feita
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			//int x = 1;
			//
			//if (x < 2) {
			//	throw new SQLException("Fake error");
			//}
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();
			//Aqui se encerra os parametros que se derem errado não irá executar
			
			System.out.println("rows1 " + rows1);
			System.out.println("rows2 " + rows2);

		} catch (SQLException e) {
			try {
				conn.rollback();
				//Com esse comando desfazemos o que foi feito
				throw new DbException("Transaction rolled back! caused by:" + e.getMessage()); 
			} catch (SQLException e1) {
			
				throw new DbException("Error tryng to rollback: " + e1.getMessage());
				//Aqui seria caso de erro no rollback, f*deu se aparecer
			}
		} 
		finally
		{
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
}
