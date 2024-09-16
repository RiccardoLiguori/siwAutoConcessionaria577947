package it.uniroma3.siwprogetto.model;


	import java.time.LocalDate;
	import com.fasterxml.jackson.annotation.JsonManagedReference;
	import jakarta.persistence.*;

	@Entity
	public class Auto {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

	    private String marca;
	    private String modello;
	    private String urlimage;
		private String condizioni;
		private LocalDate immatricolazione;
		private int chilometraggio;	
		private String alimentazione;
		private int cilindrata;	
		private int potenza;	
		private String cambio;	
		private String colore;
		private int prezzo;
		
		@OneToOne(mappedBy = "auto", fetch = FetchType.EAGER)
	    private Purchase purchase;

		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public String getModello() {
			return modello;
		}
		public void setModello(String modello) {
			this.modello = modello;
		}
		public String getUrlimage() {
			return urlimage;
		}
		public void setUrlimage(String urlimage) {
			this.urlimage = urlimage;
		}
		public String getCondizioni() {
			return condizioni;
		}
		public void setCondizioni(String condizioni) {
			this.condizioni = condizioni;
		}
		public LocalDate getImmatricolazione() {
			return immatricolazione;
		}
		public void setImmatricolazione(LocalDate immatricolazione) {
			this.immatricolazione = immatricolazione;
		}
		public int getChilometraggio() {
			return chilometraggio;
		}
		public void setChilometraggio(int chilometraggio) {
			this.chilometraggio = chilometraggio;
		}
		public String getAlimentazione() {
			return alimentazione;
		}
		public void setAlimentazione(String alimentazione) {
			this.alimentazione = alimentazione;
		}
		public int getCilindrata() {
			return cilindrata;
		}
		public void setCilindrata(int cilindrata) {
			this.cilindrata = cilindrata;
		}
		public int getPotenza() {
			return potenza;
		}
		public void setPotenza(int potenza) {
			this.potenza = potenza;
		}
		public String getCambio() {
			return cambio;
		}
		public void setCambio(String cambio) {
			this.cambio = cambio;
		}
		public String getColore() {
			return colore;
		}
		public void setColore(String colore) {
			this.colore = colore;
		}
		public int getPrezzo() {
			return prezzo;
		}
		public void setPrezzo(int prezzo) {
			this.prezzo = prezzo;
		}
		public Purchase getPurchase() {
			return purchase;
		}
		public void setPurchase(Purchase purchase) {
			this.purchase = purchase;
		}
/*		public boolean isAvailable() {
			// TODO Auto-generated method stub
			return this.getAvailable();
		}
		private boolean getAvailable() {
			// TODO Auto-generated method stub
			return b;
		}
		public void setAvailable(boolean b) {
			this.b=b;
			
		}
	*/	
		
	}

