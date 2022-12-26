package me.elmajni.commonapi

import org.axonframework.modelling.command.TargetAggregateIdentifier

//**********************Customer********************************//


abstract class BaseCommand<T>(
    @TargetAggregateIdentifier open val id : T
);
data class CreateNewCustomerCommand(
    override val id : String,
    val payload : CustomerDTO,
) : BaseCommand<String>(id);

data class UpdateCustomerCommand(
    override val id : String,
    val payload : CustomerDTO,
) : BaseCommand<String>(id);

data class DeleteCustomerCommand(
    override val id : String,
    val payload : CustomerDTO,
) : BaseCommand<String>(id);

/**********************Produit********************************/

data class CreateNewProduitCommand(
    override val id : String,
    val payload : ProduitDTO,
) : BaseCommand<String>(id);

data class UpdateProduitCommand(
    override val id : String,
    val payload : ProduitDTO,
) : BaseCommand<String>(id);

data class DeleteProduitCommand(
    override val id : String,
    val payload : ProduitDTO,
) : BaseCommand<String>(id);

data class ChangeProduitEtatCommand(
    override val id : String,
    val payload : ProduitDTO,
) : BaseCommand<String>(id);

/**********************Categorie********************************/

data class CreateNewCategorieCommand(
    override val id : String,
    val payload : CategorieDTO,
) : BaseCommand<String>(id);

data class UpdateCategorieCommand(
    override val id : String,
    val payload : CategorieDTO,
) : BaseCommand<String>(id);

data class DeleteCategorieCommand(
    override val id : String,
    val payload : CategorieDTO,
) : BaseCommand<String>(id);




