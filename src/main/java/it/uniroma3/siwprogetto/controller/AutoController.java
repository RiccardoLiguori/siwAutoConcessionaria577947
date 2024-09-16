package it.uniroma3.siwprogetto.controller;


	import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siwprogetto.model.Auto;
import it.uniroma3.siwprogetto.service.AutoService;
import it.uniroma3.siwprogetto.validator.AutoValidator;

	@Controller
	public class AutoController {
		
			@Autowired 
			private AutoService AutoService;

		    @Autowired
		    private AutoValidator AutoValidator;

		
		    @GetMapping("/ParcoAuto")
		    public String getAutoList(Model model) {      
		        Iterable<Auto> availableAutos = this.AutoService.findAvailableAutos();
		        model.addAttribute("AutoList", availableAutos);
		        return "ParcoAuto.html";
		    }
		    
		    @GetMapping("/admin/ParcoAuto")
		    public String getAutoListIndex(Model model) {      
		        Iterable<Auto> availableAutos = this.AutoService.findAvailableAutos();
		        model.addAttribute("AutoList", availableAutos);
		        return "admin/ParcoAuto.html";
		    }
		    
		    
		
			 
		    @GetMapping("/Auto/{id}")
			  public String getAuto(@PathVariable("id") Long id, Model model) {
			    model.addAttribute("Auto", this.AutoService.findById(id));
			    return "Auto.html";
			  }
			
			 
			 
			    @GetMapping("/admin/Auto/{id}")
				  public String getAutoIndex(@PathVariable("id") Long id, Model model) {
				    model.addAttribute("Auto", this.AutoService.findById(id));
				    return "admin/Auto.html";
				  }
				 
				 @GetMapping("/admin/formNewAuto")
				    public String formNewAutoIndex(Model model) {
				    model.addAttribute("Auto", new Auto());
				    return "admin/formNewAuto.html";
				  }

		
			 @PostMapping("admin/ParcoAuto")
			    public String newAutoIndex(@ModelAttribute("Auto") Auto auto, BindingResult bindingResult) {
			        // Valida l'oggetto Auto utilizzando il validator
			        AutoValidator.validate(auto, bindingResult);

			        // Verifica se ci sono errori di validazione
			        if (bindingResult.hasErrors()) {
			            return "/admin/formNewAuto"; // Ritorna alla form con gli errori
			        } else {
			            // Altrimenti, salva la nuova auto
			            AutoService.save(auto);
			            return "redirect:Auto/" + auto.getId();
			        }
			    }
			 
			 @PostMapping(value = "/ParcoAutoFilter")
			 public String getAutoFilter(Model model,
			                             @RequestParam String marca,
			                             @RequestParam String modello,
			                             @RequestParam String alimentazione,
			                             @RequestParam String condizioni,
			                             @RequestParam int prezzoMassimo
			                             ) {
			     Iterable<Auto> filteredAutos;

			     
			     System.out.println("Received prezzoMassimo: " + prezzoMassimo);
			     if (!"all".equals(alimentazione) && !"all".equals(marca) && !"all".equals(modello) && !"all".equals(condizioni) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca, modello, condizioni,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(marca) && !"all".equals(modello) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca, modello,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(marca) && !"all".equals(condizioni)) {
			         filteredAutos = AutoService.findByAlimentazioneAndMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca, condizioni,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(modello) && !"all".equals(condizioni)) {
			         filteredAutos = AutoService.findByAlimentazioneAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, modello, condizioni,prezzoMassimo);
			     } else if (!"all".equals(marca) && !"all".equals(modello) && !"all".equals(condizioni) ) {
			         filteredAutos = AutoService.findByMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(marca, modello, condizioni,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(marca) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(modello) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, modello,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(condizioni)) {
			         filteredAutos = AutoService.findByAlimentazioneAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, condizioni,prezzoMassimo);
			     } else if (!"all".equals(marca) && !"all".equals(modello)) {
			         filteredAutos = AutoService.findByMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(marca, modello,prezzoMassimo);
			     } else if (!"all".equals(marca) && !"all".equals(condizioni)) {
			         filteredAutos = AutoService.findByMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(marca, condizioni,prezzoMassimo);
			     } else if (!"all".equals(modello) && !"all".equals(condizioni) ) {
			         filteredAutos = AutoService.findByModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(modello, condizioni,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione,prezzoMassimo);
			     } else if (!"all".equals(marca) ) {
			         filteredAutos = AutoService.findByMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(marca,prezzoMassimo);
			     } else if (!"all".equals(modello) ) {
			         filteredAutos = AutoService.findByModelloAndPurchaseIsNullAndPrezzoLessThanEqual(modello,prezzoMassimo);
			     } else if (!"all".equals(condizioni) ) {
			         filteredAutos = AutoService.findByCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(condizioni,prezzoMassimo);
			     }
			     else {
			         filteredAutos = AutoService.findByPrezzoLessThanEqualAndPurchaseIsNull(prezzoMassimo);
			     }
			    

			     model.addAttribute("AutoList", filteredAutos);
			     model.addAttribute("marca", marca);
			     model.addAttribute("modello", modello);
			     model.addAttribute("alimentazione", alimentazione);
			     model.addAttribute("condizioni", condizioni);
			     model.addAttribute("prezzoMassimo",prezzoMassimo);
			     return "ParcoAutoFilter.html";
			 }
			 
			

			 @PostMapping(value = "/admin/ParcoAutoFilter")
			 public String getAutoFilterAdmin(Model model,
			                             @RequestParam String marca,
			                             @RequestParam String modello,
			                             @RequestParam String alimentazione,
			                             @RequestParam String condizioni,
			                             @RequestParam int prezzoMassimo
			                             ) {
			     Iterable<Auto> filteredAutos;

			     
			     System.out.println("Received prezzoMassimo: " + prezzoMassimo);
			     if (!"all".equals(alimentazione) && !"all".equals(marca) && !"all".equals(modello) && !"all".equals(condizioni) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca, modello, condizioni,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(marca) && !"all".equals(modello) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca, modello,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(marca) && !"all".equals(condizioni)) {
			         filteredAutos = AutoService.findByAlimentazioneAndMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca, condizioni,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(modello) && !"all".equals(condizioni)) {
			         filteredAutos = AutoService.findByAlimentazioneAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, modello, condizioni,prezzoMassimo);
			     } else if (!"all".equals(marca) && !"all".equals(modello) && !"all".equals(condizioni) ) {
			         filteredAutos = AutoService.findByMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(marca, modello, condizioni,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(marca) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(modello) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, modello,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) && !"all".equals(condizioni)) {
			         filteredAutos = AutoService.findByAlimentazioneAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, condizioni,prezzoMassimo);
			     } else if (!"all".equals(marca) && !"all".equals(modello)) {
			         filteredAutos = AutoService.findByMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(marca, modello,prezzoMassimo);
			     } else if (!"all".equals(marca) && !"all".equals(condizioni)) {
			         filteredAutos = AutoService.findByMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(marca, condizioni,prezzoMassimo);
			     } else if (!"all".equals(modello) && !"all".equals(condizioni) ) {
			         filteredAutos = AutoService.findByModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(modello, condizioni,prezzoMassimo);
			     } else if (!"all".equals(alimentazione) ) {
			         filteredAutos = AutoService.findByAlimentazioneAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione,prezzoMassimo);
			     } else if (!"all".equals(marca) ) {
			         filteredAutos = AutoService.findByMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(marca,prezzoMassimo);
			     } else if (!"all".equals(modello) ) {
			         filteredAutos = AutoService.findByModelloAndPurchaseIsNullAndPrezzoLessThanEqual(modello,prezzoMassimo);
			     } else if (!"all".equals(condizioni) ) {
			         filteredAutos = AutoService.findByCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(condizioni,prezzoMassimo);
			     }
			     else {
			         filteredAutos = AutoService.findByPrezzoLessThanEqualAndPurchaseIsNull(prezzoMassimo);
			     }
			    

			     model.addAttribute("AutoList", filteredAutos);
			     model.addAttribute("marca", marca);
			     model.addAttribute("modello", modello);
			     model.addAttribute("alimentazione", alimentazione);
			     model.addAttribute("condizioni", condizioni);
			     model.addAttribute("prezzoMassimo",prezzoMassimo);
			     return "/admin/ParcoAutoFilter.html";
			 }
			// Metodo per mostrare il form di modifica
			    @GetMapping("/admin/editAuto/{id}")
			    public String editAutoForm(@PathVariable("id") Long id, Model model) {
			        Auto auto = AutoService.findById(id);
			        model.addAttribute("auto", auto);
			        return "admin/editAuto";
			    }

				@PostMapping("/admin/editAuto/{id}")
		public String editAutoSubmit(@PathVariable("id") Long id,
									 @ModelAttribute("auto") Auto updatedAuto,  // L'oggetto passato dal form
									 BindingResult bindingResult,
									 @RequestParam String urlimage,
									 @RequestParam LocalDate immatricolazione,
									 @RequestParam int prezzo,
									 @RequestParam int chilometraggio,
									 @RequestParam String colore,
									 RedirectAttributes redirectAttributes,
									 Model model) {

			Auto existingAuto = AutoService.findById(id);  // Carica l'auto esistente dal database

			// Aggiorna solo i campi modificabili su updatedAuto (che è già collegato al form)
			updatedAuto.setUrlimage(urlimage);
			updatedAuto.setImmatricolazione(immatricolazione);
			updatedAuto.setPrezzo(prezzo);
			updatedAuto.setChilometraggio(chilometraggio);
			updatedAuto.setColore(colore);

			// Copia i campi non modificabili dall'oggetto esistente a updatedAuto
			updatedAuto.setMarca(existingAuto.getMarca());
			updatedAuto.setModello(existingAuto.getModello());
			updatedAuto.setCondizioni(existingAuto.getCondizioni());
			updatedAuto.setAlimentazione(existingAuto.getAlimentazione());
			updatedAuto.setCilindrata(existingAuto.getCilindrata());
			updatedAuto.setPotenza(existingAuto.getPotenza());
			updatedAuto.setCambio(existingAuto.getCambio());

			// Valida l'oggetto updatedAuto
			AutoValidator.validate(updatedAuto, bindingResult);

			if (bindingResult.hasErrors()) {
				// Se ci sono errori, il form viene ripopolato con l'oggetto 'updatedAuto'
				return "admin/editAuto";
			}

			// Salva l'oggetto aggiornato
			AutoService.save(updatedAuto);  // Qui stai salvando l'oggetto aggiornato
			redirectAttributes.addFlashAttribute("successMessage", "Auto aggiornata con successo.");
			return "redirect:/admin/ParcoAuto";
		}



		@PostMapping("/admin/deleteAuto/{id}")
			    public String deleteAuto(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
			        Auto auto = AutoService.findById(id);
			        if (auto != null) {
			            AutoService.delete(auto);
			            redirectAttributes.addFlashAttribute("successMessage", "" + auto.getMarca() + " " + auto.getModello() + " eliminata con successo.");
			        } else {
			            redirectAttributes.addFlashAttribute("errorMessage", "Auto non trovata.");
			        }
			        return "redirect:/admin/ParcoAuto";
			    }

	}			 
			 
	