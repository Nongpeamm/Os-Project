package clientserver;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Server implements Runnable {
    private ServerSocket ss;
    private Users[] user = new Users[4];

    @Override
    public void run() {
        try {
            ss = new ServerSocket(6700);
            InetAddress s_ip = InetAddress.getLocalHost();
            System.out.println(dateTime() + " [SERVER] SOCKET CREATED: Server run at " + s_ip.getHostAddress());
            System.out.println(dateTime() + " [STATUS] WAITING: Waiting for connection...");

            while(true) {
                Socket s = ss.accept();
                for (int i=0; i< user.length; i++) {
                    if (user[i] == null) {
                        user[i] = new Users(s, i);
                        Thread thread = new Thread(user[i]);
                        thread.start();
                        System.out.println(dateTime() + " [SERVER] CLIENT CONNECTED: Connection from " + s.getInetAddress() + " PLAYER_SLOT[" + i + "]");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(dateTime() + " [STATUS] ERROR: " + e);
        }
    }

    public static void main(String[] args) {
        new Server().run();
    }
    // public static void main(String[] args) {
        // try {
        //     ServerSocket ss = new ServerSocket(6700);
        //     InetAddress s_ip = InetAddress.getLocalHost();
        //     Users[] user = new Users[4];
        //     System.out.println(dateTime() + " [SERVER] SOCKET CREATED: Server run at " + s_ip.getHostAddress());
        //     System.out.println(dateTime() + " [STATUS] WAITING: Waiting for connection...");

        //     while(true) {
        //         Socket s = ss.accept();
        //         for (int i=0; i< user.length; i++) {
        //             DataInputStream din = new DataInputStream(s.getInputStream());
        //             DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        //             if (user[i] == null) {
        //                 System.out.println(dateTime() + " [SERVER] CLIENT CONNECTED: Connection from " + s.getInetAddress());
        //                 user[i] = new Users(dout, din, user);
        //                 Thread thread = new Thread(user[i]);
        //                 thread.start();
        //                 break;
        //             }
        //         }
        //     }
            
        // } catch (IOException e) {System.out.println(dateTime() + " [STATUS] ERROR: " + e);}
    // }

    public static String dateTime() {
        Date d = new Date();
        SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
        return sm.format(d).toString();
    }

    class Users implements Runnable {
        DataInputStream din;
        DataOutputStream dout;
        Socket s;
        int index;
        String username;
        boolean isRunning;
    
        public Users(Socket s, int index) {
            this.s = s;
            this.index = index;
            isRunning = true;
        }
        
        @Override
        public void run() {
            try {
                dout = new DataOutputStream(s.getOutputStream());
                din = new DataInputStream(s.getInputStream());
                username = din.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            while(isRunning) {
                try {
                    String message = din.readUTF();
                    for (int i=0; i<user.length; i++) {
                        if(user[i] != null && user[i].username != username) {
                            user[i].dout.writeUTF("\n" + username + ": " + message);
                        }
                    }
                } catch (IOException e) {
                    user[index] = null;
                }
            }
        }
    }
}

// class Users implements Runnable {
//     DataInputStream din;
//     DataOutputStream dout;
//     Users[] user = new Users[4];
//     String username;

//     public Users(DataOutputStream dout, DataInputStream din, Users[] user) {
//         this.dout = dout;
//         this.din = din;
//         this.user = user;
//     }

//     public void run() {
//         try {
//             username = din.readUTF();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         while(true) {
//             try {
//                 String message = din.readUTF();
//                 for (int i=0; i<user.length; i++) {
//                     if(user[i] != null && user[i].username != username) {
//                         user[i].dout.writeUTF("\n" + username + ": " + message);
//                     }
//                     if(user[i] == null && i != user.length-1) {
//                         i++;
//                     //     if(user[i+1] != null) {
//                     //         user[i] = user[i+1];
//                     //         user[i+1] = null;
//                     //     }
//                     }
//                 }
//             } catch (IOException e) {
//                 throw new Error(e);
//             }
//         }
//     }
// }