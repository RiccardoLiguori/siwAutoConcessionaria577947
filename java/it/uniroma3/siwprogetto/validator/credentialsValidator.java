package it.uniroma3.siwprogetto.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siwprogetto.model.Credentials;
import it.uniroma3.siwprogetto.service.CredentialsService;

@Component
public class credentialsValidator implements Validator {

    @Autowired
    private CredentialsService credentialsService;

    private static final int MIN_LENGTH = 5;

    @Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Credentials credentials = (Credentials) target;
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        // Check for username length
        if (username.length() < MIN_LENGTH)
            errors.rejectValue("username", "size","campo Username deve contenere almeno " + MIN_LENGTH + " caratteri");

        // Check for password length
        if (password.length() < MIN_LENGTH)
            errors.rejectValue("password", "size", "campo Password deve contenere almeno " + MIN_LENGTH + " caratteri");

        // Check for existing username
        if(credentialsService.findByUsername(username).isPresent()) {
            errors.rejectValue("username", "duplicate", "Username già esistente");
        
        }
        
    }
    
}

