package Games;

import java.util.Random;
import java.util.HashSet;
import java.io.*;
public class Card
{
    private String storage[][] = new String[5][5]; private Random rand = new Random();
    public Card()
    {
        for(int y = 0, dup = 0, i = 1; y<storage.length; y++){HashSet<Integer> set = new HashSet<Integer>();
            for(int x = 0; x<storage[y].length; x++){Integer[] temp = new Integer[storage.length];
                {temp[x] = rand.nextInt(15) + i; storage[x][y] = temp[x]+"";}if(set.add(temp[x]) == false){x--;}}i = i+15;}
        storage[2][2] = "free";
    }

    public Card(int[][] input)
    {
        String[][] toReturn = new String[5][5];
        for(int y = 0; y<input.length; y++)
        {
            for(int x = 0; x<input[y].length; x++)
            {
                toReturn[y][x] = input[y][x]+"";
            }
        }
        toReturn[2][4] = toReturn [2][3]; toReturn[2][3] = toReturn [2][2];
        storage = toReturn;storage[2][2] = "free";       
    } 

    public void print() throws Exception
    {
        start.out.writeUTF("B"+"\t"+"I"+"\t"+"N"+"\t"+"G"+"\t"+"O"+"\n\n");
        for(int y = 0; y<storage.length; y++)
        {
            for(int x = 0; x<storage[y].length; x++)
            {
                start.out.writeUTF(storage[y][x]+"\t");
            }
            start.out.writeUTF("\n");
        }
    }

    public boolean call(String title) //int converted = converter(title.toUpperCase().charAt(0));
    {        
        for(int y = 0; y<storage.length; y++){
            for(int x = 0; x<storage[y].length; x++){
                if(storage[y][x].equals(title.substring(1))){storage[y][x] = "x"; return true;}}}
        return false;
    }

    public boolean win()
    {
        storage[2][2] = "x";if(testHoVe()){return true;}if(testDiag()){return true;} 
        storage[2][2] = "free";return false;
    }

    public int converter(int input)
    {
        switch(input){case 66:return 0;case 73:return 1;case 78:return 2;case 71:return 3;case 79:return 4;}
        return -1;
    }

    private boolean testHoVe()
    {
        for(int y = 0, HCo = 0, VCo = 0; y<storage.length; y++){
            for(int x = 0; x<storage[y].length; x++){
                if(storage[x][y].equals("x")){HCo++;}if(storage[y][x].equals("x")){VCo++;}}
            if(HCo==5||VCo==5){return true;}HCo = 0;VCo = 0;}
        return false;
    }

    private boolean testDiag()
    {
        for(int y = 0, Lsp = 0, Rsp = 4, LC = 0, RC = 0; y<storage.length; y++){
            for(int x = 0; x<storage[y].length; x++){
                if(storage[y][x].equals("x") && x == Lsp){LC++;}               
                if(storage[y][x].equals("x") && x == Rsp){RC++;}}Lsp++; Rsp--;
            if(LC == 5 || RC == 5){return true;}
        }       
        return false;
    }

    public String toString()
    {
        String assemble = "B\tI\tN\tG\tO\n";
        for(int y = 0; y<storage.length; y++){
            for(int x = 0; x<storage[y].length; x++){
                assemble += storage[y][x]; if(x<4) assemble+="\t";
            }
            assemble+="\n";}
        return assemble.substring(0,assemble.length()-1);
    }
}