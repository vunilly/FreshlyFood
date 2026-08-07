package com.vunilly.freshlyfood.food.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.plugin.java.JavaPlugin;

import com.vunilly.freshlyfood.food.AbstractFood;
import com.vunilly.freshlyfood.utils.Lang;
import java.util.List;

public class Pear extends AbstractFood {

    public Pear(JavaPlugin plugin) {
        super(plugin, 1001.0f);
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.APPLE);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.itemName(Lang.get("foods.pear").getFirst());
            CustomModelDataComponent cmd = meta.getCustomModelDataComponent();
            cmd.setFloats(List.of(getCustomModelData())); 
            meta.setCustomModelDataComponent(cmd); 
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public void registerRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "pear"), createItem());

        recipe.shape("GGG", "GAG", "GGG");
        recipe.setIngredient('G', Material.GREEN_DYE);
        recipe.setIngredient('A', Material.APPLE);

        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onItemConsume(Player player) {
        // Deine Logik beim Essen der Birne hier rein
    }
}