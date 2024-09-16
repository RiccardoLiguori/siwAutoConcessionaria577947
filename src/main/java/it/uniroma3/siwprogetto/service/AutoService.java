package it.uniroma3.siwprogetto.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwprogetto.model.Auto;

import it.uniroma3.siwprogetto.repository.AutoRepository;



@Service
public class AutoService {

	@Autowired
	private AutoRepository autoRepository;

	public Auto findById(Long id) {
		return autoRepository.findById(id).get();
	}

	public Iterable<Auto> findAll() {
		return autoRepository.findAll();
	}
	
	public Auto save (Auto auto) {
		return autoRepository.save(auto);
		
	}
	public List<Auto> findByAlimentazioneAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione,int prezzomassimo) {
        return autoRepository.findByAlimentazioneAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione,prezzomassimo);
    }
    
    public List<Auto> findByMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(String marca,int prezzomassimo) {
        return autoRepository.findByMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(marca,prezzomassimo);
    }
    
    public List<Auto> findByAlimentazioneAndMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String marca,int prezzomassimo) {
        return autoRepository.findByAlimentazioneAndMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca,prezzomassimo);
    }

	public List<Auto> findByAlimentazioneAndMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String marca,
			String modello, String condizioni,int prezzomassimo) {
		return autoRepository.findByAlimentazioneAndMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca , modello , condizioni,prezzomassimo);
	}

	public Iterable<Auto> findByCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String condizioni,int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(condizioni,prezzomassimo);
	}

	public Iterable<Auto> findByModelloAndPurchaseIsNullAndPrezzoLessThanEqual(String modello,int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByModelloAndPurchaseIsNullAndPrezzoLessThanEqual(modello,prezzomassimo);
	}

	public Iterable<Auto> findByModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String modello, String condizioni,int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(modello,condizioni,prezzomassimo);
	}

	public Iterable<Auto> findByMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String marca, String condizioni,int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(marca, condizioni,prezzomassimo);
	}

	public Iterable<Auto> findByMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(String marca, String modello,int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(marca, modello,prezzomassimo);
	}

	public Iterable<Auto> findByAlimentazioneAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String condizioni,int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByAlimentazioneAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, condizioni,prezzomassimo);
	}

	public Iterable<Auto> findByAlimentazioneAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String modello,int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByAlimentazioneAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, modello,prezzomassimo);
	}

	public Iterable<Auto> findByMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String marca, String modello, String condizioni,int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(marca, modello, condizioni,prezzomassimo);
	}

	public Iterable<Auto> findByAlimentazioneAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String modello,
			String condizioni, int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByAlimentazioneAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, modello, condizioni,prezzomassimo);
	}

	public Iterable<Auto> findByAlimentazioneAndMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String marca,
			String condizioni, int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByAlimentazioneAndMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca, condizioni,prezzomassimo);
	}

	public Iterable<Auto> findByAlimentazioneAndMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String marca, String modello, int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByAlimentazioneAndMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(alimentazione, marca, modello,prezzomassimo);
	}


	public Iterable<Auto> findAvailableAutos() {
		// TODO Auto-generated method stub
		return autoRepository.findByPurchaseIsNull();
	}

	public Iterable<Auto> findByPrezzoLessThanEqualAndPurchaseIsNull( int prezzomassimo) {
		// TODO Auto-generated method stub
		return autoRepository.findByPrezzoLessThanEqualAndPurchaseIsNull(prezzomassimo);
	}

	

	public void delete(Auto auto) {
		// TODO Auto-generated method stub
		 autoRepository.delete(auto);
		
	}
	

}