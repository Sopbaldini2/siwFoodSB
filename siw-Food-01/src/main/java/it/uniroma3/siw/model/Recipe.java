package it.uniroma3.siw.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	@ManyToOne
	private Cooke cooke;
	@ManyToMany
	private Set<Ingredients> ingredients;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Cooke getCooke() {
		return cooke;
	}
	public void setCooke(Cooke cooke) {
		this.cooke = cooke;
	}
	public Set<Ingredients> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}
	
	//Aggiungo i metodi Equals() e HashCode() 
	//Due oggetti Recipe sono uguali se hanno lo stesso id,lo stesso nome e lo stesso cuoco
	
	@Override
	public int hashCode() {
		return Objects.hash(cooke, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		return Objects.equals(cooke, other.cooke) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}