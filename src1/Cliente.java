import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.Iterator;
import java.util.Scanner;

public class Cliente {

	
	public static void MostrarMenu() {
		System.out.println("");
		System.out.println("*********************");
		System.out.println("********Menu*********");
		System.out.println("1. Sumar");
		System.out.println("2. Restar");
		System.out.println("3. Multiplicar");
		System.out.println("4. Salir");
		LeerSeleccion();
	}
	
	public static void LeerSeleccion() {
		try {
			int Seleccion;
			int[] polinomioA;
			int[] polinomioB;
			int[] resultado;
			Interface i = (Interface) Naming.lookup("rmi://localhost/Polinomio");
		System.out.println("Digite su opcion");
		Scanner sc = new Scanner (new InputStreamReader(System.in));
		Seleccion = sc.nextInt();
		switch(Seleccion)
		{
		case 1:
			System.out.println("Sumar:");
			polinomioA= LeerPolinomio("Ingrese el primer Polinomio");
			polinomioB= LeerPolinomio("Ingrese el segundo Polinomio");
			resultado = i.Sumar(polinomioA, polinomioB);
			System.out.println("El resultado de la operacion sumar es: ");
			MostrarPolinomio(resultado);
			MostrarMenu();
			break;
		
		case 2:
			System.out.println("Restar:");
			polinomioA= LeerPolinomio("Ingrese el primer Polinomio");
			polinomioB= LeerPolinomio("Ingrese el segundo Polinomio");
			resultado = i.Restar(polinomioA, polinomioB);
			System.out.println("El resultado de la operacion restar es: ");
			MostrarPolinomio(resultado);
			MostrarMenu();
			break;
			
		case 3:
			System.out.println("Multiplicar:");
			polinomioA= LeerPolinomio("Ingrese el primer Polinomio");
			polinomioB= LeerPolinomio("Ingrese el segundo Polinomio");
			resultado = i.Multiplicar(polinomioA, polinomioB);
			System.out.println("El resultado de la operacion multiplicar es: ");
			MostrarPolinomio(resultado);
			MostrarMenu();
			break;
			
		case 4:
			System.exit(0);
			break;
		default:
			System.out.println("Opcion Incorrecta");
			MostrarMenu();
			break;
		}
		
		} catch (Exception e) {
			System.out.println("Excepcion en casos menu"+e);
		}
	}
	
	public static int[] LeerPolinomio(String pMensaje)
	{
		int[] Polinomio = new int[3];
		Scanner sc= new Scanner (System.in);
		System.out.println(pMensaje);
		for (int i = 0; i < 3; i++) {
			Polinomio[i]= sc. nextInt();
		}
		return Polinomio;
	}
	
	public static void MostrarPolinomio(int[] pPolinomio)
	{
		for (int i = 0; i < 3; i++) {
			System.out.println(" " + pPolinomio[i]);
		}
	}
	
	public static void main(String[] args) {
		try {
			MostrarMenu();
		} catch (Exception e) {
			System.out.println("Excepcion en main menu"+e);
		}
	}
	
	
	
}
