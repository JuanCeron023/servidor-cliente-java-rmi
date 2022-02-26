import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
	
	
	public int[] Sumar(int[] pA, int[] pB) throws RemoteException;
	public int[] Restar(int[] pA, int[] pB) throws RemoteException;
	public int[] Multiplicar(int[] pA, int[] pB) throws RemoteException;
	
}



