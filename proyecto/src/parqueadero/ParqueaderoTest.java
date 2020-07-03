package parqueadero;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class ParqueaderoTest {



	@Test
	void testValidaParqueadero() throws Exception {
		String data = "vrd123\r\n";
		java.io.InputStream stdin = System.in;
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
		
		try {
			  System.setIn(new ByteArrayInputStream(data.getBytes()));
			  Scanner scanner = new Scanner(System.in);
			  System.out.println(scanner.nextLine());
			} finally {
			  System.setIn(stdin);
			}
		
	}
	
	


	
	
	


	
}
