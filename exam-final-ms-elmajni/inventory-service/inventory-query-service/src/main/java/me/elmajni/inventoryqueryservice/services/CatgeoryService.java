package me.elmajni.inventoryqueryservice.services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import me.elmajni.commonapi.CategorieCreatedEvent;
import me.elmajni.commonapi.GetAllCategoriesQuery;
import me.elmajni.commonapi.GetCategorieById;
import me.elmajni.inventoryqueryservice.entities.Categorie;
import me.elmajni.inventoryqueryservice.repositories.CategorieRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CatgeoryService {

        private CategorieRepository categorieRepository;

        @EventHandler
        @Transactional
        public void on(CategorieCreatedEvent event) {
            log.info("CategorieCreatedEvent: {}", event);
            Categorie categorie = new Categorie();
            categorie.setId(event.getId());
            categorie.setNom(event.getPayload().getNom());
            categorie.setDescription(event.getPayload().getDescription());
            categorieRepository.save(categorie);
        }

        @QueryHandler
        public List<Categorie> on(GetAllCategoriesQuery query) {
            return categorieRepository.findAll();
        }

    @QueryHandler
    public Categorie on(GetCategorieById query) {
        return categorieRepository.findById(query.getId()).get();
    }

}

