package me.elmajni.inventoryqueryservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.elmajni.commonapi.ProduitEtat;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Produit {
    @Id
    private String id;
    private String nom;
    private String description;
    private double prix;
    private int quantite;
    private ProduitEtat etat;

    @ManyToOne
    private Categorie categorie;

}
