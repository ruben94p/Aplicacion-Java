package data;

public class Address{
    String street, suite, city, zipcode;
    MapCoordinate geo;

    public Address(){

    }

	public Address(String street, String suite, String city, String zipcode, MapCoordinate geo) {
		super();
		this.street = street;
		this.suite = suite;
		this.city = city;
		this.zipcode = zipcode;
		this.geo = geo;
	}
	
	public Address(String text, MapCoordinate geo) {
		this.geo = geo;
		
		String[] s = text.split(",");
		street = s[0];
		suite = s[1];
		city = s[2];
		zipcode = s[3];
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s\t%s\t%s", street, suite, city, zipcode, geo.lat, geo.lng);
	}
	
	public String PrintValue() {
		return String.format("%s,%s,%s,%s", street, suite, city, zipcode);
	}
	
	public static boolean ValidAddress(String text) {
		String[] s = text.split(",");
		return s.length == 4;
	}
}