package pl.pracainz.osk.osk;

import java.util.Arrays;
import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.User;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        User dan = new User("dan",passwordEncoder.encode("dan123"),"USER","");
        User admin = new User("admin",passwordEncoder.encode("admin"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User student = new User("student",passwordEncoder.encode("student"),"STUDENT","ACCESS_TEST1");

        List<User> users = Arrays.asList(dan,admin,student);

        // Save to db
        this.userRepository.saveAll(users);
    }
}