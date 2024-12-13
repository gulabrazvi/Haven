package Hotel.Haven.service;

import Hotel.Haven.dto.LoginDto;
import Hotel.Haven.dto.UserPropertyDto;
import Hotel.Haven.entity.UserProperty;
import Hotel.Haven.reposiorty.UserPropertyReposiotry;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPropertyService {

    private UserPropertyReposiotry userPropertyReposiotry;
    private JWTService jwtService;

    public UserPropertyService(UserPropertyReposiotry userPropertyReposiotry, JWTService jwtService) {
        this.userPropertyReposiotry = userPropertyReposiotry;
        this.jwtService = jwtService;
    }

    public UserProperty addProperty(UserPropertyDto userPropertyDto){
        UserProperty userProperty = new UserProperty();
        userProperty.setFirstName(userPropertyDto.getFirstName());
        userProperty.setLastName(userPropertyDto.getLastName());
        userProperty.setUsername(userPropertyDto.getUsername());
        userProperty.setEmail(userPropertyDto.getEmail());
        userProperty.setPassword(BCrypt.hashpw(userPropertyDto.getPassword(), BCrypt.gensalt(10)));
        UserProperty save = userPropertyReposiotry.save(userProperty);
        return save;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<UserProperty> byEmail = userPropertyReposiotry.findByEmail(loginDto.getEmail());
        if (byEmail !=null){
            UserProperty userProperty = byEmail.get();
            if (BCrypt.checkpw(loginDto.getPassword(), userProperty.getPassword())){
                return jwtService.genrateToken(userProperty);
            }
        }
        return null;
    }
}
