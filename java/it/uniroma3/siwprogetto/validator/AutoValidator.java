package it.uniroma3.siwprogetto.validator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siwprogetto.model.Auto;

@Component
public class AutoValidator implements Validator {
	
	private static final List<String> COLORI_AMMESSI = Arrays.asList("Bianco", "Nero", "Grigio", "Rosso", "Blu", "Verde", "Giallo", "Argento");

    @Override
    public boolean supports(Class<?> clazz) {
        return Auto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Auto auto = (Auto) target;
        
        if (auto.getModello() != null && auto.getModello().length() > 15) {
            errors.rejectValue("modello", "Size.auto.modello", "Il campo modello può contenere al massimo 15 caratteri");
        }

        if (auto.getMarca() != null && auto.getMarca().matches(".*\\d.*")) {
            errors.rejectValue("marca","marca.number","Il campo marca non può contenere numeri");
        }
        if (!auto.getUrlimage().matches("^(http|https)://.*$")) {
            errors.rejectValue("urlimage","urlimage.invalid", "Invalid");
        }
        if (auto.getImmatricolazione() != null && auto.getImmatricolazione().isBefore(LocalDate.of(2000, 1, 1))) {
            errors.rejectValue("immatricolazione", "data.immatricolazione.invalid", "La data di immatricolazione deve essere dal 01/2000 in poi");
        }
        if(auto.getImmatricolazione().isAfter(LocalDate.now())) {
            errors.rejectValue("immatricolazione", "immatricolazione.future", "La data di immatricolazione non puo essere nel futuro");
        }
        // Validation for "chilometraggio"
       if (auto.getChilometraggio() < 0 || auto.getChilometraggio()>=300000) {
            errors.rejectValue("chilometraggio", "negative.value", "Il chilometraggio non può essere negativo o superiore a 300000");
        }

        // Validation for "cilindrata"
        if (auto.getCilindrata() <= 0 || auto.getCilindrata() >= 3000) {
            errors.rejectValue("cilindrata", "invalid.value", "La cilindrata deve essere maggiore di 0 e minore di 3000");
        }

        // Validation for "potenza"
      if (auto.getPotenza() <= 0 || auto.getPotenza()>=300) {
            errors.rejectValue("potenza", "invalid.value", "La potenza deve essere maggiore di 0 e minore di 300");
        }

        // Validation for "colore"
      if (!COLORI_AMMESSI.contains(auto.getColore())) {
          errors.rejectValue("colore", "colore.invalid", "Il colore deve essere uno di questi: " + COLORI_AMMESSI);
      }

      else if (auto.getColore() == null || auto.getColore().isEmpty()) {
            errors.rejectValue("colore", "required", "Il colore è obbligatorio");
        }

        // Validation for "prezzo"
        if (auto.getPrezzo() <= 0 || auto.getPrezzo() >=100000) {
            errors.rejectValue("prezzo", "invalid.value", "Il prezzo deve essere maggiore di 0 e minore di 100000");
        }
    }

    }
