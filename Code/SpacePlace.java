     1	package base;
     2	/**
     3	 * @author Kevan Buckley, maintained by __student
     4	 * @version 2.0, 2014
     5	 */
     6	
     7	public class SpacePlace {
     8	  private int xOrg;
     9	  private int yOrg;
    10	  private double theta;
    11	  private double phi;
    12	  
    13	  public SpacePlace() {
    14	    xOrg = 0;
    15	    yOrg = 0;
    16	  }
    17	
    18	  public SpacePlace(double theta, double phi) {
    19	    super();
    20	    this.theta = theta;
    21	    this.phi = phi;
    22	  }
    23	
    24	  public int getxOrg() {
    25	    return xOrg;
    26	  }
    27	
    28	  public void setxOrg(int xOrg) {
    29	    this.xOrg = xOrg;
    30	  }
    31	
    32	  public int getyOrg() {
    33	    return yOrg;
    34	  }
    35	
    36	  public void setyOrg(int yOrg) {
    37	    this.yOrg = yOrg;
    38	  }
    39	
    40	  public double getTheta() {
    41	    return theta;
    42	  }
    43	
    44	  public void setTheta(double theta) {
    45	    this.theta = theta;
    46	  }
    47	
    48	  public double getPhi() {
    49	    return phi;
    50	  }
    51	
    52	  public void setPhi(double phi) {
    53	    this.phi = phi;
    54	  }
    55	  
    56	}
