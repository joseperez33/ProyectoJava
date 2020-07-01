package parqueadero;

public class vehiculo {
	private String nroPlaca;
	private String horaEntrada,horasalida;
	private int tipoveh,estado;
	
	
	public vehiculo(String nroPlaca, String horaEntrada, String horasalida, int tipoveh,int estado) {
		
		this.nroPlaca = nroPlaca;
		this.horaEntrada = horaEntrada;
		this.horasalida = horasalida;
		this.tipoveh = tipoveh;
		this.estado=estado;
	}
	
	
	public String getNroPlaca() {
		return nroPlaca;
	}

	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}
	
	public int getTipoVeh() {
		return tipoveh;
	}

	public void setTipoVeh(int tipo) {
		this.tipoveh = tipo;
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String hora) {
		this.horaEntrada = hora; 
	}
	
	public String getHoraSalida() {
		return horasalida;
	}

	public void setHoraSalida(String hora) {
		this.horasalida = hora; 
	}	

}
