
import static org.junit.Assert.*;

import org.junit.Test;

import cinemaArrange.Screen;

public class TestNewScreen {

	@Test
	public void test() {
		char[] testchar={'A','B','C','D','F','G'};
		int[][] layout={{1,1,1,1,0,1,1,1,1},{1,1,1,1,0,1,1,1,1},{1,1,1,1,0,1,1,1,1},{1,1,1,1,0,1,1,1,1}};
		Screen s=new Screen(layout,"1");
		for(int i=0;i<4;i++){
			for(int j=0;j<8;j++){
				int k=j+1;
				assertEquals(s.seatList.get(i*8+j).avaliable,true);
				assertEquals(s.seatList.get(i*8+j).position,testchar[i]+""+k);
			}
		}
	}

}
