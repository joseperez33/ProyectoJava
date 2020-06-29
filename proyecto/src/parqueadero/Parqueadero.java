package parqueadero;

import java.util.Scanner;


public class Parqueadero  extends registroPlaca{
	
	static Scanner teclado = new Scanner(System.in);
	static int opcion;
	
	public void menu() {
		System.out.println("Menu Parqueadero");
		System.out.println("1. Registrar entrada");
		System.out.println("2. Registrar salida");
		System.out.println("3. Da de alta vehículo oficial");
		System.out.println("4. Da de alta vehículo de residente");
		System.out.println("5. Comienza mes");
		System.out.println("6. Pagos de residentes");
		System.out.println("7. Salir");
		System.out.println("Digite la opcion");
		
	}
	
	public static void menuPrincipal() {

		Parqueadero pqd = new Parqueadero();
		pqd.validaDirectorio();
		pqd.validaArchivo();
		pqd.menu();
		opcion= teclado.nextInt();
       while (opcion != 7) {
			switch (opcion) {
			case 1:
				pqd.ingresoVehiculo();
				pqd.menu();
		
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				System.exit(0);
				break;
			default:
				System.out.println("error");
				break;
			}
			opcion = teclado.nextInt();	
       }	
	}

	public static void main(String[] args) {
		menuPrincipal();

	}

}
