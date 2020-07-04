package parqueadero;

import java.io.ByteArrayInputStream;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class ParqueaderoTest {



	@Test
	void testValidaParqueadero() throws Exception {
		Robot robot = new Robot();
		String nroPlaca ="";
		String horaEntrada="",horasalida="";
		int tipoveh=0,estado=0;		
		registroPlaca rp = new registroPlaca(nroPlaca, horaEntrada, horasalida, tipoveh, estado);
		
		rp.validaDirectorio();	
		rp.validaArchivo();		
		rp.iniciar();	
		Parqueadero p = new Parqueadero(nroPlaca, horaEntrada, horasalida, tipoveh, estado);
		p.menu();
		//p.menuPrincipal();
		/*robot.keyPress(KeyEvent.VK_1);
        robot.keyRelease(KeyEvent.VK_1);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);*/
		rp.ingresoVeh();		
		// Simulate a key press
       /*robot.keyPress(KeyEvent.VK_V);
       robot.keyRelease(KeyEvent.VK_V);
       robot.keyPress(KeyEvent.VK_D);
       robot.keyRelease(KeyEvent.VK_D);
       robot.keyPress(KeyEvent.VK_F);
       robot.keyRelease(KeyEvent.VK_F);
       robot.keyPress(KeyEvent.VK_4);
       robot.keyRelease(KeyEvent.VK_4);
       robot.keyPress(KeyEvent.VK_5);
       robot.keyRelease(KeyEvent.VK_5);
       robot.keyPress(KeyEvent.VK_6);
       robot.keyRelease(KeyEvent.VK_6);
       robot.keyPress(KeyEvent.VK_ENTER);
       robot.keyRelease(KeyEvent.VK_ENTER);*/
		rp.escrituraArchivo();
		rp.SalidaVeh();
		p.menuPrincipal();
	}
	
	


	
	
	


	
}
