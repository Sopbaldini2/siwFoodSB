package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Cook;
import it.uniroma3.siw.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	public boolean existsByNameAndCooke(String name, Cook cooke);
	public List<Recipe> findbyName(String name);


}
