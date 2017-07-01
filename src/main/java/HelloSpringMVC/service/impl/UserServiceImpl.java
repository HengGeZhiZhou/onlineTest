package HelloSpringMVC.service.impl;

import HelloSpringMVC.dao.UserDao;
import HelloSpringMVC.entity.User;
import HelloSpringMVC.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
@Scope("prototype")
public class UserServiceImpl implements UserService {

    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public void addToken(User user) {
        userDao.update(user);
    }
}
