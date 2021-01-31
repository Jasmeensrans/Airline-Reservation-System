package Gateways;

import Usecases.UserManager;

import java.io.*;

public class UserManagerGateway {
    private final String path = "UserManagerFile.ser";

    public void saveToFile(UserManager um) throws IOException{
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(um);
        output.close();

    }

    public UserManager readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            UserManager um = (UserManager) input.readObject();
            input.close();
            return um;
        } catch(IOException io){
            return new UserManager();
        }
    }
}
