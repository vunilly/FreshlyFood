package com.vunilly.freshlyfood.food;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.plugin.java.JavaPlugin;

import com.vunilly.freshlyfood.food.items.*;

import java.util.ArrayList;
import java.util.List;

public class FoodManager implements Listener {

    private final JavaPlugin plugin;
    private final static List<Food> registeredFoods = new ArrayList<>();

    public FoodManager(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        loadFoods();
    }

    public void discoverRecipes(Player player) {
        for (Food food : registeredFoods) {
            player.discoverRecipe(food.getRecipeKey());
        }
    }

    private void loadFoods() {
        // Prevent duplicate food registration/recipes when reloading the plugin
        registeredFoods.clear();
        addFood(new Pear(plugin));
        addFood(new GoldenPear(plugin));
        addFood(new VeganFish(plugin));
        addFood(new CookedVeganFish(plugin));
        addFood(new VeganSteak(plugin));
        addFood(new CookedVeganSteak(plugin));
    }

    private void addFood(Food food) {
        registeredFoods.add(food);
        food.registerRecipe();
    }

    public static int getItemSize() {
        return registeredFoods.size();
    }

    public static ItemStack getItem(int id) {
        if (id >= 0 && id < registeredFoods.size()) {
            return registeredFoods.get(id).createItem();
        }
        return null;
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();

        if (meta == null || !meta.hasCustomModelDataComponent()) {
            return;
        }

        CustomModelDataComponent cmd = meta.getCustomModelDataComponent();
        List<Float> floats = cmd.getFloats();

        if (floats != null && !floats.isEmpty()) {
            float modelData = floats.get(0);

            for (Food food : registeredFoods) {
                // Use epsilon comparison for floats instead of == to avoid precision issues
                if (Math.abs(food.getCustomModelData() - modelData) < 0.001f) {
                    food.onItemConsume(event.getPlayer());
                    break;
                }
            }
        }
    }
}