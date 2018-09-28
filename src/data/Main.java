package data;
import java.io .*;
import java.net.*;
import java.util.Scanner;

public class Main{
	static UserList users;
	static Scanner scanner;
	
    public static void main(String[] args)
    {
    	String json = DownloadData();
    	if(json != null) {
    		users = UserList.fromJson(json);
    		
    		boolean quit = false;
    		do {
    			users.Display();
    			quit = Options();
    		}while(!quit);
    	}
    }
    
    static boolean Options() {
    	System.out.println("Opciones:\n1 : Editar\n2 : Eliminar\n3 : Exportar a archivo\n4 : Terminar");
    	scanner = new Scanner(System.in);
    	char c = scanner.nextLine().charAt(0);
    	switch(c) {
    	case '1':
    		Edit();
    		break;
    	case '2':
    		Remove();
    		break;
    	case '3':
    		Export();
    		break;
    	case '4':
    		return true;
    	}
    	return false;
    }
    
    static void Edit() {
    	System.out.println("Ingrese el ID del usuario a editar");
    	int id = scanner.nextInt();
    	User user = users.Get(id);
    	if(user != null) {
    		System.out.println("Ingrese el campo a editar:\n"
    				+ "1 : Nombre\n2 : Usuario\n3 : Correo electronico\n"
    				+ "4 : Dirección\n5 : Latitud\n6 : Longitud\n7 : Telefono\n"
    				+ "8 : Sitio web\n9 : Compañia");
    		int n = scanner.nextInt();
    		scanner = new Scanner(System.in);
    		switch(n) {
    		case 1:
    			System.out.println(user.name);
    			user.name = scanner.nextLine();
    			break;
    		case 2:
    			System.out.println(user.username);
    			user.username = scanner.nextLine();
    			break;
    		case 3:
    			System.out.println(user.email);
    			user.email = scanner.nextLine();
    			break;
    		case 4:
    			System.out.println(user.address);
    			String temp = scanner.nextLine();
    			if(Address.ValidAddress(temp))
    				user.address = new Address(temp, user.address.geo);
    			else
    				System.out.println("Dirección invalida");
    			break;
    		case 5:
    			System.out.println(user.address.geo.lat);
    			user.address.geo.lat = scanner.nextLine();
    			break;
    		case 6:
    			System.out.println(user.address.geo.lng);
    			user.address.geo.lng = scanner.nextLine();
    			break;
    		case 7:
    			System.out.println(user.phone);
    			user.phone = scanner.nextLine();
    			break;
    		case 8:
    			System.out.println(user.website);
    			user.website = scanner.nextLine();
    			break;
    		case 9:
    			System.out.println(user.company.name);
    			user.company.name = scanner.nextLine();
    			break;
    		
    			default:
    				System.out.println("Opción desconocida");
    		}
    	}
    	else {
    		System.out.println("El usuario seleccionado no existe");
    	}
    }
    
    static void Remove() {
    	System.out.println("Ingrese el ID del usuario a eliminar");
    	int id = scanner.nextInt();
    	if(users.Exists(id)) {
    		if(users.Remove(id))
    			System.out.println("Usuario eliminado");
    		else
    			System.out.println("Error");
    	}
    	else {
    		System.out.println("El usuario seleccionado no existe");
    	}
    }
    
    static void Export() {
    	System.out.println("Ingrese el nombre del archivo");
    	scanner = new Scanner(System.in);
    	String filename = scanner.nextLine();
    	try {
    		users.Export(filename);
    	}
    	catch(IOException e) {
    		System.out.println("Error al generar el archivo");
    		e.printStackTrace();
    	}
    }
    
    static String DownloadData() {
    	try {
    		URL oracle = new URL("https://jsonplaceholder.typicode.com/users");
            BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));

            String data = "";
            String temp;
            while ((temp = in.readLine()) != null) {
            	data += temp;
            }
            in.close();
            
            return data;
    	}
    	catch(MalformedURLException urlException) {
    		//Error en la URL
    	}
    	catch(IOException ioException) {
    		//Error obteniendo el json del sitio
    		System.out.println("Error al conectarse con el origen de datos, verifique que tenga conexión a Internet");
    	}
    	return null;
    }
}