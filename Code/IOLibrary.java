     1	package base;
     2	import java.io.*;
     3	import java.net.*;
     4	/**
     5	 * @author Kevan Buckley, maintained by __student
     6	 * @version 2.0, 2014
     7	 */
     8	
     9	public final class IOLibrary {
    10	  public static String getString() {
    11	    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    12	    do {
    13	      try {
    14	        return r.readLine();
    15	      } catch (Exception e) {
    16	      }
    17	    } while (true);
    18	  }
    19	
    20	  public static InetAddress getIPAddress() {
    21	    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    22	    do {
    23	      try {
    24	        String[] chunks = r.readLine().split("\\.");
    25	        byte[] data = { Byte.parseByte(chunks[0]),Byte.parseByte(chunks[1]),Byte.parseByte(chunks[2]),Byte.parseByte(chunks[3])};
    26	        return Inet4Address.getByAddress(data);
    27	      } catch (Exception e) {
    28	      }
    29	    } while (true);
    30	  }
    31	
    32	}
