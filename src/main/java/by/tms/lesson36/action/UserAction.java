package by.tms.lesson36.action;

import by.tms.lesson36.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAction {

    private List<User> userList;

    public UserAction(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user){
        userList.add(user);
    }

    public boolean authCheck(User userCheck){
        for (User user :userList) {
            return user.getLogin().equals(userCheck.getLogin())
             && user.getPassword().equals(userCheck.getPassword());
        }
        return false;
    }
}
