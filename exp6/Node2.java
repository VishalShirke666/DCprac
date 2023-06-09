
// This file is a Node in the distributed system.
// It acts as a Client

import java.io.*;
import java.net.*;
import java.util.HashMap;
//import java.util.PriorityQueue;

public class Node2
{
	public static int SERVER_PORT=7001;
	public static int TO_PORT=7000;
	
	public static int MAX_CONNECTIONS=3;
	
	public static final HashMap<Integer,Integer> map;
	static{
		map=new HashMap<Integer,Integer>();
		map.put(1,0);
		map.put(4,1);
		map.put(5,2);
	}
	
	public static Node_struct node=new Node_struct(2,map,MAX_CONNECTIONS);
	   
    Socket socket=null;
    static ServerSocket ss;
    
    Node2(Socket newSocket)
    {
        this.socket=newSocket;  
    }
    
    public static void print_q(){
		System.out.println("---Queue elements----");
		
		for(Integer s : node.request_q) { 
			  System.out.print(s.toString()+" | "); 
			}
		System.out.println("\n-------------");
	}
    
    public static void main(String args[]) throws IOException, InterruptedException
    {
    	BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    	
    	Socket socket=new Socket("localhost",TO_PORT);
    	//Save this-->server connection on thread 0
    	node.t[0] = new cl_thread(socket,Integer.toString(node.ID)+":1",node);
    	
    	node.t[0].start();
    	//Listen to input port continuosly
        //t[0].get_request();
        
    	//As the connection to server starts assign holder to parent
    	node.holder=node.t[0];
    	
    	ss=new ServerSocket(SERVER_PORT);
    	int i=1;
    	//For 2 child clients 
        while(i<=2)
        {
            Socket cl_socket = ss.accept(); //Accept connection from client
            //Node1 s = new Node1(socket);
            node.t[i]=new cl_thread(cl_socket,Integer.toString(node.ID)+":"+Integer.toString(i+3),node); // Give client to new thread and Run thread
            System.out.println("Accepting "+i);
            node.t[i].start();
            //Listen to input port continuosly
            
            //t[i].get_request();
            
            i=i+1;
        }
        
        
        while(true){
        	
        	System.out.println("Do you want to use CS?");
        	String str=sc.readLine();
        	
        	if(str.equalsIgnoreCase("Yes")){
        		
        		if(node.holder==null){
    	    		System.out.println("In main : This Node has TOKEN");
    	    		if(!node.USING){
    	        		System.out.println("This Node will NOW USE CS");
    	        		CriticalSection cs=new CriticalSection(); //Critical section object
            			// CS execution
    	        		node.USING=true;
                		String[] args1 = {Integer.toString(node.ID)};
                		cs.main(args1);
                		node.USING=false;
    	        	}
    	    	}
        		else if(node.request_q.isEmpty()){
        			//Add request to Request Queue
	        		node.request_q.add(node.ID);
        			//Pass request to holder variable
	        		node.holder.send_request(node.ID,node.ID);
	        		//Waiting to get token
	        		//node.holder.getToken();
        		}
        		else{
        			//Add request to Request Queue
	        		node.request_q.add(node.ID);
	        		System.out.println("Adding in main : ");
	        		print_q();
        			//Do nothing
        		}
        		
        	}
        	//else repeat the same
        		
    	}
    
    }
    
 
}