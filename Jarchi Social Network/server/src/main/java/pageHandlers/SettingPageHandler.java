package pageHandlers;

import managers.ClientManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.events.settingEvents.*;
import shared.models.User;
import shared.responses.Response;
import shared.responses.settingResponses.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingPageHandler extends PageHandler {
    static private final Logger logger = LogManager.getLogger(SettingPageHandler.class);

    public SettingPageHandler(ClientManager clientManager) {
        super(clientManager);
    }

    public Response accountActivation(AccountActivationEvent accountActivationEvent) {
        if (clientManager.getAuthToken() == accountActivationEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(accountActivationEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            user.setActive(accountActivationEvent.isActivationState());
            if (accountActivationEvent.isActivationState()) {
                logger.info("The User : " + user.getUserName() + " Activated the account");
            } else {
                logger.info("The User : " + user.getUserName() + " Deactivated the account");
            }
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new AccountActivationResponse();
        }else {return null;}
    }

    public Response changePassword(ChangePasswordEvent changePasswordEvent) {
        if (clientManager.getOnlineUsers().containsKey(changePasswordEvent.getAuthToken())) {
            String newPassword = changePasswordEvent.getNewPasswordFormEvent().getPassword();
            User user = clientManager.getOnlineUsers().get(changePasswordEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            user.setPassword(newPassword);
            logger.info("The User : " + user.getUserName() + " Changed the Password to : " + newPassword);
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ChangePasswordResponse();
        }else {return null;}
    }

    public Response deleteAccount(DeleteAccountEvent deleteAccountEvent) {
        if (clientManager.getAuthToken() == deleteAccountEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(deleteAccountEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            clientManager.getContext().previousData.getAllUserNames().remove(user.getUserName());
            clientManager.getContext().previousData.getAllEmails().remove(user.getEmailAddress());
            clientManager.getContext().previousData.getAllPhoneNumbers().remove(user.getPhoneNumber());
            try {
                clientManager.getContext().previousData.saveNewAllPhoneNumbers();
                clientManager.getContext().previousData.saveNewAllEmailAddresses();
                clientManager.getContext().previousData.saveNewAllUserNames();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            clientManager.getContext().userDataBase.delete(user.getId());
            logger.info("The User : " + user.getUserName() + " Deleted the account");
            return new DeleteAccountResponse(true);
        }else {return null;}
    }

    public Response changeGeneralPrivacy(GeneralPrivacyEvent generalPrivacyEvent) {
        if (clientManager.getAuthToken() == generalPrivacyEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(generalPrivacyEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            user.setPrivacyState(generalPrivacyEvent.isPrivacyState());
            logger.info("The user : " + user.getUserName() + " changed the PrivacyState ");
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new GeneralPrivacyResponse();
        }else {return null;}
    }

    public Response changeItemPrivacy(ItemPrivacyEvent itemPrivacyEvent) {
        if (clientManager.getAuthToken() == itemPrivacyEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(itemPrivacyEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            user.getPrivacyStateItems()[itemPrivacyEvent.getItemType()] = itemPrivacyEvent.isPrivacyState();
            switch (itemPrivacyEvent.getItemType()) {
                case 0:
                    logger.info("The User : " + user.getUserName() + " Changed the privacyState of Birthdate");
                    break;
                case 1:
                    logger.info("The User : " + user.getUserName() + " Changed the privacyState of EmailAddress");
                    break;
                case 2:
                    logger.info("The User : " + user.getUserName() + " Changed the privacyState of PhoneNumber");
                    break;
            }
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ItemPrivacyResponse();
        }else {return null;}
    }

    public Response changeLastSeenState(LastSeenEvent lastSeenEvent) {
        if (clientManager.getAuthToken() == lastSeenEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(lastSeenEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            user.setLastSeenState(lastSeenEvent.getLastSeenState());
            switch (lastSeenEvent.getLastSeenState()) {
                case 1:
                    logger.info("The User : " + user.getUserName() + " Changed the LastSeenState to EveryBody");
                    break;
                case 2:
                    logger.info("The User : " + user.getUserName() + " Changed the LastSeenState to OnlyFollowers");
                    break;
                case 3:
                    logger.info("The User : " + user.getUserName() + " Changed the LastSeenState to NoBody");
                    break;
            }
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new LastSeenResponse();
        }else {return null;}
    }

    public Response showMyInfo (ShowMyInfoEvent showMyInfoEvent){
        if (clientManager.getAuthToken() == showMyInfoEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(showMyInfoEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                return new ShowMyInfoResponse(user.getUserName(),user.getPhoneNumber(),user.getEmailAddress(),user.getFirstName()+ " " + user.getLastName());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response logOut (LogOutEvent logOutEvent){
        if (clientManager.getAuthToken() == logOutEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(logOutEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                clientManager.disconnectUser();
                return new LogOutResponse();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}
