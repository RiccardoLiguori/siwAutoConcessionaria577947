package it.uniroma3.siwprogetto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwprogetto.model.Auto;
import it.uniroma3.siwprogetto.model.CartItem;
import it.uniroma3.siwprogetto.model.User;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);

	CartItem findByUserAndCar(User user, Auto car);
}
