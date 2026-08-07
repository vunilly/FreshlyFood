package com.vunilly.freshlyfood.food.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.plugin.java.JavaPlugin;

import com.vunilly.freshlyfood.food.Food;
import com.vunilly.freshlyfood.utils.Lang;
import java.util.List;

public class CookedVeganSteak extends Food {

    public CookedVeganSteak(JavaPlugin plugin) {
        super(plugin, 1001.0f, "cooked_vegan_steak");
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.COOKED_BEEF);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.itemName(Lang.get("foods.cooked_vegan_steak").getFirst());
            CustomModelDataComponent cmd = meta.getCustomModelDataComponent();
            // Prevent NPE when custom model data is not yet present
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
        NamespacedKey key = new NamespacedKey(plugin, "cooked_vegan_steak");
        ItemStack result = createItem();

        RecipeChoice veganSteakChoice = new RecipeChoice.ExactChoice(new VeganSteak(plugin).createItem());

        float experience = 0.35f;
        int cookingTime = 200; 

        FurnaceRecipe recipe = new FurnaceRecipe(key, result, veganSteakChoice, experience, cookingTime);
        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onItemConsume(Player player) {
        
    }
}