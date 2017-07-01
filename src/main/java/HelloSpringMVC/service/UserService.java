package HelloSpringMVC.service;

import HelloSpringMVC.entity.User;

/**
 * Created by Administrator on 2017/2/1.
 */
public interface UserService {

    void addUser(User user);

    void addToken(User user);
}
