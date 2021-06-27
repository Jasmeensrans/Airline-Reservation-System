package Gateways;

import Usecases.FlightManager;

import java.io.*;

public class FlightManagerGateway {
    private final String path = "FlightManagerFile.ser";

    public void saveToFile(FlightManager fm) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(fm);
        output.close();

    }

    public FlightManager readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            FlightManager fm = (FlightManager) input.readObject();
            input.close();
            return fm;
        } catch(IOException io){
            return new FlightManager();
        }
    }
}
