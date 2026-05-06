package user_service.service;

import org.springframework.stereotype.Service;
import user_service.entity.User;
import user_service.exception.UserNotFoundException;
import user_service.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
