package data;

class Address{
    String street, suite, city, zipcode;
    MapCoordinate geo;

    Address(){

    }

    Address(String street, String suite, String city, String zipcode, MapCoordinate geo) {
		super();
		this.street = street;
		this.suite = suite;
		this.city = city;
		this.zipcode = zipcode;
		this.geo = geo;
	}
	
    Address(String text, MapCoordinate geo) {
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
	
	String PrintValue() {
		return String.format("%s,%s,%s,%s", street, suite, city, zipcode);
	}
	
	static boolean ValidAddress(String text) {
		String[] s = text.split(",");
		return s.length == 4;
	}
}