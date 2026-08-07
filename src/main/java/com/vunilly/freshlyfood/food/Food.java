package com.vunilly.freshlyfood.food;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Food {

    protected final JavaPlugin plugin;
    private final float customModelData;
    private final NamespacedKey recipeKey;

    public Food(JavaPlugin plugin, float customModelData, String recipeId) {
        this.plugin = plugin;
        this.customModelData = customModelData;
        this.recipeKey = new NamespacedKey(plugin, recipeId);
    }

    public float getCustomModelData() {
        return customModelData;
    }

    public NamespacedKey getRecipeKey() {
        return recipeKey;
    }

    public abstract ItemStack createItem();
    public abstract void registerRecipe();
    public abstract void onItemConsume(Player player);
}