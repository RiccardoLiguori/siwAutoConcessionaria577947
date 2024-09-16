package it.uniroma3.siwprogetto.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwprogetto.model.Auto;



public interface AutoRepository extends CrudRepository<Auto,Long>{

	List<Auto> findByAlimentazioneAndPurchaseIsNullAndPrezzoLessThanEqual(String Alimentazione,int prezzomassimo);

	List<Auto> findByMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(String marca,int prezzomassimo);

	List<Auto> findByAlimentazioneAndMarcaAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String marca,int prezzomassimo);

	List<Auto> findByAlimentazioneAndMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String marca, String modello,
			String condizioni,int prezzomassimo);

	Iterable<Auto> findByCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String condizioni,int prezzomassimo);

	Iterable<Auto> findByModelloAndPurchaseIsNullAndPrezzoLessThanEqual(String modello,int prezzomassimo);

	Iterable<Auto> findByAlimentazioneAndMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String marca, String modello,int prezzomassimo);

	Iterable<Auto> findByAlimentazioneAndMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String marca, String condizioni,int prezzomassimo);

	Iterable<Auto> findByAlimentazioneAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String modello, String condizioni,int prezzomassimo);

	Iterable<Auto> findByMarcaAndModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String marca, String modello, String condizioni,int prezzomassimo);

	Iterable<Auto> findByAlimentazioneAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String modello,int prezzomassimo);

	Iterable<Auto> findByAlimentazioneAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String alimentazione, String condizioni,int prezzomassimo);

	Iterable<Auto> findByModelloAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String modello, String condizioni,int prezzomassimo);

	Iterable<Auto> findByMarcaAndCondizioniAndPurchaseIsNullAndPrezzoLessThanEqual(String marca, String condizioni,int prezzomassimo);

	Iterable<Auto> findByMarcaAndModelloAndPurchaseIsNullAndPrezzoLessThanEqual(String marca, String modello,int prezzomassimo);

	List<Auto> findByPurchaseIsNull();

	Iterable<Auto> findByPrezzoLessThanEqualAndPurchaseIsNull( int prezzomassimo);
	
	
}
