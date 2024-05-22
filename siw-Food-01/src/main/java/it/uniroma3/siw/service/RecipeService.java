package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired 
	private RecipeRepository recipeRepository;

	public Iterable <Recipe> findAll() {
		return recipeRepository.findAll();
	}

	public void save(Recipe recipe) {
		recipeRepository.save(recipe);
	}

	public Recipe findById(Long id) {
		return recipeRepository.findById(id).get();
	}

	public List<Recipe> findByName(String name) {
		return recipeRepository.findbyName(name);
	}

}
