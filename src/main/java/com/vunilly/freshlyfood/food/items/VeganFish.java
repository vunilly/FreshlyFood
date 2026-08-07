package com.vunilly.freshlyfood.food.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.plugin.java.JavaPlugin;

import com.vunilly.freshlyfood.food.Food;
import com.vunilly.freshlyfood.utils.Lang;
import java.util.List;

public class VeganFish extends Food {

    public VeganFish(JavaPlugin plugin) {
        super(plugin, 1001.0f, "vegan_fish");
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.COD);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.itemName(Lang.get("foods.vegan_fish").getFirst());
            CustomModelDataComponent cmd = meta.getCustomModelDataComponent();
            cmd.setFloats(List.of(getCustomModelData())); 
            meta.setCustomModelDataComponent(cmd); 
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public void registerRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(plugin, "vegan_fish"), createItem());

        recipe.addIngredient(Material.KELP);
        recipe.addIngredient(Material.CARROT);
        recipe.addIngredient(Material.SUGAR);
        recipe.addIngredient(Material.POTATO);

        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onItemConsume(Player player) {
        
    }
}