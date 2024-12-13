package Hotel.Haven.reposiorty;

import Hotel.Haven.entity.UserProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPropertyReposiotry extends JpaRepository<UserProperty, Long> {
    Optional<UserProperty> findByEmail(String email);
}
