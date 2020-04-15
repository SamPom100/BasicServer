package Games;

import java.util.Scanner;

public class Adventure
{

    Scanner myScanner = new Scanner(System.in);
    Scanner enterScanner = new Scanner(System.in);
    int playerHP;
    String playerName;
    String playerWeapon;
    int choice;
    int monsterHP;
    int silverRing;

    public static void main()
    {
        Adventure a = new Adventure();
        try
        {
            a.playerSetUp(); 	
            a.townGate();
        }
        catch(Exception e)
        {

        }
    }

    public void playerSetUp() throws Exception
    {

        playerHP = 10;
        monsterHP = 15;

        playerWeapon = "Knife";

        start.out.writeUTF("Your HP: "+ playerHP);
        start.out.writeUTF("Your Weapon: "+ playerWeapon);

        start.out.writeUTF("Please enter your name:");

        playerName = myScanner.nextLine();

        start.out.writeUTF("Hello " + playerName + ", let's start the game!");	

    }	

    public void townGate() throws Exception
    {
        start.out.writeUTF("\n------------------------------------------------------------------\n");
        start.out.writeUTF("You are at the gate of the town.");
        start.out.writeUTF("A guard is standing in front of you.");
        start.out.writeUTF("");
        start.out.writeUTF("What do you want to do?");
        start.out.writeUTF("");
        start.out.writeUTF("1: Talk to the guard");
        start.out.writeUTF("2: Attack the guard");
        start.out.writeUTF("3: Leave");
        start.out.writeUTF("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if(choice==1){
            if(silverRing==1){
                ending();
            }
            else{
                start.out.writeUTF("Guard: Hello there, stranger. So your name is " + playerName + "? \nSorry but we cannot let stranger enter our town.");
                enterScanner.nextLine();
                townGate();
            }

        }
        else if(choice==2){
            playerHP = playerHP-1;
            start.out.writeUTF("Guard: Hey don't be stupid.\n\nThe guard hit you so hard and you gave up.\n(You receive 1 damage)\n");
            start.out.writeUTF("Your HP: " + playerHP);
            enterScanner.nextLine();
            townGate();
        }
        else if(choice==3){
            crossRoad();
        }	
        else{
            townGate();
        }
    }

    public void crossRoad() throws Exception
    {
        start.out.writeUTF("\n------------------------------------------------------------------\n");
        start.out.writeUTF("You are at a crossroad. If you go south, you will go back to the town.\n\n");
        start.out.writeUTF("1: Go north");
        start.out.writeUTF("2: Go east");
        start.out.writeUTF("3: Go south");
        start.out.writeUTF("4: Go west");
        start.out.writeUTF("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if(choice==1){
            north();
        }
        else if(choice==2){
            east();
        }
        else if(choice==3){
            townGate();
        }
        else if(choice==4){
            west();
        }
        else{
            crossRoad();
        }
    }

    public void north() throws Exception
    {
        start.out.writeUTF("\n------------------------------------------------------------------\n");
        start.out.writeUTF("There is a river. You drink the water and rest at the riverside.");
        start.out.writeUTF("Your HP is recovered.");
        playerHP = playerHP + 1;
        start.out.writeUTF("Your HP: " + playerHP);
        start.out.writeUTF("\n\n1: Go back to the crossroad");
        start.out.writeUTF("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if(choice==1){
            crossRoad();
        }
        else{
            north();
        }
    }

    public void east() throws Exception
    {
        start.out.writeUTF("\n------------------------------------------------------------------\n");
        start.out.writeUTF("You walked into a forest and found a Long Sword!");
        playerWeapon = "Long Sword";
        start.out.writeUTF("Your Weapon: "+ playerWeapon);
        start.out.writeUTF("\n\n1: Go back to the crossroad");
        start.out.writeUTF("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if(choice==1){
            crossRoad();
        }
        else{
            east();
        }
    }

    public void west() throws Exception
    {
        start.out.writeUTF("\n------------------------------------------------------------------\n");
        start.out.writeUTF("You encounter a goblin!\n");
        start.out.writeUTF("1: Fight");
        start.out.writeUTF("2: Run");
        start.out.writeUTF("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if(choice==1){
            fight();
        }
        else if(choice==2){
            crossRoad();
        }
        else{
            west();
        }
    }

    public void fight() throws Exception
    {
        start.out.writeUTF("\n------------------------------------------------------------------\n");
        start.out.writeUTF("Your HP: "+ playerHP);
        start.out.writeUTF("Monster HP: " + monsterHP);
        start.out.writeUTF("\n1: Attack");
        start.out.writeUTF("2: Run");
        start.out.writeUTF("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if(choice==1){
            attack();
        }
        else if(choice==2){
            crossRoad();
        }
        else{
            fight();
        }
    }

    public void attack() throws Exception
    {
        int playerDamage =0;

        if(playerWeapon.equals("Knife")){
            playerDamage = new java.util.Random().nextInt(5); 
        }
        else if(playerWeapon.equals("Long Sword")){
            playerDamage = new java.util.Random().nextInt(8); 
        }

        start.out.writeUTF("You attacked the monster and gave " + playerDamage + " damage!");

        monsterHP = monsterHP - playerDamage;

        start.out.writeUTF("Monster HP: " + monsterHP);

        if(monsterHP<1){ win(); } else if(monsterHP>0){
            int monsterDamage =0;

            monsterDamage = new java.util.Random().nextInt(4);

            start.out.writeUTF("The monster attacked you and gave " + monsterDamage + " damage!");

            playerHP = playerHP - monsterDamage;

            start.out.writeUTF("Player HP: " + playerHP);

            if(playerHP<1){ dead(); } else if(playerHP>0){
                fight();
            }
        }

    }

    public void dead() throws Exception
    {
        start.out.writeUTF("\n------------------------------------------------------------------\n");
        start.out.writeUTF("You are dead!!!");
        start.out.writeUTF("\n\nGAME OVER");
        start.out.writeUTF("\n------------------------------------------------------------------\n");
    }

    public void win() throws Exception
    {
        start.out.writeUTF("\n------------------------------------------------------------------\n");
        start.out.writeUTF("You killed the monster!");
        start.out.writeUTF("The monster dropped a ring!");
        start.out.writeUTF("You obtaind a silver ring!\n\n");
        start.out.writeUTF("1: Go east");
        start.out.writeUTF("\n------------------------------------------------------------------\n");

        silverRing = 1;

        choice = myScanner.nextInt();
        if(choice==1){
            crossRoad();
        }
        else{
            win();
        }

    }

    public void ending() throws Exception
    {
        start.out.writeUTF("\n------------------------------------------------------------------\n");
        start.out.writeUTF("Guard: Oh you killed that goblin!?? Great!");
        start.out.writeUTF("Guard: It seems you are a trustworthy guy. Welcome to our town!");
        start.out.writeUTF("\n\n           THE END                    ");
        start.out.writeUTF("\n------------------------------------------------------------------\n");
    }
}