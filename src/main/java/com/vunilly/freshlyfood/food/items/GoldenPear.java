package com.vunilly.freshlyfood.food.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.plugin.java.JavaPlugin;

import com.vunilly.freshlyfood.food.Food;
import com.vunilly.freshlyfood.utils.Lang;
import java.util.List;

public class GoldenPear extends Food {

    public GoldenPear(JavaPlugin plugin) {
        super(plugin, 1002.0f, "golden_pear");
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.APPLE);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.itemName(Lang.get("foods.golden_pear").getFirst());
            CustomModelDataComponent cmd = meta.getCustomModelDataComponent();
            if (cmd == null) {
                cmd = CustomModelDataComponent.customModelData(List.of(getCustomModelData()));
            } else {
                cmd.setFloats(List.of(getCustomModelData()));
            }
            meta.setCustomModelDataComponent(cmd);
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public void registerRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "golden_pear"), createItem());

        recipe.shape("GGG", "GAG", "GGG");
        recipe.setIngredient('G', Material.GOLD_NUGGET);
        ItemStack normalPear = new Pear(plugin).createItem();
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(normalPear));

        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onItemConsume(Player player) {
        
    }
}