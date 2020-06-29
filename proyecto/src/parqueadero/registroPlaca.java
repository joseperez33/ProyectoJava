package parqueadero;

import java.io.*;
import java.util.Scanner;

class registroPlaca extends vehiculo{
	Scanner teclado;
	
	static File archivo = null;
    static File directorio  = null;
    static String nroPlaca2; 
    static String registro;


    public void validaDirectorio() {
    	 File directorio = new File("C:\\parqueadero");

         if (!directorio.exists()) {
        	 System.out.println("/*************************************/");
             if (directorio.mkdirs()) 
             	{
                 System.out.println("Directorio principal C:\\parqueadero creado");
             	} else {
                 System.out.println("Error al crear directorio C:\\parqueadero");
             	}
         	}
       
    }
    
    public void validaArchivo() {
    	 try {
             // Apertura del ARCHIVO
        	 archivo = new File ("C:\\parqueadero\\registro_parqueo.db");
        	 if (!archivo.exists()) {
        		 archivo.createNewFile();
        		 System.out.println("Archivo registro_parqueo.db creado");
        		 System.out.println("/*************************************/");
        	 }else
        	 { 
        		 System.out.println("/*************************************/");
        		 System.out.println("Iniciado registro_parqueo.db");
        		 System.out.println("/*************************************/");
        	 }	 
          }
          catch(Exception e){
             e.printStackTrace();
          }
     }
    public void cerrarArchivo() {
    	
    	
    }
    
    public void ingresoVehiculo() {
    	FileWriter fichero = null;
    	teclado = new Scanner(System.in);
    	registroPlaca rp = new registroPlaca();	
    	 System.out.println("/*************************************/");
    	System.out.println("Ingrese el numero de placa:");
    	nroPlaca2 = teclado.next();
    	rp.setNroPlaca(nroPlaca2);
    	rp.setHoraEntrada();
    	System.out.println("HORA INGRESO: " + rp.getHoraEntrada());
    	System.out.println("/*************************************/");
    	registro = rp.getNroPlaca().toUpperCase() + '@' + rp.getHoraEntrada();
    	try {
    	fichero  = new FileWriter("C:\\parqueadero\\registro_parqueo.db", true);   
		PrintWriter printWriter = new PrintWriter(fichero);
		printWriter.println(registro);  //New line
		printWriter.close();
    	}
        catch(Exception e){
           e.printStackTrace();
        }
    }
}    
