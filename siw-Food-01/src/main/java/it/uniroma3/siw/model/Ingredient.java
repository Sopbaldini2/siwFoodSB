package it.uniroma3.siw.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Float quantita;
	@ManyToMany(mappedBy="ingredients")
	private Set<Recipe> ingredients;
	
	public Ingredient() {
		this.ingredients = new HashSet<>();
	}

	//Aggiungo i metodi Getter e Setter
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getQuantita() {
		return quantita;
	}

	public void setQuantita(Float quantita) {
		this.quantita = quantita;
	}

	public Set<Recipe> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Recipe> ingredients) {
		this.ingredients = ingredients;
	}

	//Aggiungo i metodi Equals() e HashCode() 
	//Due oggetti Recipe sono uguali se hanno lo stesso id, lo stesso nome e la stessa ricetta
	
	@Override
	public int hashCode() {
		return Objects.hash(ingredients, name, quantita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		return Objects.equals(ingredients, other.ingredients) && Objects.equals(name, other.name)
				&& Objects.equals(quantita, other.quantita);
	}
}

