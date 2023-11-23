     1	package base;
     2	/**
     3	 * @author Kevan Buckley, maintained by __student
     4	 * @version 2.0, 2014
     5	 */
     6	
     7	public class Domino implements Comparable<Domino> {
     8	  public int high;
     9	  public int low;
    10	  public int hx;
    11	  public int hy;
    12	  public int lx;
    13	  public int ly;
    14	  public boolean placed = false;
    15	
    16	  public Domino(int high, int low) {
    17	    super();
    18	    this.high = high;
    19	    this.low = low;
    20	  }
    21	  
    22	  public void place(int hx, int hy, int lx, int ly) {
    23	    this.hx = hx;
    24	    this.hy = hy;
    25	    this.lx = lx;
    26	    this.ly = ly;
    27	    placed = true;
    28	  }
    29	
    30	  public String toString() {
    31	    StringBuffer result = new StringBuffer();
    32	    result.append("[");
    33	    result.append(Integer.toString(high));
    34	    result.append(Integer.toString(low));
    35	    result.append("]");
    36	    if(!placed){
    37	      result.append("unplaced");
    38	    } else {
    39	      result.append("(");
    40	      result.append(Integer.toString(hx+1));
    41	      result.append(",");
    42	      result.append(Integer.toString(hy+1));
    43	      result.append(")");
    44	      result.append("(");
    45	      result.append(Integer.toString(lx+1));
    46	      result.append(",");
    47	      result.append(Integer.toString(ly+1));
    48	      result.append(")");
    49	    }
    50	    return result.toString();
    51	  }
    52	
    53	  /** turn the domino around 180 degrees clockwise*/
    54	  
    55	  public void invert() {
    56	    int tx = hx;
    57	    hx = lx;
    58	    lx = tx;
    59	    
    60	    int ty = hy;
    61	    hy = ly;
    62	    ly = ty;    
    63	  }
    64	
    65	  public boolean ishl() {    
    66	    return hy==ly;
    67	  }
    68	
    69	
    70	  public int compareTo(Domino arg0) {
    71	    if(this.high < arg0.high){
    72	      return 1;
    73	    }
    74	    return this.low - arg0.low;
    75	  }
    76	  
    77	  
    78	  
    79	}
