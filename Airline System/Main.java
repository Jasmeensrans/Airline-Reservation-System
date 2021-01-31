import Controllers.AbstractController;
import Controllers.EntryController;
import Controllers.FileManager;
import Controllers.UseCaseBundle;

import java.io.IOException;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        UseCaseBundle bundle = new UseCaseBundle();
        Stack<AbstractController> controllerStack = new Stack<>();
        AbstractController currentController;
        controllerStack.push(new EntryController(bundle));

        currentController = controllerStack.peek();

        while(!controllerStack.empty()) {
            AbstractController c = currentController.run();
            int popNum = currentController.getPopNum();
            if(popNum == -2){
                while(!controllerStack.empty()){
                    controllerStack.pop();
                }
            } else if(popNum == -1){
                while(!controllerStack.empty()){
                    controllerStack.pop();
                }
                controllerStack.push(new EntryController(bundle));
            } else {
                for(int i = 0; i < popNum; i++) {
                    controllerStack.pop();
                }
            }

            if(c != null){
                controllerStack.push(c);
            }

            if(!controllerStack.empty()){
                currentController = controllerStack.peek();
            }
        }
        FileManager fileManager = new FileManager();
        fileManager.deload(bundle);
    }
}

