import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DatosEquipos {

	static char claseIP(byte[] ip) {
		int highByte = 0xff & ip[0];
		return (highByte < 128)
				?'A'
				: (highByte < 192)
				?'B' 
				: (highByte < 224)
				?'C' 
				: (highByte < 240)
				?'D' 
				: 'E';
	}
	
	static void direccionLocal() {
		try {
			InetAddress local = InetAddress.getLocalHost();
			System.out.println("Nombre del equipo local: "+local.getHostName());
			System.out.println("Dirección IP "+local.getHostAddress());
			System.out.println("Clase de la dirección IP: "+claseIP(local.getAddress()));
		}catch (UnknownHostException ex) {
			System.out.println("No se pudo encontrar el equipo local");
			ex.printStackTrace();
		}
	}
	
	static void direccionRemota(String nombre) {
		try {
			System.out.println("Buscando "+nombre+"...");
			InetAddress equipo = InetAddress.getByName(nombre);
			System.out.println("Nombre del Host: "+equipo.getHostName());
			System.out.println("IP del Host: "+equipo.getHostAddress());
			System.out.println("Clase de la dirección IP del Host: "+claseIP(equipo.getAddress()));
		}catch (UnknownHostException ex) {
			System.out.println("No se pudo encontrar a "+nombre);
		}
	}
	
	public static void main(String [] args) throws IOException {
		direccionLocal();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cadena;
		do {
			cadena = br.readLine(); 
			if(!cadena.equals("FIN") && cadena!=null) {
				direccionRemota(cadena);
			}
		}while(!cadena.equals("FIN"));
		System.out.println("Adios");
	}
}
