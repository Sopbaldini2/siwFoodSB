package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Ingredients;

public interface IngredientsRepository extends CrudRepository <Ingredients, Long> {

	Iterable<Ingredients> findIngredientsNotRecipe(@Param ("recipeId") Long recipeId);

}
