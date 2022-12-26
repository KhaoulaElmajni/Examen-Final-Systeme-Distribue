package me.elmajni.commonapi

import java.time.Instant


/******************************************Customer**********************************************/


class GetAllCustomersQuery();
data class GetCustomerById(
    val radarId:String,
);
data class GetCustomerByNom(
    val nom:String,
);
data class GetCustomerByAdresse(
    val adresse:String,
);
data class GetCustomerByTel(
    val tel:String,
);


class SubscribeToEventsQuery();
/******************************************Produit**********************************************/

class GetAllProduitsQuery();
data class GetProduitById(
    val id:String,
);
data class GetProduitByNom(
    val nom:String,
);
data class GetProduitByPrix(
    val prix:Double,
);
data class GetProduitByQuantity(
    val quantity:Int,
);
data class GetProduitByDescription(
    val description:String,
);
data class GetProduitByEtat(
    val etat:ProduitEtat,
);

/*************************Categorie***************************/
class GetAllCategoriesQuery();
data class GetCategorieById(
    val id:String,
);
data class GetCategorieByNom(
    val nom:String,
);
data class GetCategorieByDescription(
    val description:String,
);
