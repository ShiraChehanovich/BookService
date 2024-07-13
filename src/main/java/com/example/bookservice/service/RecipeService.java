package com.example.bookservice.service;

import com.example.bookservice.common.entity.*;
import com.example.bookservice.exception.ResourceNotFoundException;
import com.example.bookservice.repository.ProductRepository;
import com.example.bookservice.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Recipe createRecipe(Recipe recipe, List<Ingredient> ingredients) {
        Recipe savedRecipe = recipeRepository.save(recipe);

        for (Ingredient ingredient : ingredients) {
            Product product = productRepository.findById(ingredient.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + ingredient.getProduct().getId()));
            ingredient.setProduct(product);
            ingredient.setRecipe(savedRecipe);
        }

        savedRecipe.setIngredients(ingredients);
        return recipeRepository.save(savedRecipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(int id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found for this id :: " + id));
    }

    @Transactional
    public Recipe updateRecipe(int id, RecipeDTO recipeDTO) {
        Recipe recipe = getRecipeById(id);

        recipe.setTitle(recipeDTO.getTitle());
        recipe.setAuthor(recipeDTO.getAuthor());
        recipe.setInstructions(recipeDTO.getInstructions());
        recipe.getIngredients().clear();

        for (IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {
            Ingredient ingredient = new Ingredient();
            Product product = productRepository.findById(ingredientDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + ingredientDTO.getProductId()));
            ingredient.setProduct(product);
            ingredient.setQuantity(ingredientDTO.getQuantity());
            ingredient.setRecipe(recipe);
            recipe.getIngredients().add(ingredient);
        }

        return recipeRepository.save(recipe);
    }

    @Transactional
    public void deleteRecipe(int id) {
        Recipe recipe = getRecipeById(id);
        recipeRepository.delete(recipe);
    }
}
