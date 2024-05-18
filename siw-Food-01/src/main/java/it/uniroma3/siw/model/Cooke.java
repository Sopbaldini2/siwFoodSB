package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cooke {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname; 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataOfBirth;
	@OneToMany(mappedBy="cooke")
	private List<Recipe> cookeRecipe;
	
	public Cooke() {
		this.cookeRecipe = new LinkedList<>();
	}
	
	//Aggiungo i  metodi Getter e Setter

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getDataOfBirth() {
		return dataOfBirth;
	}

	public void setDataOfBirth(LocalDate dataOfBirth) {
		this.dataOfBirth = dataOfBirth;
	}

	public List<Recipe> getCookeRecipe() {
		return cookeRecipe;
	}

	public void setCookeRecipe(List<Recipe> cookeRecipe) {
		this.cookeRecipe = cookeRecipe;
	}

	//Aggiungo i metodi Equals() e HashCode() 
	//Due oggetti Cooke sono uguali se hanno lo stesso nome e cognome
	
	@Override
	public int hashCode() {
		return Objects.hash(name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cooke other = (Cooke) obj;
		return Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname);
	}	
}
