package parqueadero;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

/**
 * Posicionamiento de los registros dentro del archivo Posicion 0= Placa
 * Posicion 1= hora de ingreso Posicion 2= tipo de vehiculo(1 oficial, 2
 * residente, 3 visitante) Posicion 4= segun tipo de vehiculo(tipo 1 hora de
 * salida, tipo 2 suma de tiempo al total acumulado, tipo 3 importe a pagar)
 * posicion 5= estado vehiculo (0 en parqueadero, 1 afuera parqueadero)
 */

public class registroPlaca extends vehiculo {

	public registroPlaca(String nroPlaca, String horaEntrada, String horasalida, int tipoveh, int estado) {
		super(nroPlaca, horaEntrada, horasalida, tipoveh, estado);
	}

	//Atributos
	private Scanner teclado;
	private File archivo = null;
	private FileReader lector = null;
	private FileWriter escritor = null;
	private BufferedReader bRead = null;
	private BufferedWriter bWriter = null;
	private String nroplaca, horaentrada, horasalida, nroPlaca2, registro;
	private int tipoVeh, tipoveh, posicion, estado;
	private int preciominr = 25;
	private int preciominv = 50;
	private Date date;
	private SimpleDateFormat formatter;
	private ArrayList<vehiculo> datosvehiculo = new ArrayList<vehiculo>();

	/*-----------------------------Metodos definitivos-------------------------------------------*/
	
	/**
	 * Metodo para crear la carpeta Parqueadero
	 */
	public void validaDirectorio() {
		File directorio = new File("C:\\parqueadero");
		if (!directorio.exists()) {
			System.out.println("/*************************************/");
			if (directorio.mkdirs()) 
				System.out.println("Directorio principal C:\\parqueadero creado");
			 else 
				System.out.println("Error al crear directorio C:\\parqueadero");
		
		}
	}

	
	/***
	 * Metodo que valida q exista el archivo registro_parqueo
	 * 
	 * @throws IOException
	 */
	public void validaArchivo() throws IOException  {

		archivo = new File("C:\\parqueadero\\registro_parqueo.db");
		if (!archivo.exists()) {
			archivo.createNewFile();
			System.out.println("Archivo registro_parqueo.db creado");
			System.out.println("/*************************************/");
		} else
			System.out.println("Iniciado registro_parqueo.db");
		
	}

	
	/**
	 * Metodo para escribir en el archivo registro_parqueo
	 * @throws IOException
	 */
	public void iniciar() throws IOException {
		String linea = "";
		
			lector = new FileReader(new File("C:\\parqueadero\\registro_parqueo.db"));
			bRead = new BufferedReader(lector);
			// linea = bRead.readLine();
			while (linea != null) {
				if ((linea = bRead.readLine()) != null) {
					cadena(linea);
					vehiculo parq = new vehiculo(nroplaca, horaentrada, horasalida, tipoveh, estado);
					datosvehiculo.add(parq);
				}
			}
			bRead.close();
			lector.close();
	
	}

	/**
	 * Metodo para imprimr la informacion de los vehiculos del parqueadero
	 */
	
	public void imprimir() {
		for (int a = 0; a < datosvehiculo.size(); a++) {
			System.out.println("Placa: " + datosvehiculo.get(a).getNroPlaca() + " hora entrada: "+ datosvehiculo.get(a).getHoraEntrada() + " tipo veh: " + datosvehiculo.get(a).getTipoVeh()+ " hora salida: " + datosvehiculo.get(a).getHoraSalida() + " Estado: "+ datosvehiculo.get(a).getEstado());
		}
	}

	
	/**
	 * Metodo para digitar el tipo del vehiculo
	 */
	private void submenu() {
		System.out.println("/*************************************/");
		System.out.println("Seleccione el tipo de vehiculo");
		System.out.println("1. Oficial");
		System.out.println("2. Residente");
		System.out.println("3. Visitante");
		tipoVeh = getTeclado().nextInt();
	}
	
	/**
	 * Metodo para leer archivo y dividir la cadena en variables
	 * @param in
	 */

	private void cadena(String in) {
		if (in != null || in != "") {
			String cadena[] = in.split("@");
			if (cadena.length > 0) {
				nroplaca = cadena[0];
				horaentrada = cadena[1];
				tipoveh = (Integer.valueOf(cadena[2]));
				horasalida = cadena[3];
				estado = (Integer.valueOf(cadena[4]));
			}
		}
	}
	
	/**
	 * Metodo para la captura de la placa
	 */

	private void capturaplaca() {
		nroPlaca2 = "";
		System.out.println("/*************************************/");
		System.out.print("Ingrese el numero de placa: ");
		nroPlaca2 = (getTeclado().next()).toUpperCase();
	}
	
