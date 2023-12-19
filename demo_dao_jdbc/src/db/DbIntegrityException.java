package db;

public class DbIntegrityException extends RuntimeException{
	//Criamos essa exceção caso tentemos deletar uma linha no sql que esteja atrelado a outra linha
	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}

}
