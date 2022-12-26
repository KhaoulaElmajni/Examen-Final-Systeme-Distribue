package me.elmajni.commonapi

abstract class BaseEvent<T> (
    open val id: T
);

/*****************************Customer*********************************/

data class CustomerCreatedEvent(
    override val id: String,
    val payload: CustomerDTO
) : BaseEvent<String>(id);

data class CustomerUpdatedEvent(
    override val id: String,
    val payload: CustomerDTO
) : BaseEvent<String>(id);

data class CustomerDeletedEvent(
    override val id: String,
    val payload: CustomerDTO
) : BaseEvent<String>(id);

/*****************************Produit*********************************/

data class ProduitCreatedEvent(
    override val id: String,
    val payload: ProduitDTO
) : BaseEvent<String>(id);

data class ProduitUpdatedEvent(
    override val id: String,
    val payload: ProduitDTO
) : BaseEvent<String>(id);

data class ProduitDeletedEvent(
    override val id: String,
    val payload: ProduitDTO
) : BaseEvent<String>(id);

data class ProduitEtatChangedEvent(
    override val id: String,
    val payload: ProduitDTO
) : BaseEvent<String>(id);

/*****************************Categorie*********************************/

data class CategorieCreatedEvent(
    override val id: String,
    val payload: CategorieDTO
) : BaseEvent<String>(id);

data class CategorieUpdatedEvent(
    override val id: String,
    val payload: CategorieDTO
) : BaseEvent<String>(id);

data class CategorieDeletedEvent(
    override val id: String,
    val payload: CategorieDTO
) : BaseEvent<String>(id);




