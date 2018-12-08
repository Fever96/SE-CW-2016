
import static org.junit.Assert.*;

import org.junit.Test;

import cinemaArrange.Arrange;
import cinemaArrange.CinemaDirector;
import cinemaArrange.Movie;
import cinemaArrange.Screen;
import cinemaArrange.Show;

public class TestNewShow {

	@Test
	public void test() {
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
		
		//已加入所有的影厅
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
		m1.info="1";
		m2.info="2";
		m3.info="3";
		m4.info="4";
		m5.info="5";
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
		//增加今天所有的安排
		
		Arrange arran2=new Arrange();
		
		Show show2=new Show(m2, a.getTheScreen(0), 10, 10,1);
		Show show3=new Show(m1,a.getTheScreen(2),23,59,1);
		arran2.showToday.add(show2);
		arran2.movieToday.add(m2);
		arran2.showToday.add(show3);
		arran2.movieToday.add(m1);
		a.difDayArran.add( arran2);
		//增加两天安排
		
		
		
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
	
		assertEquals(a.difDayArran.get(0).showToday.get(0).movie.name,"BEAUTY AND THE BEAST");
		assertEquals(a.difDayArran.get(0).showToday.get(2).movie.name,"KONG: SKULL ISLAND");
		assertEquals(a.difDayArran.get(0).showToday.get(0).movie.name,"BEAUTY AND THE BEAST");
		assertEquals(a.difDayArran.get(0).showToday.get(1).movie.name,"BEAUTY AND THE BEAST");
		assertEquals(a.difDayArran.get(0).showToday.get(2).movie.name,"KONG: SKULL ISLAND");
		assertEquals(a.difDayArran.get(0).showToday.get(3).movie.name,"KONG: SKULL ISLAND");
		assertEquals(a.difDayArran.get(0).showToday.get(4).movie.name,"KONG: SKULL ISLAND");
		assertEquals(a.difDayArran.get(0).showToday.get(5).movie.name,"LA LA LAND");
		assertEquals(a.difDayArran.get(0).showToday.get(6).movie.name,"LA LA LAND");
		assertEquals(a.difDayArran.get(0).showToday.get(7).movie.name,"MOONLIGHT");
		assertEquals(a.difDayArran.get(0).showToday.get(8).movie.name,"MOONLIGHT");
		assertEquals(a.difDayArran.get(0).showToday.get(9).movie.name,"KONG: SKULL ISLAND");
		assertEquals(a.difDayArran.get(0).showToday.get(10).movie.name,"BEAUTY AND THE BEAST");
		assertEquals(a.difDayArran.get(0).showToday.get(11).movie.name,"BEAUTY AND THE BEAST");
		assertEquals(a.difDayArran.get(0).showToday.get(12).movie.name,"LOGAN");
		assertEquals(a.difDayArran.get(0).showToday.get(13).movie.name,"LOGAN");
		assertEquals(a.difDayArran.get(0).showToday.get(14).movie.name,"KONG: SKULL ISLAND");

		
	}

}
