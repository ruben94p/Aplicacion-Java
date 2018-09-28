package data;

import java.util.LinkedList;
import java.util.Optional;

import com.google.gson.Gson;
import java.nio.file.*;
import java.io.*;

public class UserList extends LinkedList<User> {

	public UserList() {
		super();
	}
	
	public void Display() {
		for(User user : this) {
			System.out.println(user);
		}
	}
	
	public boolean Exists(int id) {
		return this.stream()
			.filter(user -> user.id == id)
			.count() > 0;
	}
	
	public User Get(int id) {
		Optional<User> user = this.stream()
				.filter(u -> u.id == id)
				.findFirst();
		if(user.isPresent())
			return user.get();
		
		return null;
	}
	
	public boolean Remove(int id) {
		Optional<User> userToRemove = this.stream()
				.filter(user -> user.id == id)
				.findFirst();
		if(userToRemove.isPresent()) {
			remove(userToRemove.get());
			return true;
		}
		return false;
	}

	public void Export(String filename) throws IOException {
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
	
	public static UserList fromJson(String json) {
    	return new Gson().fromJson(json, UserList.class);
    }
}
