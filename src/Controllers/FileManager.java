package Controllers;

import Gateways.FlightManagerGateway;
import Gateways.MessageSystemGateway;
import Gateways.TicketManagerGateway;
import Gateways.UserManagerGateway;

import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    private final FlightManagerGateway fmg;
    private final MessageSystemGateway msg;
    private final TicketManagerGateway tmg;
    private final UserManagerGateway usg;

    public FileManager(){
        fmg = new FlightManagerGateway();
        msg = new MessageSystemGateway();
        tmg = new TicketManagerGateway();
        usg = new UserManagerGateway();
    }

    public ArrayList<Object> load() throws ClassNotFoundException{
        ArrayList<Object> uses = new ArrayList<>();
        uses.add(fmg.readFromFile());
        uses.add(msg.readFromFile());
        uses.add(tmg.readFromFile());
        uses.add(usg.readFromFile());
        return uses;
    }

    public void deload(UseCaseBundle bundle) throws IOException{
        fmg.saveToFile(bundle.getFlightManager());
        msg.saveToFile(bundle.getMessageSystem());
        tmg.saveToFile(bundle.getTicketManager());
        usg.saveToFile(bundle.getUserManager());
    }
}