	/**
	 * Metodo paraingresar el vehiculo al parqueadero
	 */

	public void ingresoVeh() {
		capturaplaca();
		submenu();
		nroplaca = nroPlaca2;
		horaentrada = formatDate();
		tipoveh = tipoVeh;
		horasalida = "0";
		estado = 0;
		if (!busquedaPlaca()) {
			vehiculo parq = new vehiculo(nroplaca, horaentrada, horasalida, tipoveh, estado);
			datosvehiculo.add(parq);
			System.out.println("HORA INGRESO: " + parq.getHoraEntrada());
		} else {
			actualizaResidente();
			System.out.println("HORA INGRESO: " + datosvehiculo.get(posicion).getHoraEntrada());
		}
		System.out.println("/*************************************/");
	}

	
	/**
	 * 
	 */
	private void actualizaResidente() {
		datosvehiculo.get(posicion).setHoraEntrada(formatDate());
	}
	
	/**
	 * Metodo para el mensaje de salida exitosa
	 */

	private void mensaje() {
		System.out.println("/**************************************/");
		System.out.println("/**********Salida Exitosa**************/");
		System.out.println("/**************************************/");
	}
	
	/**
	 * Metodo para sacar el vehiculo del parqueadero
	 */

	public void SalidaVeh() {
		String dato = "";
		capturaplaca();
		if (busquedaPlaca()) {
			switch (tipoVeh) {
			case 1:
				datosvehiculo.get(posicion).setHoraSalida(formatDate());
				datosvehiculo.get(posicion).setEstado(1);
				mensaje();
				break;
			case 2:
				dato = String.valueOf(diferenciaHoras(formateo(datosvehiculo.get(posicion).getHoraEntrada()),
						formateo(formatDate())));
				datosvehiculo.get(posicion).setHoraEntrada("0");
				datosvehiculo.get(posicion).setHoraSalida(String.valueOf(
						Integer.valueOf(datosvehiculo.get(posicion).getHoraSalida()) + (Integer.valueOf(dato))));
				mensaje();
				break;
			case 3:
				dato = String
						.valueOf(preciominv * diferenciaHoras(formateo(datosvehiculo.get(posicion).getHoraEntrada()),
								formateo(formatDate())));
				System.out.println("/**************************************/");
				System.out.println("El valor a pagar del vehículo de placa: ");
				System.out.println(datosvehiculo.get(posicion).getNroPlaca() + " es: $" + dato);
				datosvehiculo.get(posicion).setHoraSalida(dato);
				datosvehiculo.get(posicion).setEstado(1);
				mensaje();
				break;
			default:
				break;
			}
		} else {
			System.out.println("El vehiculo no se encuentra registrado en el sistema");
		}
	}

	
	/**
	 * Metodo para buscar la placa  
	 * @return retorno
	 */
	private boolean busquedaPlaca() {
		boolean retorno = false;
		for (int a = 0; a < datosvehiculo.size(); a++) {
			if ((datosvehiculo.get(a).getNroPlaca().equals(nroPlaca2)) && (datosvehiculo.get(a).getEstado() == 0)) {
				retorno = true;
				posicion = a;
				tipoVeh = datosvehiculo.get(a).getTipoVeh();
			}
		}
		return retorno;
	}
	
	/**
	 * Metodo para darle el formato de la fecha
	 * @return retorno
	 */

	private String formatDate() {
		String retorno = "";
		date = new Date();
		formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		retorno = (formatter.format(date));
		return retorno;
	}
	
	/**
	 * Metodo para calcular el tiempo que duro el vehiculo en el parqueadero 
	 * 
	 * @param horaIni
	 * @param horaFin
	 * @return total
	 */

