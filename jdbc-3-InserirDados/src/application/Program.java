package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {
	public static void main(String[] args) {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			/*
			st = conn.prepareStatement(
					"INSERT INTO seller "
					//O statement tem que seguir o mesmo código e tem que ser igual os valores das colunas
					+ "(Name, Email,BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?)", // ? Placeholder	
						Statement.RETURN_GENERATED_KEYS); 
					
					st.setString(1, "Carl Purple");
					st.setString(2, "calr@gmail.com");
					st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
					st.setDouble(4, 3000.0);
					st.setInt(5, 4);
					// Para cada ? temos que cruar set informando o tipo do valor
					
					*/
			
					st = conn.prepareStatement("Insert into department (Name) values ('D1'), ('D2')",
							Statement.RETURN_GENERATED_KEYS);
					//é possivel passar num mesmo statement dois valores da mesma coluna 
					
					int rowsAffected = st.executeUpdate();
					//Apenas para capturar a informação de linhas alteradas
					
					if (rowsAffected > 0) {
						ResultSet rs = st.getGeneratedKeys();
						while(rs.next()) {
							int id = rs.getInt(1);
							System.out.println("Done! Id = " + id);
						}
					}
					else {
						System.out.println("No rows affected");
					}
					
		}
		catch (SQLException e) {
			e.printStackTrace();
		}/*
		catch (ParseException e) {
			e.printStackTrace();
			
		}*/
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
			
		}
		
		}
}
