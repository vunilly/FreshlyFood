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

import com.vunilly.freshlyfood.food.AbstractFood;
import com.vunilly.freshlyfood.utils.Lang;
import java.util.List;

public class VeganSteak extends AbstractFood {

    public VeganSteak(JavaPlugin plugin) {
        super(plugin, 1001.0f);
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.BEEF);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.itemName(Lang.get("foods.vegan_steak").getFirst());
            CustomModelDataComponent cmd = meta.getCustomModelDataComponent();
            cmd.setFloats(List.of(getCustomModelData())); 
            meta.setCustomModelDataComponent(cmd); 
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public void registerRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(plugin, "vegan_steak"), createItem());

        recipe.addIngredient(Material.CARROT);
        recipe.addIngredient(Material.POTATO);
        recipe.addIngredient(Material.BAMBOO);

        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onItemConsume(Player player) {
        // Deine Logik beim Essen hier rein
    }
}