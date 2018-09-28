package data;

public class Company{
    String name, catchPhrase, bs;

    public Company(){

    }

	public Company(String name, String catchPhrase, String bs) {
		super();
		this.name = name;
		this.catchPhrase = catchPhrase;
		this.bs = bs;
	}

	@Override
	public String toString() {
		return name;
	}
}