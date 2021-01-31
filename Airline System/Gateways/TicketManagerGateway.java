package Gateways;

import Usecases.TicketManager;

import java.io.*;

public class TicketManagerGateway {
    private final String path = "TicketManager.ser";

    public void saveToFile(TicketManager tm) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(tm);
        output.close();

    }

    public TicketManager readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            TicketManager tm = (TicketManager) input.readObject();
            input.close();
            return tm;
        } catch(IOException io){
            return new TicketManager();
        }
    }
}
