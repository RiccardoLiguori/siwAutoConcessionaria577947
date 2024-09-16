package it.uniroma3.siwprogetto.controller;

import java.security.Principal;
import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import it.uniroma3.siwprogetto.model.Auto;
import it.uniroma3.siwprogetto.model.Credentials;
import it.uniroma3.siwprogetto.model.Purchase;
import it.uniroma3.siwprogetto.model.CartItem;
import it.uniroma3.siwprogetto.model.User;
import it.uniroma3.siwprogetto.repository.AutoRepository;
import it.uniroma3.siwprogetto.repository.CartItemRepository;
import it.uniroma3.siwprogetto.repository.CredentialsRepository;
import it.uniroma3.siwprogetto.repository.PurchaseRepository;
import jakarta.transaction.Transactional;



@Controller
public class CartItemController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private AutoRepository carRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

 
        @GetMapping("/my-cart")
        public String viewCart(Model model, Principal principal) {
            Credentials credentials = credentialsRepository.findByUsername(principal.getName()).orElse(null);
            if (credentials != null) {
                User user = credentials.getUser();
                List<CartItem> cartItems = cartItemRepository.findByUser(user);
                model.addAttribute("cartItems", cartItems);
            }
            return "cart";
        }

    @PostMapping("/add-to-cart/{carId}")
    public String addToCart(@PathVariable Long carId, Principal principal, RedirectAttributes redirectAttributes) {
        String username = principal.getName();
        Credentials credentials = credentialsRepository.findByUsername(username).orElse(null);

        if (credentials != null) {
            User user = credentials.getUser();
            Auto car = carRepository.findById(carId).orElse(null);
            if (car != null) {
                // Verifica se l'auto ha già una Purchase associata
                if (car.getPurchase() != null) {
                    return "redirect:/ParcoAuto";
                }

                // Verifica se l'auto è già nel carrello
                CartItem existingItem = cartItemRepository.findByUserAndCar(user, car);
                if (existingItem == null) {
                    CartItem newItem = new CartItem();
                    newItem.setUser(user);
                    newItem.setCar(car);
                    cartItemRepository.save(newItem);
                    redirectAttributes.addFlashAttribute("successMessage", "Auto aggiunta al carrello con successo!");
                    return "redirect:/my-cart";
                } else {
                    redirectAttributes.addFlashAttribute("errorMessage", "Questa auto è già nel tuo carrello.");
                    return "redirect:/my-cart";
                }
            }
            return "redirect:/ParcoAuto";
        }
        return "redirect:/login";
    }

    
    

    @PostMapping("/checkout")
    @Transactional
    public String checkout(Principal principal, RedirectAttributes redirectAttributes) {
        Credentials credentials = credentialsRepository.findByUsername(principal.getName()).orElse(null);
        if (credentials != null) {
            User user = credentials.getUser();
            List<CartItem> cartItems = cartItemRepository.findByUser(user);
            if (!cartItems.isEmpty()) {
                for (CartItem item : cartItems) {
                    Auto car = item.getCar();
                    Purchase purchase = new Purchase(); // Creare un nuovo Purchase per ogni auto
                    purchase.setUser(user);
                    purchase.setPurchaseDate(LocalDate.now());
                    purchase.setAuto(car);
                    car.setPurchase(purchase);
                    carRepository.save(car);
                    purchaseRepository.save(purchase);
                }
                cartItemRepository.deleteAll(cartItems);
                redirectAttributes.addFlashAttribute("successMessage", "Ordine effettuato con successo");
                return "redirect:/my-account";
            }
        }
        return "redirect:/login";
    }



    @GetMapping("/my-account")
    public String myAccount(Model model, Principal principal) {
        Credentials credentials = credentialsRepository.findByUsername(principal.getName()).orElse(null);
        if (credentials != null) {
            User user = credentials.getUser();
            List<Purchase> purchases = purchaseRepository.findByUser(user);
            model.addAttribute("purchases", purchases);
        }
        return "account";
    }
    
    @PostMapping("/remove-from-cart/{carId}")
    public String removeFromCart(@PathVariable Long carId, Principal principal, RedirectAttributes redirectAttributes) {
        String username = principal.getName();
        Credentials credentials = credentialsRepository.findByUsername(username).orElse(null);

        if (credentials != null) {
            User user = credentials.getUser();
            Auto car = carRepository.findById(carId).orElse(null);

            if (car != null) {
                CartItem cartItem = cartItemRepository.findByUserAndCar(user, car);
                if (cartItem != null) {
                    cartItemRepository.delete(cartItem);
                    redirectAttributes.addFlashAttribute("successMessage", "Auto rimossa dal carrello con successo!");
                    return "redirect:/my-cart";
                } 
            } 
        }
        
        return "redirect:/login";
    }


}

