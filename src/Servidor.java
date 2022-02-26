import java.rmi.Naming;

public class Servidor {
	
	public Servidor() {
		
		try {
			//archivos del servidor
			System.setProperty("java.rmi.server.codebase","file:/c/RMI/src/");
			Interface i = new Implementacion();
			Naming.rebind("rmi://localhost/Polinomio", i);
		}	
		catch(Exception Ex)
		{
		System.out.println("error");
		}
		
	}
	
	public static void main(String[] args)
	{
		new Servidor();
		System.out.println("Servidor en espera");
	}
	
	

}