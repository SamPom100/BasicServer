import Games.*;
import java.net.*; 
import java.io.*; 
import java.util.*; 

public class multiServer
{
    private Socket socket   = null; 
    private ServerSocket server   = null; 
    private DataInputStream in =  null;
    public static ArrayList<ClientHandler> ar = new ArrayList<ClientHandler>(); 
    public static ArrayList<String> log = new ArrayList<String>();

    public multiServer(int port) throws Exception
    { 
        server = new ServerSocket(port); 
        System.out.println("SamChat started on port "+port); 
        Scanner scan = new Scanner(System.in);

        while(true)
        {
            socket = server.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream()); 
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String username = input.readUTF();
            ClientHandler mtch = new ClientHandler(socket, username, input, out);
            Thread t = new Thread(mtch); 
            ar.add(mtch);
            t.start();
            String adminIn = "";
            try
            {
                adminIn = scan.nextLine();
                if(adminIn.equals("quit"))
                {
                    System.out.println("*** Forced Shutdown ***");
                    System.exit(0);
                }
            }
            catch(Exception e){}
        }
    }

    public static void main(String args[]) throws Exception
    { 
        multiServer server = new multiServer(5000); 
    } 
} 

class ClientHandler implements Runnable  
{ 
    private String name; 
    private DataInputStream input; 
    private DataOutputStream out; 
    Socket s;
    private boolean connected;
    private boolean admin;

    public ClientHandler(Socket s, String name, DataInputStream input, DataOutputStream out) 
    { 
        this.input = input; 
        this.out = out; 
        this.name = name; 
        this.s = s;
        connected = true;
        admin = false;
        send(name+" joined!");
    } 

    @Override
    public void run() 
    {
        String line = ""; 
        while (true) 
        { 
            try
            {
                line = input.readUTF(); 
                lineReader(line);
            }
            catch(Exception e)
            {
                //System.out.println(e);
            }
        } 
    } 

    public void send(String in)
    {
        System.out.println("SERVERLOG: "+in);
        multiServer.log.add("LOG - "+name+": "+in);
        for(ClientHandler client: multiServer.ar)
        {
            if(client.connected == true && !client.name.equals(this.name))
            {
                try
                {
                    client.out.writeUTF("\n"+in);
                }
                catch(Exception e){}
            }
        }
    }

    public void lineReader(String line) throws Exception
    {
        if(line.equals("#bushell"))
        {
            admin = true;
            out.writeUTF("SERVER: You're now an admin\n");
            System.out.println("*** User "+name+" was promoted ***");
        }
        else if(line.equals("#shutdown") && admin)
        {
            send("Server has shutdown");
            saveLog();
            System.exit(0);
        }
        else if(line.contains("#ban") && admin)
        {
            for(ClientHandler client: multiServer.ar)
            {
                if((client.name).equals((line.substring(5)).toUpperCase()))
                    client.banned();
            }
        }
        else if(line.equals("#users") && admin)
        {
            for(ClientHandler client: multiServer.ar)
            {
                out.writeUTF(client.name+"\n");
            }
        }
        else if(line.equals("#commands") && admin)
        {
            out.writeUTF("#shutdown\n#users\n#ban\n#commands\nexit\n#saveLog\n");
        }
        else if(line.equals("#saveLog") && admin)
        {
            saveLog();
        }
        else if(((line.charAt(0)+"").equals("#")) && admin)
            out.writeUTF("SERVER: Unknown Command\n");

        if(line.equals("quit"))
        {
            send(name+" has disconnected");
            s.close();
            input.close();
            out.close();
        }

        if(line.equals("#bingo"))
        {
            out.writeUTF("Starting Bingo Game\n");
            start.bingo(input, out);
        }
        
        if(line.equals("#adventure"))
        {
            out.writeUTF("Starting adventure game\n");
            start.adventure(input, out);
        }
        if((line.charAt(0)+"").equals("#") && !admin)
            out.writeUTF("SERVER: Not Authenticated\n");
        else
            send(name+": "+line);
    }

    public void banned() throws Exception
    {
        out.writeUTF("You've been banned");
        send(name+" was banned");
        s.close();
        input.close();
        out.close();
    }

    public void saveLog() throws Exception
    {
        System.out.println("*** Saving Log ***");
        PrintWriter writer = new PrintWriter("Logs//"+System.currentTimeMillis()+".txt", "UTF-8");
        for(String str: multiServer.log)
        {
            writer.println(str);
            writer.println("");
        }
        writer.close();
    }
}


