package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.RecipeValidator;
import it.uniroma3.siw.model.Ingredient;
import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.service.IngredientsService;
import it.uniroma3.siw.service.RecipeService;
import jakarta.validation.Valid;

@Controller
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientsService ingredientsService;
	
	@Autowired
	private RecipeValidator recipeValidator;
	
	@GetMapping ("/indexRecipe")
	public String indexRecipe() {
		return "indexRecipe.html";
	}
	
	@GetMapping ("/manageRecipes")
	public String manageRecipes(Model model) {
		model.addAttribute("recipes", this.recipeService.findAll());
		return "manageRecipes.html";
	}
	
	@PostMapping("/recipe")
	public String newRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult, Model model) {
		
		this.recipeValidator.validate(recipe, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.recipeService.save(recipe); 
			model.addAttribute("recipe", recipe);
			return "recipe.html";
		} else {
			return "formNewRecipe.html"; 
		}
	}
	
	@GetMapping("/recipe/{id}")
	public String getRecipe(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipe", this.recipeService.findById(id));
		return "recipe.html";
	}

	@GetMapping("/recipe")
	public String getRecipes(Model model) {		
		model.addAttribute("recipes", this.recipeService.findAll());
		return "recipes.html";
	}
	
	@GetMapping("/formNewRecipe")
	public String formNewRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		return "formNewRecipe.html";
	}

	@GetMapping("/formUpdateRecipe/{id}")
	public String formUpdateRecipe(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipe", recipeService.findById(id));
		return "formUpdateRecipe.html";
	}

	
	@GetMapping("/formSearchRecipes")
	public String formSearchRecipes() {
		return "formSearchRecipes.html";
	}

	@PostMapping("/searchRecipes")
	public String searchRecipes(Model model, @RequestParam String name) {
		model.addAttribute("recipes", this.recipeService.findByName(name));
		return "foundRecipes.html";
	}
	
	@GetMapping("/updateIngredients/{id}")
	public String updateIngredients(@PathVariable("id") Long id, Model model) {

		List<Ingredient> ingredientsToAdd = this.ingredientsToAdd(id);
		model.addAttribute("ingredientsToAdd", ingredientsToAdd);
		model.addAttribute("recipe", this.recipeService.findById(id));

		return "ingredientsToAdd.html";
	}

	@GetMapping("addIngredientToRecipe/{IngredientId}/{recipeId}")
	public String addIngredientToRecipe(@PathVariable("IngredientId") Long IngredientId, @PathVariable("recipeId") Long recipeId, Model model) {
		Recipe recipe = this.recipeService.findById(recipeId);
		Ingredient ingredient = this.ingredientsService.findById(IngredientId);
		Set<Ingredient> ingredients = recipe.getIngredients();
		ingredients.add(ingredient);
		this.recipeService.save(recipe);
		
		List<Ingredient> ingredientsToAdd = ingredientsToAdd(recipeId);
		
		model.addAttribute("recipe", recipe);
		model.addAttribute("ingredientsToAdd", ingredientsToAdd);

		return "ingredientsToAdd.html";
	}
	
	@GetMapping("/removeIngredientsFromRecipe/{IngredientId}/{recipeId}")
	public String removeIngredientsFromRecipe(@PathVariable("actorId") Long IngredientId, @PathVariable("recipeId") Long recipeId, Model model) {
		Recipe recipe = this.recipeService.findById(recipeId);
		Ingredient ingredient = this.ingredientsService.findById(IngredientId);
		Set<Ingredient> ingredients = recipe.getIngredients();
		ingredients.remove(ingredient);
		this.recipeService.save(recipe);

		List<Ingredient> ingredientsToAdd = ingredientsToAdd(recipeId);
		
		model.addAttribute("recipe", recipe);
		model.addAttribute("ingredientsToAdd", ingredientsToAdd);

		return "ingredientsToAdd.html";
	}

	private List<Ingredient> ingredientsToAdd(Long recipeId) {
		List<Ingredient> ingredientsToAdd = new ArrayList<>();

		for (Ingredient a : ingredientsService.findIngredientsNotRecipe(recipeId)) {
			ingredientsToAdd.add(a);
		}
		return ingredientsToAdd;
	}
}
