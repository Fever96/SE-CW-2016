package cinemaArrange;

import userInterface.ScreenInterface;

public class ArrangeTest {
	
	public static void main(String args[]){
		new ArrangeTest().resetAll();
	}
	
	public void resetAll(){
		int[][] layout={{1,1,1,1,0,1,1,1,1},{1,1,1,1,0,1,1,1,1},{1,1,1,1,0,1,1,1,1},{1,1,1,1,0,1,1,1,1}};
		Screen s=new Screen(layout,"1");
		CinemaDirector a =CinemaDirector.getUniqueDirector();
		a.allMovies.clear();
		a.allScreen.clear();
		a.difDayArran.clear();
		a.allScreen.add(s);
		System.out.println(s.id);
		
		
		int[][] layout2={{0,1,1,1,0,1,1,1,0},{0,1,1,1,0,1,1,1,0},{0,1,1,1,0,1,1,1,0},{1,1,1,1,0,1,1,1,1}};
		s=new Screen(layout2,"2");
		a.allScreen.add(s);
		
		int[][] layout3={{1,1,0,1,1,0,1,1},{1,1,0,1,1,0,1,1},{1,1,0,1,1,0,1,1},{1,1,1,1,1,1,1,1}};
		s=new Screen(layout3,"3");
		a.allScreen.add(s);
		
		//includes movies on all screens
		Movie m1=new Movie("KONG: SKULL ISLAND");
		Movie m2=new Movie("LOGAN");
		Movie m3=new Movie("BEAUTY AND THE BEAST");
		Movie m4=new Movie("MOONLIGHT");
		Movie m5=new Movie("LA LA LAND");
		m1.timeUsed=118;
		m2.timeUsed=135;
		m3.timeUsed=130;
		m4.timeUsed=111;
		m5.timeUsed=128;
		m1.setImg("images/1.jpg");
		m2.setImg("images/2.jpg");
		m3.setImg("images/3.jpg");
		m4.setImg("images/4.jpg");
		m5.setImg("images/5.jpg");
		m1.info="Kong: Skull Island is a 2017 American monster film directed by Jordan Vogt-Roberts and written by Dan Gilroy, Max Borenstein and Derek Connolly, from a story by John Gatins. The film is a reboot of the King Kong franchise and serves as the second film in Legendary's MonsterVerse. The film follows a team of scientists and Vietnam War soldiers who travel to an uncharted island in the Pacific and encounter terrifying creatures and the mighty Kong.";
		m2.info="Logan is a 2017 American superhero film featuring the Marvel Comics character Wolverine, produced by Marvel Entertainment, TSG Entertainment, and The Donners' Company, and distributed by 20th Century Fox. It is the tenth installment in the X-Men film series, as well as the third and final Wolverine solo film following X-Men Origins: Wolverine (2009) and The Wolverine (2013).The film takes inspiration from \"Old Man Logan\" by Mark Millar and Steve McNiven, which follows a past-his-prime Logan undertaking a final adventure in a dystopian future.";
		m3.info="Beauty and the Beast is a 2017 American musical romantic fantasy film directed by Bill Condon from a screenplay written by Stephen Chbosky and Evan Spiliotopoulos, and co-produced by Walt Disney Pictures and Mandeville Films. Beauty and the Beast is a live-action/CGI-animated film based on Disney's 1991 animated film of the same name, itself an adaptation of Jeanne-Marie Leprince de Beaumont's eighteenth-century fairy tale. The film features an ensemble cast that includes Emma Watson and Dan Stevens as the titular characters with Luke Evans, Kevin Kline, Josh Gad, Ewan McGregor, Stanley Tucci, Audra McDonald, Gugu Mbatha-Raw, Ian McKellen and Emma Thompson in supporting roles.";
		m4.info="Moonlight is a 2016 American coming-of-age drama film written and directed by Barry Jenkins, based on Tarell Alvin McCraney's unpublished semi-autobiographical play In Moonlight Black Boys Look Blue. It stars Trevante Rhodes, Andr茅 Holland, Janelle Mon谩e, Ashton Sanders, Jharrel Jerome, Naomie Harris, and Mahershala Ali. The film presents three stages in the life of the main character. It explores the difficulties he faces with his own sexuality and identity, including the physical and emotional abuse he receives as a result of it.";
		m5.info="La La Land is a 2016 American romantic musical film written and directed by Damien Chazelle, and starring Ryan Gosling and Emma Stone as a musician and an aspiring actress who meet and fall in love in Los Angeles. The film's title refers both to the city of Los Angeles and to the idiom for being out of touch with reality.";
		a.allMovies.add(m1);
		a.allMovies.add(m2);
		a.allMovies.add(m3);
		a.allMovies.add(m4);
		a.allMovies.add(m5);
		
				
		
		Arrange arran1=new Arrange();
		
		Show show1=new Show(m3, a.getTheScreen(0), 10, 0,0);
		arran1.showToday.add(show1);
		arran1.movieToday.add(m1);
		arran1.movieToday.add(m2);
		arran1.movieToday.add(m3);
		arran1.movieToday.add(m4);
		arran1.movieToday.add(m5);
		
		show1=new Show(m3, a.getTheScreen(0), 12, 30,0);
		arran1.showToday.add(show1);
		show1=new Show(m1, a.getTheScreen(0), 15, 30,0);
		arran1.showToday.add(show1);
		show1=new Show(m1, a.getTheScreen(0), 18, 30,0);
		arran1.showToday.add(show1);
		show1=new Show(m1, a.getTheScreen(0), 21, 0,0);
		arran1.showToday.add(show1);
		
		show1=new Show(m5, a.getTheScreen(1), 10, 30,0);
		arran1.showToday.add(show1);
		show1=new Show(m5, a.getTheScreen(1), 13, 0,0);
		arran1.showToday.add(show1);
		show1=new Show(m4, a.getTheScreen(1), 16, 0,0);
		arran1.showToday.add(show1);
		show1=new Show(m4, a.getTheScreen(1), 18, 0,0);
		arran1.showToday.add(show1);
		show1=new Show(m1, a.getTheScreen(1), 20, 0,0);
		arran1.showToday.add(show1);
		
		show1=new Show(m3, a.getTheScreen(2), 10, 30,0);
		arran1.showToday.add(show1);
		show1=new Show(m3, a.getTheScreen(2), 13, 0,0);
		arran1.showToday.add(show1);
		show1=new Show(m2, a.getTheScreen(2), 15, 30,0);
		arran1.showToday.add(show1);
		show1=new Show(m2, a.getTheScreen(2), 18, 0,0);
		arran1.showToday.add(show1);
		show1=new Show(m1, a.getTheScreen(2), 20, 30,0);
		arran1.showToday.add(show1);
		
		
		a.difDayArran.add(arran1);
		//add arrangement for today
		
		Arrange arran2=new Arrange();
		
		Show show2=new Show(m2, a.getTheScreen(0), 10, 10,1);
		Show show3=new Show(m1,a.getTheScreen(2),23,59,1);
		arran2.showToday.add(show2);
		arran2.movieToday.add(m2);
		arran2.showToday.add(show3);
		arran2.movieToday.add(m1);
		a.difDayArran.add( arran2);
		//add arrangement for today and next day
		
		
		
		Arrange arran3=new Arrange();
		Arrange arran4=new Arrange();
		Arrange arran5=new Arrange();
		Arrange arran6=new Arrange();
		Arrange arran7=new Arrange();
		a.difDayArran.add( arran3);
		a.difDayArran.add( arran4);
		a.difDayArran.add( arran5);
		a.difDayArran.add( arran6);
		a.difDayArran.add( arran7);

		//test the condition of next day
		
		CinemaIO.storeAllArrange();
		
		
		System.out.println(a.difDayArran.get(1).movieToday.isEmpty());
		System.out.println(a.getTheScreen(0).id);
	}

}
