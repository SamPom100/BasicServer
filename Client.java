import java.net.*; 
import java.io.*; 
import java.util.Scanner;

public class Client 
{ 
    private Socket socket = null; 
    private DataInputStream  input = null; 
    private DataOutputStream out = null; 
    public static boolean running = true;
    Scanner scan = new Scanner(System.in);

    public Client(String address, int port) 
    { 
        System.out.print("Enter Username: ");
        String username = scan.nextLine();
        boolean gate = true;
        while(gate)
        {
            try
            { 
                Socket socket = new Socket(address, port); 
                System.out.print("Connected to port: "+port); 
                input = new DataInputStream(socket.getInputStream()); 
                out = new DataOutputStream(socket.getOutputStream());
                Sender send = new Sender(socket, input, out);
                Reciever recieve = new Reciever(socket, input, out);
                Thread s = new Thread(send);
                Thread r = new Thread(recieve);
                s.start();
                r.start();
                out.writeUTF(username.toUpperCase());
                gate = false;
            } 
            catch(Exception e) 
            { 
                System.out.println("Searching for server . . .");
            } 
        }
        while(running)
        {

        }
    } 

    public static void main(String args[]) //127.0.0.1
    { 
        Client client = new Client("192.168.44.245", 5000); 
    } 
}

class Sender implements Runnable
{
    final DataInputStream input; 
    final DataOutputStream out; 
    Socket socket; 

    public Sender(Socket socket, DataInputStream input, DataOutputStream out)
    {
        this.input = input; 
        this.out = out; 
        this.socket = socket;
    }

    public void run()
    {
        System.out.print(" + Sender Running!");
        String line = "";
        Scanner scan = new Scanner(System.in);
        while(true)
        {
            line = scan.nextLine(); 
            try
            { 
                out.writeUTF(line);
                System.out.println();
            } 
            catch(IOException i){}
            
            if(line.equals("quit"))
            {
                try
                { 
                    out.writeUTF(line);
                    System.out.println();
                    break;
                } 
                catch(Exception e){}
            }
        }

        try
        { 
            input.close(); 
            out.close(); 
            socket.close();
            System.exit(0);
        } 
        catch(Exception e){} 
        Client.running = false;
    }
}

class Reciever implements Runnable
{
    final DataInputStream input; 
    final DataOutputStream out; 
    Socket socket; 

    public Reciever(Socket socket, DataInputStream input, DataOutputStream out)
    {
        this.input = input; 
        this.out = out; 
        this.socket = socket; 
    }

    public void run()
    {
        System.out.println(" + Reciever Running!");
        while(true)
        {
            try
            {
                System.out.print(input.readUTF());
            }
            catch(Exception e){}
        }
    }
}

