package Controllers;

import java.util.ArrayList;

public abstract class AbstractController {

    private UseCaseBundle bundle;
    private int popNum = 0;
    private ControllerType controllerType;

    public AbstractController(UseCaseBundle bundle) {
        this.bundle = bundle;
    }

    public abstract AbstractController run();

    public int getPopNum() {
        return popNum;
    }

    public void setPopNum(int popNum) {
        this.popNum = popNum;
    }

    public UseCaseBundle getBundle() {
        return bundle;
    }

    public void setControllerType(ControllerType controllerType) {
        this.controllerType = controllerType;
    }

    public ControllerType getControllerType() {
        return controllerType;
    }

}


