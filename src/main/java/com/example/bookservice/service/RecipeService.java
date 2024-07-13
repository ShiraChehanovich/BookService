package com.example.bookservice.service;

import com.example.bookservice.common.entity.Recipe;
import com.example.bookservice.exception.ResourceNotFoundException;
import com.example.bookservice.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(int id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found for this id :: " + id));
    }

    public Recipe updateRecipe(int id, Recipe recipeDetails) {
        Recipe recipe = getRecipeById(id);

        recipe.setTitle(recipeDetails.getTitle());
        recipe.setAuthor(recipeDetails.getAuthor());
        recipe.setIngredients(recipeDetails.getIngredients());

        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(int id) {
        Recipe recipe = getRecipeById(id);
        recipeRepository.delete(recipe);
    }
}
