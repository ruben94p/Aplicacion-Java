package data;

import java.util.LinkedList;
import java.util.Optional;

import com.google.gson.Gson;
import java.nio.file.*;
import java.io.*;

class UserList extends LinkedList<User> {

	UserList() {
		super();
	}
	
	/**
	 * Despliega los datos de cada usuario en la lista en el formato especificado
	 */
	void Display() {
		for(User user : this) {
			System.out.println(user);
		}
	}
	
	/**
	 * Verifica si existe en la lista un usuario con el ID proporcionado
	 * @param id ID del usuario
	 * @return true = existe, false = no existe
	 */
	boolean Exists(int id) {
		return this.stream()
			.filter(user -> user.id == id)
			.count() > 0;
	}
	
	/**
	 * Busca a un usuario por ID y regresa los datos de este
	 * @param id ID del usuario
	 * @return Usuario con el ID solicitado, null en caso de que no exista en la lista
	 */
	User Get(int id) {
		Optional<User> user = this.stream()
				.filter(u -> u.id == id)
				.findFirst();
		if(user.isPresent())
			return user.get();
		
		return null;
	}
	
	/**
	 * Elimina al usuario con el ID proporcionado
	 * @param id ID del usuario
	 * @return true = existia y fue eliminado, false = no existia un usuario y no se modifico la lista
	 */
	boolean Remove(int id) {
		Optional<User> userToRemove = this.stream()
				.filter(user -> user.id == id)
				.findFirst();
		if(userToRemove.isPresent()) {
			remove(userToRemove.get());
			return true;
		}
		return false;
	}

	/**
	 * Crea un archivo y escribe los datos de la lista
	 * @param filename nombre del archivo, la extension ".txt" es agregada automaticamente en caso de no tenerla
	 * @throws IOException
	 */
	void Export(String filename) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<size();i++) {
			sb.append(get(i).toString());
			if(i != size()-1)
				sb.append('\n');
		}
		
		if(!filename.endsWith(".txt"))
			filename += ".txt";
		
		Path file = Paths.get(filename);
		
		BufferedWriter writer = Files.newBufferedWriter(file);
		writer.write(sb.toString(), 0, sb.length());
		writer.flush();
		writer.close();
	}
	
	/**
	 * Genera la lista del JSON obtenido del sitio web
	 * @param json JSON a deserializar
	 * @return Lista de usuarios del JSON
	 */
	static UserList fromJson(String json) {
    	return new Gson().fromJson(json, UserList.class);
    }
}
