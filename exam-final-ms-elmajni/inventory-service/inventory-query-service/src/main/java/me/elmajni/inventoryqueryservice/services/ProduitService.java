package me.elmajni.inventoryqueryservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import me.elmajni.commonapi.GetAllProduitsQuery;
import me.elmajni.commonapi.GetProduitById;
import me.elmajni.commonapi.ProduitCreatedEvent;
import me.elmajni.inventoryqueryservice.entities.Categorie;
import me.elmajni.inventoryqueryservice.entities.Produit;
import me.elmajni.inventoryqueryservice.repositories.CategorieRepository;
import me.elmajni.inventoryqueryservice.repositories.ProduitRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProduitService {
        private ProduitRepository produitRepository;
        private CategorieRepository categorieRepository;

        @EventHandler
        @Transactional
        public void on(ProduitCreatedEvent event) {
            log.info("ProduitCreatedEvent: {}", event);
            Categorie categorie = categorieRepository.findById(event.getPayload().getCategorieId()).get();
            Produit produit = new Produit();
            produit.setId(event.getId());
            produit.setNom(event.getPayload().getNom());
            produit.setDescription(event.getPayload().getDescription());
            produit.setCategorie(categorie);
            produitRepository.save(produit);

        }


        @QueryHandler
        public List<Produit> on(GetAllProduitsQuery query) {
            return produitRepository.findAll();
        }

        @QueryHandler
        public Produit on(GetProduitById query) {
            return produitRepository.findById(query.getId()).get();
        }

        /*@QueryHandler
        public List<Vehicle> on(GetVehicleByOwner query) {
            return vehiculeRepository.findByProprietaireIdEquals(query.getId());
        }*/

    }

