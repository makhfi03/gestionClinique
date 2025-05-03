package services;

import dao.UserDao;
import entities.User;
import java.util.List;

public class UserService implements IService<User> {
    
    private final UserDao ud;
    
    public UserService(){
        this.ud = new UserDao();
    }

    @Override
    public boolean create(User o) {
        return ud.create(o);
    }

    @Override
    public boolean update(User o) {
        return ud.update(o);
    }

    @Override
    public boolean delete(User o) {
        return ud.delete(o);
    }

    @Override
    public List<User> findAll() {
        return ud.findAll();
    }

    @Override
    public User findById(int id) {
        return ud.findById(id);
    }
    
    public List<User> findByEmail(String email) {
        return ud.findByEmail(email);
    }
    
}