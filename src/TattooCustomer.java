
public class TattooCustomer {
	private String name;
	private String tattoo;
	private int minutes;
	
	public TattooCustomer(String name, String tattoo, int minutes) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.tattoo = tattoo;
		this.minutes = minutes;
	}
	
	public String getName(){
		return name;
	}
	
	public String getTattoo(){
		return tattoo;
	}
	
	public int getMinutes(){
		return minutes;
	}
}
