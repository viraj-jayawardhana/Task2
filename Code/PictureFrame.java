     1	package base;
     2	import java.awt.*;
     3	
     4	import javax.swing.*;
     5	/**
     6	 * @author Kevan Buckley, maintained by __student
     7	 * @version 2.0, 2014
     8	 */
     9	
    10	public class PictureFrame {
    11	  public int[] reroll = null;
    12	  public Main master = null;
    13	
    14	  class DominoPanel extends JPanel {
    15	    private static final long serialVersionUID = 4190229282411119364L;
    16	
    17	    public void drawGrid(Graphics g) {
    18	      for (int are = 0; are < 7; are++) {
    19	        for (int see = 0; see < 8; see++) {
    20	          drawDigitGivenCentre(g, 30 + see * 20, 30 + are * 20, 20,
    21	              master.grid[are][see]);
    22	        }
    23	      }
    24	    }
    25	
    26	
    27	
    28	    public void drawHeadings(Graphics g) {
    29	      for (int are = 0; are < 7; are++) {
    30	        fillDigitGivenCentre(g, 10, 30 + are * 20, 20, are+1);
    31	      }
    32	
    33	      for (int see = 0; see < 8; see++) {
    34	        fillDigitGivenCentre(g, 30 + see * 20, 10, 20, see+1);
    35	      }
    36	    }
    37	
    38	    public void drawDomino(Graphics g, Domino d) {
    39	      if (d.placed) {
    40	        int y = Math.min(d.ly, d.hy);
    41	        int x = Math.min(d.lx, d.hx);
    42	        int w = Math.abs(d.lx - d.hx) + 1;
    43	        int h = Math.abs(d.ly - d.hy) + 1;
    44	        g.setColor(Color.WHITE);
    45	        g.fillRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);
    46	        g.setColor(Color.RED);
    47	        g.drawRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);
    48	        drawDigitGivenCentre(g, 30 + d.hx * 20, 30 + d.hy * 20, 20, d.high,
    49	            Color.BLUE);
    50	        drawDigitGivenCentre(g, 30 + d.lx * 20, 30 + d.ly * 20, 20, d.low,
    51	            Color.BLUE);
    52	      }
    53	    }
    54	
    55	    void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
    56	      int radius = diameter / 2;
    57	      g.setColor(Color.BLACK);
    58	      // g.drawOval(x - radius, y - radius, diameter, diameter);
    59	      FontMetrics fm = g.getFontMetrics();
    60	      // convert the string to an integer
    61	      String txt = Integer.toString(n);
    62	      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    63	    }
    64	
    65	    void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n,
    66	        Color c) {
    67	      int radius = diameter / 2;
    68	      g.setColor(c);
    69	      // g.drawOval(x - radius, y - radius, diameter, diameter);
    70	      FontMetrics fm = g.getFontMetrics();
    71	      String txt = Integer.toString(n);
    72	      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    73	    }
    74	
    75	    void fillDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
    76	      int radius = diameter / 2;
    77	      g.setColor(Color.GREEN);
    78	      g.fillOval(x - radius, y - radius, diameter, diameter);
    79	      g.setColor(Color.BLACK);
    80	      g.drawOval(x - radius, y - radius, diameter, diameter);
    81	      FontMetrics fm = g.getFontMetrics();
    82	      String txt = Integer.toString(n);
    83	      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    84	    }
    85	
    86	    protected void paintComponent(Graphics g) {
    87	      g.setColor(Color.YELLOW);
    88	      g.fillRect(0, 0, getWidth(), getHeight());
    89	
    90	      // numbaz(g);
    91	      //
    92	      // if (master!=null && master.orig != null) {
    93	      // drawRoll(g, master.orig);
    94	      // }
    95	      // if (reroll != null) {
    96	      // drawReroll(g, reroll);
    97	      // }
    98	      //
    99	      // drawGrid(g);
   100	      Location l = new Location(1,2);
   101	
   102	      if (master.mode == 1) {
   103	        l.drawGridLines(g);
   104	        drawHeadings(g);
   105	        drawGrid(g);
   106	        master.drawGuesses(g);
   107	      }
   108	      if (master.mode == 0) {
   109	        l.drawGridLines(g);
   110	        drawHeadings(g);
   111	        drawGrid(g);
   112	        master.drawDominoes(g);
   113	      }
   114	    }
   115	
   116	    public Dimension getPreferredSize() {
   117	      // the application window always prefers to be 202x182
   118	      return new Dimension(202, 182);
   119	    }
   120	  }
   121	
   122	  public DominoPanel dp;
   123	
   124	  public void PictureFrame(Main sf) {
   125	    master = sf;
   126	    if (dp == null) {
   127	      JFrame f = new JFrame("Abominodo");
   128	      dp = new DominoPanel();
   129	      f.setContentPane(dp);
   130	      f.pack();
   131	      f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   132	      f.setVisible(true);
   133	    }
   134	  }
   135	
   136	  public void reset() {
   137	    // TODO Auto-generated method stub
   138	
   139	  }
   140	
   141	}
