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
            case MESSAGEHOME:
                return new GUIMessageHomeView((GUIMessageHomeController) newController);
            case ADMINFLIGHT:
                return new GUIAdminFlightView((GUIAdminFlightController) newController);
            case MANAGINGFLIGHTS:
                return new GUIManagingFlightsView((GUIManagingFlightController) newController);
            case USERMANGER:
                return new GUIUserManagerView((GUIUserManagerController) newController);
            case NEWMESSAGE:
                return new GUINewMessageView((GUINewMessageController) newController);
            case MESSAGE:
                return new GUIMessageView((GUIMessageController) newController);
            case CREATEFLIGHT:
                return new GUICreateFlightView((GUICreateFlightController) newController);

        }
        return null;
    }
}
