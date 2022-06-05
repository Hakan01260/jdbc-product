package Servicee.Impl;

import Service.UserService;
import com.proje.model.User;
import com00proje.repository.Impl.UserRepositoryImpl;
import com__proje.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User saveUser(User user) {
        User u = userRepository.saveUser(user);
        return u;
    }

    @Override
    public boolean saveUserProduct(int userId, int productId) {
        return userRepository.saveUserProduct(userId,productId);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean removeUser(int id) {
        return userRepository.removeUser(id);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserProductsById(int id) {
        return userRepository.findUserProductsById(id);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findUsers();
    }
}
