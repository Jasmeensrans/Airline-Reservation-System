package Gateways;

import Usecases.MessageSystem;

import java.io.*;

public class MessageSystemGateway {
    private final String path = "MessageSystem.ser";

    public void saveToFile(MessageSystem ms) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(ms);
        output.close();

    }

    public MessageSystem readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            MessageSystem ms = (MessageSystem) input.readObject();
            input.close();
            return ms;
        } catch(IOException io){
            return new MessageSystem();
        }
    }
}
