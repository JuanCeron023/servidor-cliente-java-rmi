import java.rmi.Naming;

public class Servidor {
	
	public Servidor() {
		
		try {
			//archivos del servidor//localhost
			System.setProperty("java.rmi.server.codebase","file:/c/RMI/src/");
			Interface i = new Implementacion();
			Naming.rebind("rmi://192.168.0.17/Polinomio", i);
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