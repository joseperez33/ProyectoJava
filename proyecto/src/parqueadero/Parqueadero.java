package parqueadero;

import java.util.Scanner;

/**
 * 
 * @author Jhonattan.Diaz
 * @author Jose.Perez
 * @author Mariano.Jara
 * 
 * @since 01/07/2020
 * @version 1 
 *
 */
public class Parqueadero  extends registroPlaca{
	
	public Parqueadero(String nroPlaca, String horaEntrada, String horasalida, int tipoveh,int estado) {
		super(nroPlaca, horaEntrada, horasalida, tipoveh, estado);
	}

	private Scanner teclado = new Scanner(System.in);
	private int opcion;
	private static Parqueadero pqd = new Parqueadero("","","",0,0);
	
	private void menu() {
		System.out.println("Menu Parqueadero");
		System.out.println("1. Registrar entrada");
		System.out.println("2. Registrar salida");
		System.out.println("3. Da de alta vehículo oficial");
		System.out.println("4. Da de alta vehículo de residente");
		System.out.println("5. Comienza mes");
		System.out.println("6. Pagos de residentes");
		System.out.println("7. Salir");
		System.out.print("Digite la opcion ");		
	}
	
	private void menuPrincipal() {		
		pqd.validaDirectorio();
		pqd.validaArchivo();
		pqd.iniciar() ;
		pqd.menu();
		try {
			opcion= teclado.nextInt();
		       while (opcion != 7) {
					switch (opcion) {
					case 1:
						pqd.ingresoVeh();
						pqd.menu();	
						pqd.escrituraArchivo();
						break;
					case 2:
						pqd.SalidaVeh();
						pqd.menu();
						pqd.escrituraArchivo();
						break;
					case 3:
						pqd.altaVehOficial();
						pqd.menu();
						pqd.escrituraArchivo();
						break;
					case 4:
						pqd.altaVehRes();
						pqd.menu();
						pqd.escrituraArchivo();
						break;
					case 5:
						pqd.imprimir();
						pqd.menu();
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
		} catch (Exception e) {
			System.out.println("Opción incorrecta");
		}
			
	}

	public static void main(String[] args) {		
		pqd.menuPrincipal();

	}

}
