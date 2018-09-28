package data;

public class User{
    int id;
    String name, username, email, phone, website;

    Address address;
    Company company;

    public User(){

    }
    
    public User(int id, String name, String username, String email, String phone,
    		String website, Address address, Company company) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.address = address;
		this.company = company;
	}

	@Override
	public String toString() {
		return String.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s",id, name, username, email, address, phone, website, company);
	}
}