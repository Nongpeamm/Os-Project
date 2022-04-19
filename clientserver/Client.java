package clientserver;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Client {
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    boolean isRunning;
    int index;
    boolean upPressed, downPressed, leftPressed, rightPressed;

    public static void main(String[] args) {
        new Client().init();
    }

    public void init() {
        try {
            s = new Socket("localhost", 6700);
            System.out.println(dateTime() + " [STATUS] SOCKET CONNECTED: Connection established.");

            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());

            isRunning = true;
            Input in = new Input(din);

            Thread thread = new Thread(in);
            thread.start();

            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER USERNAME: ");
            String username = sc.nextLine();

            while(username.isBlank()) {
                System.out.print("ENTER USERNAME: ");
                username = sc.nextLine();
            }
            dout.writeUTF(username);

            while(true) {
                String str = sc.nextLine();
                dout.writeUTF(str);
            }
        } catch (IOException e) {
            System.out.println(dateTime() + " [STATUS] ERROR: " + e);
        }
    } 

    // public static void exit() {
    //     try {
    //         dout.close();
    //         din.close();
    //         if(!s.isClosed()){
    //             s.close();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // @Override
    // public void keyTyped(KeyEvent e) {
    // }

    // @Override
    // public void keyPressed(KeyEvent e) {
    //     int code = e.getKeyCode();

    //     if(code == KeyEvent.VK_UP) {
    //         upPressed = true;
    //     }

    //     if(code == KeyEvent.VK_DOWN) {
    //         downPressed = true;
    //     }

    //     if(code == KeyEvent.VK_LEFT) {
    //         leftPressed = true;
    //     }

    //     if(code == KeyEvent.VK_RIGHT) {
    //         rightPressed = true;
    //     }
    // }

    // @Override
    // public void keyReleased(KeyEvent e) {
    //     int code = e.getKeyCode();

    //     if(code == KeyEvent.VK_UP) {
    //         upPressed = false;
    //     }

    //     if(code == KeyEvent.VK_DOWN) {
    //         downPressed = false;
    //     }

    //     if(code == KeyEvent.VK_LEFT) {
    //         leftPressed = false;
    //     }

    //     if(code == KeyEvent.VK_RIGHT) {
    //         rightPressed = false;
    //     }
    // }

    private static String dateTime() {
        Date d = new Date();
        SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
        return sm.format(d).toString();
    }

    class Input implements Runnable {
        DataInputStream din;
    
        public Input(DataInputStream din) {
            this.din = din;
        }
    
        public void run() {
            while(isRunning) {
                try {
                    String message = din.readUTF();
                    System.out.println(message);
                } catch (IOException e) {
                    // e.printStackTrace();
                    exit();
                }
            }
        }
    
        public void exit() {
            try {
                din.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// class Input implements Runnable {
//     DataInputStream din;

//     public Input(DataInputStream din) {
//         this.din = din;
//     }

//     public void run() {
//         while(true) {
//             try {
//                 String message = din.readUTF();
//                 System.out.println(message);
//             } catch (IOException e) {
//                 // e.printStackTrace();
//                 exit();
//             }
//         }
//     }

//     public void exit() {
//         try {
//             din.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
