package ticket;

import cinemaArrange.Seat;
import cinemaArrange.Show;

public class SeniorTicketBuilder extends TicketBuilder {

	
	
	@Override
	public void buildSeat(Seat seat) {
		this.myTicket.seat=seat;
		this.myTicket.position=seat.position;
		
		
	}

	@Override
	public void buildShow(Show show) {
		this.myTicket.show=show;
		this.myTicket.type="SENIOR";
		this.myTicket.discount=Ticket.SENIOR;
		this.myTicket.movieName=show.movie.name;
		this.myTicket.time=show.hour+":"+show.minute;
		this.myTicket.screenID=show.screen.id;
		this.myTicket.howLong=""+show.movie.timeUsed;
		this.myTicket.date=""+show.date;
	}

	@Override
	public void buildStudentID(String sid) {
		
		this.myTicket.studentID="";

	}

	@Override
	public Ticket getTicket() {
		
		return this.myTicket;
	}
	@Override
	public void buildID(String ID) {
		this.myTicket.ID=ID;
		
	}

}
