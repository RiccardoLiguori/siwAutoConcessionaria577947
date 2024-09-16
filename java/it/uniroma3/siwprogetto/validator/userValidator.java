package it.uniroma3.siwprogetto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siwprogetto.model.User;
import it.uniroma3.siwprogetto.service.UserService;

import java.util.regex.Pattern;

@Component
public class userValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final int MIN_NAME_LENGTH = 3;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String email = user.getEmail();
        String Name = user.getName();
        String Surname = user.getSurname();

        // Controllo del formato dell'email
        if (email == null || !Pattern.matches(EMAIL_PATTERN, email)) {
            errors.rejectValue("email", "user.email.invalid", " Formato dell'email non è valido.");
        }

        // Verifica se l'email è già utilizzata
        if (userService.findByEmail(email).isPresent()) {
            errors.rejectValue("email", "user.email.duplicate", "Questa email è già stata registrata.");
        }

        // Controllo della lunghezza del nome
        if (Name != null && Name.length() < MIN_NAME_LENGTH) {
            errors.rejectValue("name", "user.firstName.tooShort", "campo Nome deve contenere almeno " + MIN_NAME_LENGTH + " caratteri.");
        }

        // Controllo della lunghezza del cognome
        if (Surname != null && Surname.length() < MIN_NAME_LENGTH) {
            errors.rejectValue("surname", "user.lastName.tooShort", "campo Cognome deve contenere almeno " + MIN_NAME_LENGTH + " caratteri.");
        }
    }
}
