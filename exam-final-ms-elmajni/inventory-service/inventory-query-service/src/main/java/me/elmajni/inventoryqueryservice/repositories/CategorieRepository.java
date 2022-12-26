package me.elmajni.inventoryqueryservice.repositories;


import me.elmajni.inventoryqueryservice.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, String> {
}