	private int diferenciaHoras(Date horaIni, Date horaFin) {
		int total = 0;
		long milliseconds = horaFin.getTime() - horaIni.getTime();
		long minutes = (milliseconds / 60000);
		total = ((int) minutes);
		return total;
	}

	
	/**
	 * Metodo para formatear la fecha
	 * 
	 * @param dato
	 * @return date1
	 */
	private Date formateo(String dato) {
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(dato);
		} catch (Exception e) {

		}
		return date1;
	}

	
	/**
	 *Metodo para crear la informacion que va en el archivo
	 * 
	 * @throws IOException
	 */
	
	public void escrituraArchivo() throws IOException {
		borrar();
		System.out.println(datosvehiculo.size());
		int a= datosvehiculo.size()-1;
		//for (int a = 0; a < datosvehiculo.size(); a++) {
			registro = datosvehiculo.get(a).getNroPlaca() + '@' + datosvehiculo.get(a).getHoraEntrada() + '@'
					+ datosvehiculo.get(a).getTipoVeh() + '@' + datosvehiculo.get(a).getHoraSalida() + '@'
					+ datosvehiculo.get(a).getEstado();
			escritura(registro);
		//}
	}

	
	/**
	 * Metodo para escribir el archivo
	 * 
	 * @param registro
	 * @throws IOException
	 */
	
	private void escritura(String registro) throws IOException {
		FileWriter fichero = null;
		
			fichero = new FileWriter("C:\\parqueadero\\registro_parqueo.db", true);
			PrintWriter printWriter = new PrintWriter(fichero);
			printWriter.println(registro); // New line
			printWriter.close();
		
	}

	/**
	 * Metodo para borrar el archivo
	 * 
	 */
	
	public void borrar() {
		String linea = "";
		try {
			lector = new FileReader(new File("C:\\parqueadero\\registro_parqueo.db"));
			escritor = new FileWriter(new File("C:\\parqueadero\\registro_parqueo.db"));
			bRead = new BufferedReader(lector);
			bWriter = new BufferedWriter(escritor);
			while ((linea = bRead.readLine()) != null) {
				System.out.println("Ingreso");
				bWriter.write(linea + System.getProperty("line.separator"));
			}
			bRead.close();
			lector.close();
		} catch (IOException ex) {
			System.out.println("Problemas con la lectura del archivo");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Metodo para marcar el vehiculo como oficial
	 * 
	 */
	
	public void altaVehOficial() {
		capturaplaca();
		if (busquedaPlaca()) {
			datosvehiculo.get(posicion).setTipoVeh(1);
			mensajeAlta(1);

		} else {
			mensajeAlta(3);
		}
	}
	
	/**
	 *  Metodo para marcar el vehiculo como residente
	 * 
	 */

	public void altaVehRes() {
		capturaplaca();
		if (busquedaPlaca()) {
			datosvehiculo.get(posicion).setTipoVeh(2);
			mensajeAlta(2);
		} else {
			mensajeAlta(3);
		}
	}

	
	/**
	 * Metodo para marcar los vehiculos
	 * 
	 * @param param
	 */
	private void mensajeAlta(int param) {
		switch (param) {
		case 1:
			System.out.println("/**************************************/");
			System.out.println("Vehiculo agregado a la lista de vehiculos oficiales");
			System.out.println("/**************************************/");
			break;
		case 2:
			System.out.println("/**************************************/");
			System.out.println("Vehiculo agregado a la lista de vehiculos residenciales");
			System.out.println("/**************************************/");
			break;
		case 3:
			System.out.println("/**************************************/");
			System.out.println("El vehiculo no se encuentra en el sistema");
			System.out.println("/**************************************/");
			break;
		default:
			break;
		}
	}

	/**
	 * Metodo para el inicio del mes
	 * 
	 * @throws IOException
	 */
	
	public void comienzaMes() throws IOException {
		for (int a = 0; a < datosvehiculo.size(); a++) {
			if (datosvehiculo.get(a).getTipoVeh() == 1) {
				datosvehiculo.get(a).setHoraSalida("0");
			} else if (datosvehiculo.get(a).getTipoVeh() == 2) {
				datosvehiculo.get(a).setHoraSalida("0");
			}
		}
		System.out.println("/**************************************/");
		System.out.println("/*******Comienzo de mes exitoso********/");
		System.out.println("/**************************************/");
		escrituraArchivo();
	}

	/**
	 * Metodo para el pago de residentes
	 */
	
	public void pagoResidentes() {
		int dato = 0;
		System.out.println("/**************************************/");
		System.out.println("/**Matricula**||***Tiempo***||**Total**/");
		for (int a = 0; a < datosvehiculo.size(); a++) {
			if (datosvehiculo.get(a).getTipoVeh() == 2) {
				dato = diferenciaHoras(formateo(datosvehiculo.get(a).getHoraEntrada()), formateo(formatDate()))
						+ Integer.valueOf(datosvehiculo.get(a).getHoraSalida());
				impresion(datosvehiculo.get(a).getNroPlaca(), dato);
			}
		}
		System.out.println("/**************************************/");
	}
	
	/**
	 * Metodo para imprimir el pago del residente
	 * 
	 * @param placa
	 * @param dato
	 */

	private void impresion(String placa, int dato) {
		System.out.println("/****" + placa + "*******" + dato + "*******" + (dato * preciominr));
	}
	
	/**
	 * Metodo para la captura de informacion por teclado
	 * 
	 * @return teclado
	 */

	private Scanner getTeclado() {
		if (teclado == null) {
			teclado = new Scanner(System.in);
		}
		return teclado;
	}

	/*-------------------------------------------------------------------------------------*/
}
