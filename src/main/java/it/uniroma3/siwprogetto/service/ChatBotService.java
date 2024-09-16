package it.uniroma3.siwprogetto.service;



import org.springframework.stereotype.Service;


@Service
public class ChatBotService {

    private enum State {
        INIT, INFO, RENTAL, RENTAL_CONFIRMATION, DATE_PICKER, FINAL_CONFIRMATION, CAR_AVAILABILITY
    }

    private State state = State.INIT;
    private String p;
    
    public String handleUserMessage(String message) {
        String response = "";
        message = message.toLowerCase();

        switch (state) {
            
            case INIT:
                if (message.contains("ciao")) {
                    response = " Puoi chiedermi di: 'noleggio' o 'informazioni sulla nostra azienda'.";
                    break;
                }
            case INFO:
                if (message.contains("informazioni")) {
                    response = "Siamo un'azienda leader nel settore automobilistico con vari punti vendita in Italia. Offriamo i migliori veicoli a prezzi competitivi.";
                    state = State.INIT;
                    break;
                }
            case RENTAL:
                if (message.contains("noleggio")) {
                    response = "Hai la patente B da almeno 3 anni?";
                    state = State.CAR_AVAILABILITY;
                } else {
                    response = "Non ho capito, puoi specificare 'noleggio' o 'informazioni'?";
                }
                break;
            case CAR_AVAILABILITY:
                if (message.contains("si")) {
                    response = "Perfetto, nel nostro concessionario sono disponibili diverse vetture delle seguenti case automobilistiche: Fiat, Peugeot, Ford. L'auto che cerchi è fra queste?";
                    state = State.RENTAL_CONFIRMATION;
                } else if (message.contains("no")) {
                    response = "Mi dispiace, devi avere la patente da almeno 3 anni per noleggiare un'auto con noi.";
                    state = State.INIT;
                } else {
                    response = "Puoi rispondere con 'sì' o 'no'.";
                }
                break;
            case RENTAL_CONFIRMATION:
                if (message.contains("sì") || message.contains("si")) {
                    response = "Perfetto! Da che data vorresti prenotare il noleggio?";
                    state = State.DATE_PICKER;
                } else if (message.contains("no")) {
                    response = "Mi dispiace,puoi provare ad acquistare un auto nella sezione PARCOAUTO.";
                    state = State.INIT;
                } else {
                    response = "Puoi rispondere con 'sì' o 'no'.";
                }
                break;
            case DATE_PICKER:
                    response = "Confermi la data: " + message + "?";
                    p= message;
                    state = State.FINAL_CONFIRMATION;
                break;
            case FINAL_CONFIRMATION:
                if (message.contains("conferma") || message.contains("si")) {
                	 response = "Perfetto, ci vediamo il " + p + " per firmare il contratto!";
                    state = State.INIT;  // Reset to initial state
                } else {
                    response = "Data non confermata, per favore reinserisci la data.";
                    state = State.DATE_PICKER;
                }
                break;

        }
        return response;
    }
}
