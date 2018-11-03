package data;

class Company{
    String name, catchPhrase, bs;

    Company(){

    }

	Company(String name, String catchPhrase, String bs) {
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