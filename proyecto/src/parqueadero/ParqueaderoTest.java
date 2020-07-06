package parqueadero;


import org.junit.jupiter.api.Test;

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

class ParqueaderoTest {



	@Test
	void testValidaParqueadero() throws Exception {
		String nroPlaca ="";
		String horaEntrada="",horasalida="";
		int tipoveh=0,estado=0;		
		registroPlaca rp = new registroPlaca(nroPlaca, horaEntrada, horasalida, tipoveh, estado);
		
		rp.validaDirectorio();	
		rp.validaArchivo();		
		rp.iniciar();	
		Parqueadero p = new Parqueadero(nroPlaca, horaEntrada, horasalida, tipoveh, estado);
		p.menu();
		rp.ingresoVeh();		
		rp.escrituraArchivo();
		rp.SalidaVeh();
		p.menuPrincipal();
	}
	
	


	
	
	


	
}
