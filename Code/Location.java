     1	package base;
     2	import java.awt.Color;
     3	import java.awt.Graphics;
     4	import java.io.BufferedReader;
     5	import java.io.InputStreamReader;
     6	
     7	/**
     8	 * @author Kevan Buckley, maintained by __student
     9	 * @version 2.0, 2014
    10	 */
    11	
    12	public class Location extends SpacePlace {
    13	  public int c;
    14	  public int r;
    15	  public DIRECTION d;
    16	  public int tmp;
    17	  public enum DIRECTION {VERTICAL, HORIZONTAL};
    18	  
    19	  public Location(int r, int c) {
    20	    this.r = r;
    21	    this.c = c;
    22	  }
    23	
    24	  public Location(int r, int c, DIRECTION d) {    
    25	    this(r,c);
    26	    this.d=d;
    27	  }
    28	  
    29	  public String toString() {
    30	    if(d==null){
    31	      tmp = c + 1;
    32	      return "(" + (tmp) + "," + (r+1) + ")";
    33	    } else {
    34	      tmp = c + 1;
    35	      return "(" + (tmp) + "," + (r+1) + "," + d + ")";
    36	    }
    37	  }
    38	  
    39	  public void drawGridLines(Graphics g) {
    40	    g.setColor(Color.LIGHT_GRAY);
    41	    for (tmp = 0; tmp <= 7; tmp++) {
    42	      g.drawLine(20, 20 + tmp * 20, 180, 20 + tmp * 20);
    43	    }
    44	    for (int see = 0; see <= 8; see++) {
    45	      g.drawLine(20 + see * 20, 20, 20 + see * 20, 160);
    46	    }
    47	  }
    48	  
    49	  public static int getInt() {
    50	    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    51	    do {
    52	      try {
    53	        return Integer.parseInt(r.readLine());
    54	      } catch (Exception e) {
    55	      }
    56	    } while (true);
    57	  }
    58	}
