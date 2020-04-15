package Games;

import java.util.ArrayList;
public class Tiles
{
    private ArrayList<String> storage;
    public Tiles()
    {
        storage = new ArrayList<String>();
    }

    public void add(String input)
    {
        storage.add(input.toUpperCase());
    }

    public boolean contains(String newTile)
    {
        if(storage.contains(newTile.toUpperCase())){return true;}return false;
    }

    public boolean isValidTile(String tile)
    {
        tile = tile.toUpperCase();
        if(tile.length()==0){return true;}
        if(tile.charAt(0)=='B' && Integer.parseInt(tile.substring(1))>=1 && Integer.parseInt(tile.substring(1))<=15){return true;}
        if(tile.charAt(0)=='I' && Integer.parseInt(tile.substring(1))>=16 && Integer.parseInt(tile.substring(1))<=30){return true;}
        if(tile.charAt(0)=='N' && Integer.parseInt(tile.substring(1))>=31 && Integer.parseInt(tile.substring(1))<=45){return true;}
        if(tile.charAt(0)=='G' && Integer.parseInt(tile.substring(1))>=46 && Integer.parseInt(tile.substring(1))<=60){return true;}
        if(tile.charAt(0)=='O' && Integer.parseInt(tile.substring(1))>=61 && Integer.parseInt(tile.substring(1))<=75){return true;}
        return false;
    }

    public String getTile()
    {
        Game g = new Game();
        return g.aiGuess();
    }
}
