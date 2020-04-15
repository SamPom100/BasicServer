package Games;

import java.util.*;
import java.io.*; 
public class BingoGame
{
    public static void demo() throws Exception
    {        
        Tiles t = new Tiles();
        start.out.writeUTF("\nEnter Player Name:  "); 
        String name = start.input.readUTF(); Player pl = new Player(name);
        start.out.writeUTF("Enter Number of Boards:  "); 
        int numCards = Integer.parseInt(start.input.readUTF());
        for(int i = 0; i<numCards; i++)
        {
            pl.addCard(new Card());
        }
        pl.print();
        while(true)
        {
            start.out.writeUTF("\n");
            start.out.writeUTF("\nEnter tile value, <enter> for random tile, or \"q\" to quit):  ");
            String input = start.input.readUTF().toUpperCase();
            start.out.writeUTF("\n");
            if(input.equals("Q"))
            {
                start.out.writeUTF("\nGame ended");
            }
            else if(t.contains(input)) 
            {
                start.out.writeUTF("\n"+input + " has already been called.");
            }
            else if(!t.isValidTile(input))
            {
                start.out.writeUTF("\n"+input + " is not a valid tile.");
            }
            else
            { 
                String newTile;
                if(input.length()==0)
                    {newTile = aiGuess();}
                else
                    {newTile = input;}
                start.out.writeUTF("\nThe tile called was " + newTile);
                pl.call(newTile);
                t.add(newTile);
                if(pl.win())
                {
                    start.out.writeUTF("\nBINGO!!");
                    pl.print();
                }
            }
            pl.print();
        }
    }
    
    private static HashSet<String> set = new HashSet<String>();
    static Random r = new Random();
    public static String aiGuess()
    {    
        while(true){
            int digit = r.nextInt(75)+1;String assemble = "";
            if(digit>=1 && digit<=15){assemble+="B"+digit;} 
            if(digit>=16 && digit<=30){assemble+="I"+digit;}
            if(digit>=31 && digit<=45){assemble+="N"+digit;}
            if(digit>=46 && digit<=60){assemble+="G"+digit;}
            if(digit>=61 && digit<=75){assemble+="O"+digit;}
            if(set.add(assemble) == true){return assemble;}
        }
    }
}
