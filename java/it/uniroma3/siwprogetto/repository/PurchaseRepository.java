package it.uniroma3.siwprogetto.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siwprogetto.model.Purchase;
import it.uniroma3.siwprogetto.model.User;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

	List<Purchase> findByUser(User user);

    List<Purchase> findByPurchaseDateAfter(LocalDate purchaseDate);
}
