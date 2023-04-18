package mostafa.ehtp.applicationspringcookies.services;

import jakarta.transaction.Transactional;
import mostafa.ehtp.applicationspringcookies.entities.Utilisateur;
import mostafa.ehtp.applicationspringcookies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Utilisateur saveUser(Utilisateur user) {
        return userRepository.save(user);
    }

    @Override
    public Utilisateur isValidUser(String email , String password) {
        return userRepository.findUserByEmailAndPassword(email,password) ;
    }
}
