import java.util.Scanner;

public class LucyTattooParlor {
	public static void main(String[] args){
		int numOfArtist = Integer.parseInt(args[0]);
		int capacity = Integer.parseInt(args[1]);
		
//		int numOfArtist = 3;
//		int capacity = 5;
		TattooCustomer[][] tattooCustomers = new TattooCustomer[numOfArtist][capacity];
		Scanner input = new Scanner(System.in);
		
		boolean full = false;
		while (!full){
			System.out.println("welcome!");
			System.out.println("what is your name?");
			String name = input.next();
			System.out.println("what tattoo do you want?");
			String tattoo = input.next();
			
			int artistNum;
			while (true){
				System.out.println("if you want specific artist, choose his number between 0 to " + (numOfArtist-1) + " or enter -1 to choose least waiting time");
				artistNum = input.nextInt();
				if (artistNum >= -1 && artistNum < numOfArtist)
					break;
				System.out.println("please enter a valid number");
			}

			System.out.println("how many minutes do you need?");
			int minutes = input.nextInt();
			TattooCustomer newCustomer = new TattooCustomer(name, tattoo, minutes);
			
			boolean success;
			if (artistNum != -1)
				success = addCustomer(tattooCustomers, newCustomer, artistNum);
			else
				success = addCustomer(tattooCustomers, newCustomer);
			
			if (!success){
				System.out.println("would you like to choose another artist or reduce your time?");
				continue;
			}
			
			int fullNumArtist = 0;
			for (int i = 0; i < numOfArtist; i++){
				if (tattooCustomers[i][capacity - 1] != null)
					fullNumArtist ++;
			}
			
			if (fullNumArtist == numOfArtist)
				full = true;
			
			printList(tattooCustomers);
		}
		
		printList(tattooCustomers);
		System.out.println(full);
		
	}
	
	public static int computeMinutesOfWork(TattooCustomer[] a){
		int totalMin = 0;
		int i = 0;
		while (i < a.length && a[i] != null){
			totalMin += a[i].getMinutes();
			i++;
		}
		
		return totalMin;
	}
	
	public static boolean addCustomer(TattooCustomer[][] a, TattooCustomer c, int artistNum){
		if (computeMinutesOfWork(a[artistNum]) + c.getMinutes() > 8*60)
			return false;
		
		for (int i = 0; i < a[artistNum].length; i++){
			if (a[artistNum][i] == null){
				a[artistNum][i] = c;
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean addCustomer(TattooCustomer[][] a, TattooCustomer c){
		int shortestNum = 0; 
		for (int i = 1; i < a.length; i++){
			if (computeMinutesOfWork(a[i]) < computeMinutesOfWork(a[shortestNum]))
				shortestNum = i;
		}
		
		return addCustomer(a, c, shortestNum);
	}
	
	public static void printList(TattooCustomer[][] a){
		for (int i = 0; i < a.length; i++){
			for (int j = 0; j < a[0].length; j++){
				if (a[i][j] == null)
					System.out.print("\t");
				else
					System.out.print(a[i][j].getName() + " ");
			}
			System.out.println("");
		}
	}
}
