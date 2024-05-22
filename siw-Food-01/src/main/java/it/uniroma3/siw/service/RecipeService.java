package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired 
	private RecipeRepository recipeRepository;
	
	@Autowired
	private CredentialsService credentialsService;

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
	
	/* Verificare se funziona */
	
	public void deleteRecipe(Long recipeId, String username) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));

        Credentials credentials = credentialsService.findByUsername(username);

        if (credentials.getRole().equals(Credentials.ADMIN_ROLE) || 
            (credentials.getRole().equals(Credentials.DEFAULT_ROLE) && recipe.getCook().equals(credentials.getCook()))) {
            recipeRepository.delete(recipe);
        } else {
            throw new SecurityException("Not authorized to delete this recipe");
        }
    }

}
