package com.example.bookservice.controllers;

import com.example.bookservice.common.entity.Ingredient;
import com.example.bookservice.common.entity.Product;
import com.example.bookservice.common.entity.Recipe;
import com.example.bookservice.common.entity.RecipeDTO;
import com.example.bookservice.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
@Validated
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setAuthor(recipeDTO.getAuthor());

        List<Ingredient> ingredients = recipeDTO.getIngredients().stream().map(dto -> {
            Ingredient ingredient = new Ingredient();
            Product product = new Product();
            product.setId(dto.getProductId());
            ingredient.setProduct(product);
            ingredient.setQuantity(dto.getQuantity());
            return ingredient;
        }).collect(Collectors.toList());

        Recipe createdRecipe = recipeService.createRecipe(recipe, ingredients);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipe);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable int id, @Valid @RequestBody RecipeDTO recipeDTO) {
        Recipe updatedRecipe = recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
