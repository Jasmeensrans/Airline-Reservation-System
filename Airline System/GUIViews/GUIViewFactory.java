package GUIViews;

import Controllers.AbstractController;
import Controllers.ControllerType;
import GUIControllers.*;


public class GUIViewFactory {
    public AbstractView generateView(AbstractController newController) {
        ControllerType type = newController.getControllerType();
        switch (type) {
            case MAINENTRY:
                return new GUIMainEntryView((GUIMainEntryController) newController);
            case LOGIN:
                return new GUILoginView((GUILoginController) newController);
            case SIGNUP:
                return new GUISignUpView((GUISignUpController) newController);
            case MENU:
                return new GUIMenuView((GUIMenuController) newController);
            case FLIGHT:
                return new GUIFlightView((GUIFlightController) newController);
            case MESSAGE:
                return new GUIMessageView((GUIMessageController) newController);
            case ADMINFLIGHT:
                return new GUIAdminFlightView((GUIAdminFlightController) newController);
            case MANAGINGFLIGHTS:
                return new GUIManagingFlightsView((GUIManagingFlightController) newController);
        }
        return null;
    }
}
