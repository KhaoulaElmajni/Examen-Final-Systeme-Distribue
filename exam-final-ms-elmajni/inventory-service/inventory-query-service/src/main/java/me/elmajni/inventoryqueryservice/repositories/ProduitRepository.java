package me.elmajni.inventoryqueryservice.repositories;


import me.elmajni.inventoryqueryservice.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, String> {

    List<Produit> findByCategorie(String categorie);
    /*@Query("select v from Vehicle v where v.ownerShips = ?1")
    List<Vehicle> findbyOwnerId(String id);*/
}
