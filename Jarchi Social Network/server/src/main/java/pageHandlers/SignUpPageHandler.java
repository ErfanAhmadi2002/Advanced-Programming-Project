package pageHandlers;

import managers.ClientManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.formEvents.LoginFormEvent;
import shared.formEvents.RegistrationFormEvent;
import shared.models.Message;
import shared.models.User;
import shared.models.UserCopy;
import shared.responses.Response;
import shared.responses.signUpResponses.LoginResponse;
import shared.responses.signUpResponses.RegistrationResponse;

import java.io.IOException;
import java.util.Date;

public class SignUpPageHandler extends PageHandler {
    static private final Logger logger = LogManager.getLogger(SignUpPageHandler.class);

    public SignUpPageHandler(ClientManager clientManager) {
        super(clientManager);
    }

    public Response register(RegistrationFormEvent formEvent) {
        try {
            boolean userNameValidity;
            boolean emailAddressValidity;
            boolean phoneNumberValidity;
            synchronized (clientManager.getContext()) {
                userNameValidity = !clientManager.getContext().previousData.getAllUserNames().containsKey(formEvent.getUserName());
                phoneNumberValidity = !clientManager.getContext().previousData.getAllPhoneNumbers().contains(formEvent.getPhoneNumber());
                emailAddressValidity = !clientManager.getContext().previousData.getAllEmails().contains(formEvent.getEmailAddress());
            }
            if (userNameValidity && emailAddressValidity && phoneNumberValidity) {
                Date birthdate = new Date(0, 0, 0);
                if (!formEvent.getBirthDay().equals("")) {
                    birthdate = new Date(Integer.parseInt(formEvent.getBirthYear()),
                            Integer.parseInt(formEvent.getBirthMonth()),
                            Integer.parseInt(formEvent.getBirthDay()));
                }
                int LastId = clientManager.getContext().userDataBase.getLastId();
                User user = new User(formEvent.getFirstName(),
                        formEvent.getLastName(),
                        formEvent.getUserName(),
                        formEvent.getPassword(),
                        birthdate,
                        formEvent.getEmailAddress(),
                        formEvent.getPhoneNumber(),
                        LastId);
                synchronized (clientManager.getContext()) {
                    clientManager.getContext().userDataBase.update(user);
                    clientManager.getContext().previousData.UpdateUserNames(user);
                    clientManager.getContext().previousData.UpdateEmailAddresses(formEvent.getEmailAddress());
                    clientManager.getContext().previousData.UpdatePhoneNumbers(formEvent.getPhoneNumber());
                }
                logger.info("the new user : " + user.getUserName() + " registered successfully.");
                int authToken = clientManager.getSecureRandom().nextInt(Integer.MAX_VALUE);
                clientManager.getOnlineUsers().put(authToken, user);
                clientManager.setAuthToken(authToken);
                return new RegistrationResponse(true, true, true, authToken, new UserCopy(user));
            }
            return new RegistrationResponse(userNameValidity, emailAddressValidity, phoneNumberValidity, -1, null);
        } catch (Throwable throwable) {
            logger.error("the user could not register");
            return null;
        }
    }

    public Response login(LoginFormEvent loginFormEvent) {
        if (clientManager.getContext().previousData.getAllUserNames().containsKey(loginFormEvent.getUserName())) {
            int id = clientManager.getContext().previousData.getAllUserNames().get(loginFormEvent.getUserName());
            User user = null;
            try {
                user = clientManager.getContext().userDataBase.Load(id);
            } catch (IOException e) {
                logger.error("the user : " + user.getUserName() + " could not be loaded");
                e.printStackTrace();
            }
            if (loginFormEvent.getPassword().equals(user.getPassword())) {
                int authToken = clientManager.getSecureRandom().nextInt(Integer.MAX_VALUE);
                clientManager.getOnlineUsers().put(authToken, user);
                clientManager.setAuthToken(authToken);
                logger.info("the user : " + user.getUserName() + " logged in successfully");
                changeAllMessagesState(user);
                return new LoginResponse(authToken, new UserCopy(user), true);
            }
        }
        return new LoginResponse(0, null, false);
    }

    private void changeAllMessagesState (User user){
        for (Integer senderId:user.getAllMessages().keySet()) {
            for (Integer messageId:user.getAllMessages().get(senderId)) {
                try {
                    Message message = clientManager.getContext().messageDataBase.Load(messageId);
                    if (message.getIsSeen() == 2 && message.getSenderId() != user.getId()){
                        message.setIsSeen(3);
                        clientManager.getContext().messageDataBase.update(message);
                    }
                }catch (Throwable ignored){}
            }
        }
    }


}
