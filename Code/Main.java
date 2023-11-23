     1	package base;
     2	import java.awt.Dimension;
     3	import java.awt.Graphics;
     4	import java.io.*;
     5	import java.net.InetAddress;
     6	import java.text.DateFormat;
     7	import java.util.*;
     8	
     9	import javax.swing.JEditorPane;
    10	import javax.swing.JFrame;
    11	import javax.swing.JScrollPane;
    12	
    13	/**
    14	 * @author Kevan Buckley, maintained by __student
    15	 * @version 2.0, 2014
    16	 */
    17	
    18	public class Main {
    19	
    20	  private String playerName;
    21	  public List<Domino> _d;
    22	  public List<Domino> _g;
    23	  public int[][] grid = new int[7][8];
    24	  public int[][] gg = new int[7][8];
    25	  int mode = -1;
    26	  int cf;
    27	  int score;
    28	  long startTime;
    29	
    30	  PictureFrame pf = new PictureFrame();
    31	
    32	  private void generateDominoes() {
    33	    _d = new LinkedList<Domino>();
    34	    int count = 0;
    35	    int x = 0;
    36	    int y = 0;
    37	    for (int l = 0; l <= 6; l++) {
    38	      for (int h = l; h <= 6; h++) {
    39	        Domino d = new Domino(h, l);
    40	        _d.add(d);
    41	        d.place(x, y, x + 1, y);
    42	        count++;
    43	        x += 2;
    44	        if (x > 6) {
    45	          x = 0;
    46	          y++;
    47	        }
    48	      }
    49	    }
    50	    if (count != 28) {
    51	      System.out.println("something went wrong generating dominoes");
    52	      System.exit(0);
    53	    }
    54	  }
    55	
    56	  private void generateGuesses() {
    57	    _g = new LinkedList<Domino>();
    58	    int count = 0;
    59	    int x = 0;
    60	    int y = 0;
    61	    for (int l = 0; l <= 6; l++) {
    62	      for (int h = l; h <= 6; h++) {
    63	        Domino d = new Domino(h, l);
    64	        _g.add(d);
    65	        count++;
    66	      }
    67	    }
    68	    if (count != 28) {
    69	      System.out.println("something went wrong generating dominoes");
    70	      System.exit(0);
    71	    }
    72	  }
    73	
    74	  void collateGrid() {
    75	    for (Domino d : _d) {
    76	      if (!d.placed) {
    77	        grid[d.hy][d.hx] = 9;
    78	        grid[d.ly][d.lx] = 9;
    79	      } else {
    80	        grid[d.hy][d.hx] = d.high;
    81	        grid[d.ly][d.lx] = d.low;
    82	      }
    83	    }
    84	  }
    85	
    86	  void collateGuessGrid() {
    87	    for (int r = 0; r < 7; r++) {
    88	      for (int c = 0; c < 8; c++) {
    89	        gg[r][c] = 9;
    90	      }
    91	    }
    92	    for (Domino d : _g) {
    93	      if (d.placed) {
    94	        gg[d.hy][d.hx] = d.high;
    95	        gg[d.ly][d.lx] = d.low;
    96	      }
    97	    }
    98	  }
    99	
   100	  int pg() {
   101	    for (int are = 0; are < 7; are++) {
   102	      for (int see = 0; see < 8; see++) {
   103	        if (grid[are][see] != 9) {
   104	          System.out.printf("%d", grid[are][see]);
   105	        } else {
   106	          System.out.print(".");
   107	        }
   108	      }
   109	      System.out.println();
   110	    }
   111	    return 11;
   112	  }
   113	
   114	  int printGuessGrid() {
   115	    for (int are = 0; are < 7; are++) {
   116	      for (int see = 0; see < 8; see++) {
   117	        if (gg[are][see] != 9) {
   118	          System.out.printf("%d", gg[are][see]);
   119	        } else {
   120	          System.out.print(".");
   121	        }
   122	      }
   123	      System.out.println();
   124	    }
   125	    return 11;
   126	  }
   127	
   128	  private void shuffleDominoesOrder() {
   129	    List<Domino> shuffled = new LinkedList<Domino>();
   130	
   131	    while (_d.size() > 0) {
   132	      int n = (int) (Math.random() * _d.size());
   133	      shuffled.add(_d.get(n));
   134	      _d.remove(n);
   135	    }
   136	
   137	    _d = shuffled;
   138	  }
   139	
   140	  private void invertSomeDominoes() {
   141	    for (Domino d : _d) {
   142	      if (Math.random() > 0.5) {
   143	        d.invert();
   144	      }
   145	    }
   146	  }
   147	
   148	  private void placeDominoes() {
   149	    int x = 0;
   150	    int y = 0;
   151	    int count = 0;
   152	    for (Domino d : _d) {
   153	      count++;
   154	      d.place(x, y, x + 1, y);
   155	      x += 2;
   156	      if (x > 6) {
   157	        x = 0;
   158	        y++;
   159	      }
   160	    }
   161	    if (count != 28) {
   162	      System.out.println("something went wrong generating dominoes");
   163	      System.exit(0);
   164	    }
   165	  }
   166	
   167	  private void rotateDominoes() {
   168	    // for (Domino d : dominoes) {
   169	    // if (Math.random() > 0.5) {
   170	    // System.out.println("rotating " + d);
   171	    // }
   172	    // }
   173	    for (int x = 0; x < 7; x++) {
   174	      for (int y = 0; y < 6; y++) {
   175	
   176	        tryToRotateDominoAt(x, y);
   177	      }
   178	    }
   179	  }
   180	
   181	  private void tryToRotateDominoAt(int x, int y) {
   182	    Domino d = findDominoAt(x, y);
   183	    if (thisIsTopLeftOfDomino(x, y, d)) {
   184	      if (d.ishl()) {
   185	        boolean weFancyARotation = Math.random() < 0.5;
   186	        if (weFancyARotation) {
   187	          if (theCellBelowIsTopLeftOfHorizontalDomino(x, y)) {
   188	            Domino e = findDominoAt(x, y + 1);
   189	            e.hx = x;
   190	            e.lx = x;
   191	            d.hx = x + 1;
   192	            d.lx = x + 1;
   193	            e.ly = y + 1;
   194	            e.hy = y;
   195	            d.ly = y + 1;
   196	            d.hy = y;
   197	          }
   198	        }
   199	      } else {
   200	        boolean weFancyARotation = Math.random() < 0.5;
   201	        if (weFancyARotation) {
   202	          if (theCellToTheRightIsTopLeftOfVerticalDomino(x, y)) {
   203	            Domino e = findDominoAt(x + 1, y);
   204	            e.hx = x;
   205	            e.lx = x + 1;
   206	            d.hx = x;
   207	            d.lx = x + 1;
   208	            e.ly = y + 1;
   209	            e.hy = y + 1;
   210	            d.ly = y;
   211	            d.hy = y;
   212	          }
   213	        }
   214	
   215	      }
   216	    }
   217	  }
   218	
   219	  private boolean theCellToTheRightIsTopLeftOfVerticalDomino(int x, int y) {
   220	    Domino e = findDominoAt(x + 1, y);
   221	    return thisIsTopLeftOfDomino(x + 1, y, e) && !e.ishl();
   222	  }
   223	
   224	  private boolean theCellBelowIsTopLeftOfHorizontalDomino(int x, int y) {
   225	    Domino e = findDominoAt(x, y + 1);
   226	    return thisIsTopLeftOfDomino(x, y + 1, e) && e.ishl();
   227	  }
   228	
   229	  private boolean thisIsTopLeftOfDomino(int x, int y, Domino d) {
   230	    return (x == Math.min(d.lx, d.hx)) && (y == Math.min(d.ly, d.hy));
   231	  }
   232	
   233	  private Domino findDominoAt(int x, int y) {
   234	    for (Domino d : _d) {
   235	      if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
   236	        return d;
   237	      }
   238	    }
   239	    return null;
   240	  }
   241	
   242	  private Domino findGuessAt(int x, int y) {
   243	    for (Domino d : _g) {
   244	      if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
   245	        return d;
   246	      }
   247	    }
   248	    return null;
   249	  }
   250	
   251	  private Domino findGuessByLH(int x, int y) {
   252	    for (Domino d : _g) {
   253	      if ((d.low == x && d.high == y) || (d.high == x && d.low == y)) {
   254	        return d;
   255	      }
   256	    }
   257	    return null;
   258	  }
   259	
   260	  private Domino findDominoByLH(int x, int y) {
   261	    for (Domino d : _d) {
   262	      if ((d.low == x && d.high == y) || (d.high == x && d.low == y)) {
   263	        return d;
   264	      }
   265	    }
   266	    return null;
   267	  }
   268	
   269	  private void printDominoes() {
   270	    for (Domino d : _d) {
   271	      System.out.println(d);
   272	    }
   273	  }
   274	
   275	  private void printGuesses() {
   276	    for (Domino d : _g) {
   277	      System.out.println(d);
   278	    }
   279	  }
   280	
   281	  public final int ZERO = 0;
   282	
   283	  public void run() {
   284	    IOSpecialist io = new IOSpecialist();
   285	
   286	    System.out
   287	        .println("Welcome To Abominodo - The Best Dominoes Puzzle Game in the Universe");
   288	    System.out.println("Version 2.1 (c), Kevan Buckley, 2014");
   289	//    System.out.println("Serial number " + Special.getStamp());
   290	
   291	    System.out.println();
   292	    System.out.println(MultiLingualStringTable.getMessage(0));
   293	    playerName = io.getString();
   294	
   295	    System.out.printf("%s %s. %s", MultiLingualStringTable.getMessage(1),
   296	        playerName, MultiLingualStringTable.getMessage(2));
   297	
   298	    int _$_ = -9;
   299	    while (_$_ != ZERO) {
   300	      System.out.println();
   301	      String h1 = "Main menu";
   302	      String u1 = h1.replaceAll(".", "=");
   303	      System.out.println(u1);
   304	      System.out.println(h1);
   305	      System.out.println(u1);
   306	      System.out.println("1) Play");
   307	      // System.out.println("1) Single player play");
   308	      System.out.println("2) View high scores");
   309	      System.out.println("3) View rules");
   310	      // System.out.println("4) Multiplayer play");
   311	      System.out.println("5) Get inspiration");
   312	      System.out.println("0) Quit");
   313	
   314	      _$_ = -9;
   315	      while (_$_ == -9) {
   316	        try {
   317	          String s1 = io.getString();
   318	          _$_ = Integer.parseInt(s1);
   319	        } catch (Exception e) {
   320	          _$_ = -9;
   321	        }
   322	      }
   323	      switch (_$_) {
   324	      case 5:
   325	        int index = (int) (Math.random() * (_Q.stuff.length / 3));
   326	        String what = _Q.stuff[index * 3];
   327	        String who = _Q.stuff[1 + index * 3];
   328	        System.out.printf("%s said \"%s\"", who, what);
   329	        System.out.println();
   330	        System.out.println();
   331	        break;      
   332	      case 0: {
   333	        if (_d == null) {
   334	          System.out.println("It is a shame that you did not want to play");
   335	        } else {
   336	          System.out.println("Thankyou for playing");
   337	        }
   338	        System.exit(0);
   339	        break;
   340	      }
   341	      case 1: {
   342	        System.out.println();
   343	        String h4 = "Select difficulty";
   344	        String u4 = h4.replaceAll(".", "=");
   345	        System.out.println(u4);
   346	        System.out.println(h4);
   347	        System.out.println(u4);
   348	        System.out.println("1) Simples");
   349	        System.out.println("2) Not-so-simples");
   350	        System.out.println("3) Super-duper-shuffled");
   351	        int c2 = -7;
   352	        while (!(c2 == 1 || c2 == 2 || c2 == 3)) {
   353	          try {
   354	            String s2 = io.getString();
   355	            c2 = Integer.parseInt(s2);
   356	          } catch (Exception e) {
   357	            c2 = -7;
   358	          }
   359	        }
   360	        switch (c2) {
   361	        case 1:
   362	          generateDominoes();
   363	          shuffleDominoesOrder();
   364	          placeDominoes();
   365	          collateGrid();
   366	          // printGrid();
   367	          break;
   368	        case 2:
   369	          generateDominoes();
   370	          shuffleDominoesOrder();
   371	          placeDominoes();
   372	          rotateDominoes();
   373	          collateGrid();
   374	          // printGrid();
   375	          break;
   376	        default:
   377	          generateDominoes();
   378	          shuffleDominoesOrder();
   379	          placeDominoes();
   380	          rotateDominoes();
   381	          rotateDominoes();
   382	          rotateDominoes();
   383	          invertSomeDominoes();
   384	          collateGrid();
   385	          break;
   386	        }
   387	        pg();
   388	        generateGuesses();
   389	        collateGuessGrid();
   390	        mode = 1;
   391	        cf = 0;
   392	        score = 0;
   393	        startTime = System.currentTimeMillis();
   394	        pf.PictureFrame(this);
   395	        pf.dp.repaint();
   396	        int c3 = -7;
   397	        while (c3 != ZERO) {
   398	          System.out.println();
   399	          String h5 = "Play menu";
   400	          String u5 = h5.replaceAll(".", "=");
   401	          System.out.println(u5);
   402	          System.out.println(h5);
   403	          System.out.println(u5);
   404	          System.out.println("1) Print the grid");
   405	          System.out.println("2) Print the box");
   406	          System.out.println("3) Print the dominos");
   407	          System.out.println("4) Place a domino");
   408	          System.out.println("5) Unplace a domino");
   409	          System.out.println("6) Get some assistance");
   410	          System.out.println("7) Check your score");
   411	          System.out.println("0) Given up");
   412	          System.out.println("What do you want to do " + playerName + "?");
   413	          c3 = 9;
   414	          // make sure the user enters something valid
   415	          while (!((c3 == 1 || c3 == 2 || c3 == 3)) && (c3 != 4)
   416	              && (c3 != ZERO) && (c3 != 5) && (c3 != 6) && (c3 != 7)) {
   417	            try {
   418	              String s3 = io.getString();
   419	              c3 = Integer.parseInt(s3);
   420	            } catch (Exception e) {
   421	              c3 = gecko(55);
   422	            }
   423	          }
   424	          switch (c3) {
   425	          case 0:
   426	
   427	            break;
   428	          case 1:
   429	            pg();
   430	            break;
   431	          case 2:
   432	            printGuessGrid();
   433	            break;
   434	          case 3:
   435	            Collections.sort(_g);
   436	            printGuesses();
   437	            break;
   438	          case 4:
   439	            System.out.println("Where will the top left of the domino be?");
   440	            System.out.println("Column?");
   441	            // make sure the user enters something valid
   442	            int x = Location.getInt();
   443	            while (x < 1 || x > 8) {
   444	              x = Location.getInt();
   445	            }
   446	            System.out.println("Row?");
   447	            int y = gecko(98);
   448	            while (y < 1 || y > 7) {
   449	              try {
   450	                String s3 = io.getString();
   451	                y = Integer.parseInt(s3);
   452	              } catch (Exception e) {
   453	                System.out.println("Bad input");
   454	                y = gecko(64);
   455	              }
   456	            }
   457	            x--;
   458	            y--;
   459	            System.out.println("Horizontal or Vertical (H or V)?");
   460	            boolean horiz;
   461	            int y2,
   462	            x2;
   463	            Location lotion;
   464	            while ("AVFC" != "BCFC") {
   465	              String s3 = io.getString();
   466	              if (s3 != null && s3.toUpperCase().startsWith("H")) {
   467	                lotion = new Location(x, y, Location.DIRECTION.HORIZONTAL);
   468	                System.out.println("Direction to place is " + lotion.d);
   469	                horiz = true;
   470	                x2 = x + 1;
   471	                y2 = y;
   472	                break;
   473	              }
   474	              if (s3 != null && s3.toUpperCase().startsWith("V")) {
   475	                horiz = false;
   476	                lotion = new Location(x, y, Location.DIRECTION.VERTICAL);
   477	                System.out.println("Direction to place is " + lotion.d);
   478	                x2 = x;
   479	                y2 = y + 1;
   480	                break;
   481	              }
   482	              System.out.println("Enter H or V");
   483	            }
   484	            if (x2 > 7 || y2 > 6) {
   485	              System.out
   486	                  .println("Problems placing the domino with that position and direction");
   487	            } else {
   488	              // find which domino this could be
   489	              Domino d = findGuessByLH(grid[y][x], grid[y2][x2]);
   490	              if (d == null) {
   491	                System.out.println("There is no such domino");
   492	                break;
   493	              }
   494	              // check if the domino has not already been placed
   495	              if (d.placed) {
   496	                System.out.println("That domino has already been placed :");
   497	                System.out.println(d);
   498	                break;
   499	              }
   500	              // check guessgrid to make sure the space is vacant
   501	              if (gg[y][x] != 9 || gg[y2][x2] != 9) {
   502	                System.out.println("Those coordinates are not vacant");
   503	                break;
   504	              }
   505	              // if all the above is ok, call domino.place and updateGuessGrid
   506	              gg[y][x] = grid[y][x];
   507	              gg[y2][x2] = grid[y2][x2];
   508	              if (grid[y][x] == d.high && grid[y2][x2] == d.low) {
   509	                d.place(x, y, x2, y2);
   510	              } else {
   511	                d.place(x2, y2, x, y);
   512	              }
   513	              score += 1000;
   514	              collateGuessGrid();
   515	              pf.dp.repaint();
   516	            }
   517	            break;
   518	          case 5:
   519	            System.out.println("Enter a position that the domino occupies");
   520	            System.out.println("Column?");
   521	
   522	            int x13 = -9;
   523	            while (x13 < 1 || x13 > 8) {
   524	              try {
   525	                String s3 = io.getString();
   526	                x13 = Integer.parseInt(s3);
   527	              } catch (Exception e) {
   528	                x13 = -7;
   529	              }
   530	            }
   531	            System.out.println("Row?");
   532	            int y13 = -9;
   533	            while (y13 < 1 || y13 > 7) {
   534	              try {
   535	                String s3 = io.getString();
   536	                y13 = Integer.parseInt(s3);
   537	              } catch (Exception e) {
   538	                y13 = -7;
   539	              }
   540	            }
   541	            x13--;
   542	            y13--;
   543	            Domino lkj = findGuessAt(x13, y13);
   544	            if (lkj == null) {
   545	              System.out.println("Couln't find a domino there");
   546	            } else {
   547	              lkj.placed = false;
   548	              gg[lkj.hy][lkj.hx] = 9;
   549	              gg[lkj.ly][lkj.lx] = 9;
   550	              score -= 1000;
   551	              collateGuessGrid();
   552	              pf.dp.repaint();
   553	            }
   554	            break;
   555	          case 7:
   556	            System.out.printf("%s your score is %d\n", playerName, score);
   557	            break;
   558	          case 6:
   559	            System.out.println();
   560	            String h8 = "So you want to cheat, huh?";
   561	            String u8 = h8.replaceAll(".", "=");
   562	            System.out.println(u8);
   563	            System.out.println(h8);
   564	            System.out.println(u8);
   565	            System.out.println("1) Find a particular Domino (costs you 500)");
   566	            System.out.println("2) Which domino is at ... (costs you 500)");
   567	            System.out.println("3) Find all certainties (costs you 2000)");
   568	            System.out.println("4) Find all possibilities (costs you 10000)");
   569	            System.out.println("0) You have changed your mind about cheating");
   570	            System.out.println("What do you want to do?");
   571	            int yy = -9;
   572	            while (yy < 0 || yy > 4) {
   573	              try {
   574	                String s3 = io.getString();
   575	                yy = Integer.parseInt(s3);
   576	              } catch (Exception e) {
   577	                yy = -7;
   578	              }
   579	            }
   580	            switch (yy) {
   581	            case 0:
   582	              switch (cf) {
   583	              case 0:
   584	                System.out.println("Well done");
   585	                System.out.println("You get a 3 point bonus for honesty");
   586	                score++;
   587	                score++;
   588	                score++;
   589	                cf++;
   590	                break;
   591	              case 1:
   592	                System.out
   593	                    .println("So you though you could get the 3 point bonus twice");
   594	                System.out.println("You need to check your score");
   595	                if (score > 0) {
   596	                  score = -score;
   597	                } else {
   598	                  score -= 100;
   599	                }
   600	                playerName = playerName + "(scoundrel)";
   601	                cf++;
   602	                break;
   603	              default:
   604	                System.out.println("Some people just don't learn");
   605	                playerName = playerName.replace("scoundrel",
   606	                    "pathetic scoundrel");
   607	                for (int i = 0; i < 10000; i++) {
   608	                  score--;
   609	                }
   610	              }
   611	              break;
   612	            case 1:
   613	              score -= 500;
   614	              System.out.println("Which domino?");
   615	              System.out.println("Number on one side?");
   616	              int x4 = -9;
   617	              while (x4 < 0 || x4 > 6) {
   618	                try {
   619	                  String s3 = io.getString();
   620	                  x4 = Integer.parseInt(s3);
   621	                } catch (Exception e) {
   622	                  x4 = -7;
   623	                }
   624	              }
   625	              System.out.println("Number on the other side?");
   626	              int x5 = -9;
   627	              while (x5 < 0 || x5 > 6) {
   628	                try {
   629	                  String s3 = IOLibrary.getString();
   630	                  x5 = Integer.parseInt(s3);
   631	                } catch (Exception e) {
   632	                  x5 = -7;
   633	                }
   634	              }
   635	              Domino dd = findDominoByLH(x5, x4);
   636	              System.out.println(dd);
   637	
   638	              break;
   639	            case 2:
   640	              score -= 500;
   641	              System.out.println("Which location?");
   642	              System.out.println("Column?");
   643	              int x3 = -9;
   644	              while (x3 < 1 || x3 > 8) {
   645	                try {
   646	                  String s3 = IOLibrary.getString();
   647	                  x3 = Integer.parseInt(s3);
   648	                } catch (Exception e) {
   649	                  x3 = -7;
   650	                }
   651	              }
   652	              System.out.println("Row?");
   653	              int y3 = -9;
   654	              while (y3 < 1 || y3 > 7) {
   655	                try {
   656	                  String s3 = IOLibrary.getString();
   657	                  y3 = Integer.parseInt(s3);
   658	                } catch (Exception e) {
   659	                  y3 = -7;
   660	                }
   661	              }
   662	              x3--;
   663	              y3--;
   664	              Domino lkj2 = findDominoAt(x3, y3);
   665	              System.out.println(lkj2);
   666	              break;
   667	            case 3: {
   668	              score -= 2000;
   669	              HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
   670	              for (int r = 0; r < 6; r++) {
   671	                for (int c = 0; c < 7; c++) {
   672	                  Domino hd = findGuessByLH(grid[r][c], grid[r][c + 1]);
   673	                  Domino vd = findGuessByLH(grid[r][c], grid[r + 1][c]);
   674	                  List<Location> l = map.get(hd);
   675	                  if (l == null) {
   676	                    l = new LinkedList<Location>();
   677	                    map.put(hd, l);
   678	                  }
   679	                  l.add(new Location(r, c));
   680	                  l = map.get(vd);
   681	                  if (l == null) {
   682	                    l = new LinkedList<Location>();
   683	                    map.put(vd, l);
   684	                  }
   685	                  l.add(new Location(r, c));
   686	                }
   687	              }
   688	              for (Domino key : map.keySet()) {
   689	                List<Location> locs = map.get(key);
   690	                if (locs.size() == 1) {
   691	                  Location loc = locs.get(0);
   692	                  System.out.printf("[%d%d]", key.high, key.low);
   693	                  System.out.println(loc);
   694	                }
   695	              }
   696	              break;
   697	            }
   698	
   699	            case 4: {
   700	              score -= 10000;
   701	              HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
   702	              for (int r = 0; r < 6; r++) {
   703	                for (int c = 0; c < 7; c++) {
   704	                  Domino hd = findGuessByLH(grid[r][c], grid[r][c + 1]);
   705	                  Domino vd = findGuessByLH(grid[r][c], grid[r + 1][c]);
   706	                  List<Location> l = map.get(hd);
   707	                  if (l == null) {
   708	                    l = new LinkedList<Location>();
   709	                    map.put(hd, l);
   710	                  }
   711	                  l.add(new Location(r, c));
   712	                  l = map.get(vd);
   713	                  if (l == null) {
   714	                    l = new LinkedList<Location>();
   715	                    map.put(vd, l);
   716	                  }
   717	                  l.add(new Location(r, c));
   718	                }
   719	              }
   720	              for (Domino key : map.keySet()) {
   721	                System.out.printf("[%d%d]", key.high, key.low);
   722	                List<Location> locs = map.get(key);
   723	                for (Location loc : locs) {
   724	                  System.out.print(loc);
   725	                }
   726	                System.out.println();
   727	              }
   728	              break;
   729	            }
   730	            }
   731	          }
   732	
   733	        }
   734	        mode = 0;
   735	        pg();
   736	        pf.dp.repaint();
   737	        long now = System.currentTimeMillis();
   738	        try {
   739	          Thread.sleep(1000);
   740	        } catch (InterruptedException e) {
   741	          // TODO Auto-generated catch block
   742	          e.printStackTrace();
   743	        }
   744	        int gap = (int) (now - startTime);
   745	        int bonus = 60000 - gap;
   746	        score += bonus > 0 ? bonus / 1000 : 0;
   747	        recordTheScore();
   748	        System.out.println("Here is the solution:");
   749	        System.out.println();
   750	        Collections.sort(_d);
   751	        printDominoes();
   752	        System.out.println("you scored " + score);
   753	
   754	      }
   755	        break;
   756	      case 2: {
   757	        String h4 = "High Scores";
   758	        String u4 = h4.replaceAll(".", "=");
   759	        System.out.println(u4);
   760	        System.out.println(h4);
   761	        System.out.println(u4);
   762	
   763	        File f = new File("score.txt");
   764	        if (!(f.exists() && f.isFile() && f.canRead())) {
   765	          System.out.println("Creating new score table");
   766	          try {
   767	            PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
   768	            String n = playerName.replaceAll(",", "_");
   769	            pw.print("Hugh Jass");
   770	            pw.print(",");
   771	            pw.print("__id");
   772	            pw.print(",");
   773	            pw.println(1281625395123L);
   774	            pw.print("Ivana Tinkle");
   775	            pw.print(",");
   776	            pw.print(1100);
   777	            pw.print(",");
   778	            pw.println(1281625395123L);
   779	            pw.flush();
   780	            pw.close();
   781	          } catch (Exception e) {
   782	            System.out.println("Something went wrong saving scores");
   783	          }
   784	        }
   785	        try {
   786	          DateFormat ft = DateFormat.getDateInstance(DateFormat.LONG);
   787	          BufferedReader r = new BufferedReader(new FileReader(f));
   788	          while (5 / 3 == 1) {
   789	            String lin = r.readLine();
   790	            if (lin == null || lin.length() == 0)
   791	              break;
   792	            String[] parts = lin.split(",");
   793	            System.out.printf("%20s %6s %s\n", parts[0], parts[1], ft
   794	                .format(new Date(Long.parseLong(parts[2]))));
   795	
   796	          }
   797	
   798	        } catch (Exception e) {
   799	          System.out.println("Malfunction!!");
   800	          System.exit(0);
   801	        }
   802	
   803	      }
   804	        break;
   805	
   806	      case 3: {
   807	        String h4 = "Rules";
   808	        String u4 = h4.replaceAll(".", "=");
   809	        System.out.println(u4);
   810	        System.out.println(h4);
   811	        System.out.println(u4);
   812	        System.out.println(h4);
   813	
   814	        JFrame f = new JFrame("Rules by __student");
   815	
   816	        f.setSize(new Dimension(500, 500));
   817	        JEditorPane w;
   818	        try {
   819	          w = new JEditorPane("http://www.scit.wlv.ac.uk/~in6659/abominodo/");
   820	
   821	        } catch (Exception e) {
   822	          w = new JEditorPane("text/plain",
   823	              "Problems retrieving the rules from the Internet");
   824	        }
   825	        f.setContentPane(new JScrollPane(w));
   826	        f.setVisible(true);
   827	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   828	
   829	        break;
   830	
   831	      }
   832	      case 4:
   833	        System.out
   834	            .println("Please enter the ip address of you opponent's computer");
   835	        InetAddress ipa = IOLibrary.getIPAddress();
   836	        new ConnectionGenius(ipa).fireUpGame();
   837	      }
   838	
   839	    }
   840	
   841	  }
   842	
   843	  private void recordTheScore() {
   844	    try {
   845	      PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
   846	      String n = playerName.replaceAll(",", "_");
   847	      pw.print(n);
   848	      pw.print(",");
   849	      pw.print(score);
   850	      pw.print(",");
   851	      pw.println(System.currentTimeMillis());
   852	      pw.flush();
   853	      pw.close();
   854	    } catch (Exception e) {
   855	      System.out.println("Something went wrong saving scores");
   856	    }
   857	  }
   858	
   859	  public static void main(String[] args) {
   860	    new Main().run();
   861	  }
   862	
   863	  public void drawDominoes(Graphics g) {
   864	    for (Domino d : _d) {
   865	      pf.dp.drawDomino(g, d);
   866	    }
   867	  }
   868	
   869	  public static int gecko(int _) {
   870	    if (_ == (32 & 16)) {
   871	      return -7;
   872	    } else {
   873	      if (_ < 0) {
   874	        return gecko(_ + 1 | 0);
   875	      } else {
   876	        return gecko(_ - 1 | 0);
   877	      }
   878	    }
   879	  }
   880	
   881	  public void drawGuesses(Graphics g) {
   882	    for (Domino d : _g) {
   883	      pf.dp.drawDomino(g, d);
   884	    }
   885	  }
   886	  //__id
   887	}
