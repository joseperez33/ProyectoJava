package parqueadero;


import java.util.Date;
//import java.util.Scanner;
import java.text.SimpleDateFormat;

public  class vehiculo {
	private String nroPlaca, horaFormato;
	private Date horaEntrada;
	

	public String getNroPlaca() {
		return nroPlaca;
	}

	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}

	public String getHoraEntrada() {
		SimpleDateFormat formateo = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		horaFormato = formateo.format(horaEntrada);
		return horaFormato;
	}

	public void setHoraEntrada() {
		this.horaEntrada = new Date(); 
	}

}
