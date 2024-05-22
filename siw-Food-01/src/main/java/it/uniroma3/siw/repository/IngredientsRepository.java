package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Ingredient;

public interface IngredientsRepository extends CrudRepository <Ingredient, Long> {

public boolean existsByName(String name);	

	
	@Query(value="select * "
			+ "from ingredient s "
			+ "where s.id not in "
			+ "(select ingredients_id "
			+ "from recipe_ingredients "
			+ "where recipe_ingredients.ingredients_id = :recipeId)", nativeQuery=true)
	Iterable<Ingredient> findIngredientsNotRecipe(@Param ("recipeId") Long recipeId);

}
