package me.elmajni.commonapi

import java.time.Instant
/*************************************Customer**************************************/

data class CustomerDTO(
    var id : String ="",
    var nom : String="",
    var adresse : String="",
    var tel :String="",
    var email :String=""
    );
data class CreateCustomerRequestDTO(
    val nom : String,
    val adresse : String,
    val tel : String,
    val email : String
);

data class CustomerResponseDTO(
    var id : String ="",
    var nom : String="",
    var adresse : String="",
    var tel :String="",
    var email :String=""
    );

data class EventDataResponseDTO<T>(
    var type : String="",
    var eventData : T ,
);

/*************************************Product**************************************/

data class ProduitDTO(
    var id : String ="",
    var nom : String="",
    var prix : Double=0.0,
    var quantity :Int=0,
    var description :String="",
    var categorieId:String="",
    var etat :ProduitEtat=ProduitEtat.Disponible
    );

data class CategorieDTO(
    var id : String ="",
    var nom : String="",
    var description :String="",
    );

