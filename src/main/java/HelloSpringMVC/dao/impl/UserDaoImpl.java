package HelloSpringMVC.dao.impl;


import HelloSpringMVC.dao.UserDao;
import HelloSpringMVC.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
@Scope("prototype")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao  {



}
