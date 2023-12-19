package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());	
		//Aqui ela irá metodo que retorna o tipo da interface, mas internamente instancia uma implementação
		//Além disso iremos passar como argumento a conexão com o banco de dados
	}
}
