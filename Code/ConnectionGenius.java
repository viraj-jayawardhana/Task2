     1	package base;
     2	import java.net.InetAddress;
     3	/**
     4	 * @author Kevan Buckley, maintained by __student
     5	 * @version 2.0, 2014
     6	 */
     7	
     8	public class ConnectionGenius {
     9	
    10	  InetAddress ipa;
    11	  
    12	  public ConnectionGenius(InetAddress ipa) {
    13	    this.ipa = ipa;
    14	  }
    15	
    16	  public void fireUpGame() {
    17	    downloadWebVersion();
    18	    connectToWebService();
    19	    awayWeGo();
    20	  }
    21	  
    22	  public void downloadWebVersion(){
    23	    System.out.println("Getting specialised web version.");
    24	    System.out.println("Wait a couple of moments");  
    25	  }
    26	  
    27	  public void connectToWebService() {
    28	    System.out.println("Connecting");
    29	  }
    30	  
    31	  public void awayWeGo(){
    32	    System.out.println("Ready to play");
    33	  }
    34	
    35	}
