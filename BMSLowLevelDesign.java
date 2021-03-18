
import java.util.ArrayList;
import java.util.List;

/*
 * Assumption :- 
 * 1 .Only one movie show run at a time in a particular cinemas.
 * 2. Each city can have multiple theaters;
 * 3. Each theater can have multiple cinemas.
 */


/* Requirements:-
 * 
 * 1. The system should allow the registration of new movie theaters 
 * 2. Owners should be able to add new movie shows
 * 3. The system should be able to list down cities where it's cinema are located.
 * 4. Upon selecting the city, the system should display the movies released in that particular city to that user.
 * 5. The user should be able to select the show from a cinema and book their tickets.
 * 6. The user should be able to select multiple seats according to their choice.
 * 7. The user should be able to distinguish between available seats from the booked ones.
 * 8. The system should serve the tickets First In First Out manner
 */



/*
 * This class represents our system
 * It has list of users,owners and cities. 
 */
public class BookMyShowLowLevelDesign{ 
	private List<Users>  users;    // List of users for our system
	
	private List<Owners> owners;   // List of admins Who has the permission to add/edit/update
	
	private List<City>   cities;   // List of cities where theaters are situated
	
	/*
	 * For requirement 3:-
	 * The system should be able to list down cities where it's cinema are located.
	 */
	
	protected List<City> listAllCities(){return cities;}  // API for listing all cities
	
	/*
	 * For requirement 4
	 * Upon selecting the city, the system should display the movies released in that particular city to that user.
	 */
	
	protected List<Cinema> listAllCinema(City c)
	{
		List<Cinema> ls = new ArrayList<Cinema>();
		for(Theater t : c.getTheaters())
		{
			ls.addAll(t.getCinema());
		}
		return ls;
	}
	
	
}	


// Base class which serves as a parent for both Users and Owners
// Any person can have access for our system
class Person extends BookMyShowLowLevelDesign {
	private String name;
	private int age;
	private String phone;
}

class Users extends Person{
	 
	/*
	 * For requirement 5.
	 * The user should be able to select the show from a cinema and book their tickets.
	 */
	protected List<Seat> selectCinemaAndBook(Cinema c) {
		// get List of Seats
       return c.getSeat();
	}
	
	/*
	 * book Single Seat
	 */
	protected void bookSeat(Seat s)
	{
		s.doBooking();
	}
	
	// book Multiple seats
	protected void bookMultipleSeats(List<Seat> listOfSeat)
	{
	    for(Seat seat : listOfSeat)bookSeat(seat);	
	}
	
	/*
	 * Requirement 7 
	 * 7. The user should be able to distinguish between available seats from the booked ones.
	 */
	protected boolean isBooked(Seat s)
	{
		return s.isBooked();
	}
}

class Owners extends Person{
	/*
	 *  For requirement 1 :- 
	 *  Admins can add new theater in particular city
	 */
	 private void addTheater(Theater t,City ct) {ct.appendTheater(t);} 
	 
	 
	 /*
	  * For requirement 2 :-
	  * Admins can add new a cinema(movie) in a particular theater
	  */
	 
     private void addCinema(Cinema c,Theater t) {t.appendCinemas(c);} 
}

class City{
	private String name;
	private List<Theater> theaters; // Each city will have a list of theaters
	protected void appendTheater(Theater t) {}  //  For appending a theater in the list of theater
	public List<Theater> getTheaters(){return theaters;}
}

class Theater{
	String theaterName;
	List<Cinema> cinemas;
	protected void appendCinemas(Cinema c) {} // For appending a cinema in the list of cinemas
	public List<Cinema> getCinema() {return cinemas;}
}

class Cinema{
	int startTime;
	int endTime;
	List<Seat> seats;
	protected List<Seat> getSeat(){
		return seats;
	}
}

class Seat{
	int no;
	boolean booked;
	Seat(int no){
		booked = false;
		this.no = no;
	}
	
	protected void doBooking() {
	
		this.booked = true;
	}
	
	protected boolean isBooked() {
		return booked;
	}
}

