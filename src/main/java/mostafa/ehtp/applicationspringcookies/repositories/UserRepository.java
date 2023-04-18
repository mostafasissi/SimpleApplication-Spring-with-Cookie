package mostafa.ehtp.applicationspringcookies.repositories;

import mostafa.ehtp.applicationspringcookies.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Utilisateur, Long > {
    Utilisateur findUserByEmailAndPassword(String email, String password);
}
