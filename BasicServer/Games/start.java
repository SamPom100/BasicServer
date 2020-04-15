package Games;

import java.net.*; 
import java.io.*; 
import java.util.*; 

public class start
{
    public static DataInputStream input; 
    public static DataOutputStream out; 
    public static void bingo(DataInputStream di, DataOutputStream doo)
    {
        input = di; 
        out = doo;
        BingoGame g = new BingoGame();
        try
        {
            g.demo();
        }
        catch(Exception e)
        {
        
        }
    }

    public static void adventure(DataInputStream di, DataOutputStream doo)
    {
        input = di; 
        out = doo;
        Adventure a = new Adventure();
        a.main();
    }
}
