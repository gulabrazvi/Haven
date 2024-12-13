package Hotel.Haven.controller;

import Hotel.Haven.dto.LoginDto;
import Hotel.Haven.dto.UserPropertyDto;
import Hotel.Haven.entity.UserProperty;
import Hotel.Haven.service.UserPropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/property")
public class UserPropertyController {
    private UserPropertyService userPropertyService;

    public UserPropertyController(UserPropertyService userPropertyService) {
        this.userPropertyService = userPropertyService;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<String> addProperty(@RequestBody UserPropertyDto userPropertyDto){
        UserProperty userProperty = userPropertyService.addProperty(userPropertyDto);
        return new ResponseEntity<>("Registration is successful", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String token = userPropertyService.verifyLogin(loginDto);
        if (token != null){
            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("In valid credentials", HttpStatus.NOT_ACCEPTABLE);
    }
}
