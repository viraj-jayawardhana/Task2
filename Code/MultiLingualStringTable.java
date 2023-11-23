     1	package base;
     2	/**
     3	 * @author Kevan Buckley, maintained by __student
     4	 * @version 2.0, 2014
     5	 */
     6	
     7	public class MultiLingualStringTable {
     8	  private enum LanguageSetting {English, Klingon}
     9	  private static LanguageSetting cl = LanguageSetting.English;
    10	  private static String [] em = {"Enter your name:", "Welcome", "Have a good time playing Abominodo"};
    11	  private static String [] km = {"'el lIj pong:", "nuqneH", "QaQ poH Abominodo"};
    12	  
    13	  public static String getMessage(int index){
    14	    if(cl == LanguageSetting.English ){
    15	      return em[index];
    16	    } else {
    17	      return km[index];     
    18	    }
    19	    
    20	  }
    21	}
