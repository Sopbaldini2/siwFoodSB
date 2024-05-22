package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ingredient;
import it.uniroma3.siw.repository.IngredientsRepository;

@Service
public class IngredientsService {

	@Autowired
	private IngredientsRepository ingredientRepository;
	
	public Ingredient findById(Long id) {
		return ingredientRepository.findById(id).get();
	}

	public Iterable <Ingredient> findIngredientsNotRecipe(@Param ("recipeId") Long recipeId) {
		return ingredientRepository.findIngredientsNotRecipe(recipeId);
	}

}
