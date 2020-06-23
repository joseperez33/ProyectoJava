package parqueadero;

import java.util.Calendar;

public abstract class vehiculo {

	private String nroPlaca;
	private Calendar horaEntrada;
	private Calendar horaSalida;

	public String getNroPlaca() {
		return nroPlaca;
	}

	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}

	public Calendar getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Calendar horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Calendar getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Calendar horaSalida) {
		this.horaSalida = horaSalida;
	}

}
