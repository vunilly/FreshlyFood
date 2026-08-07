package com.vunilly.freshlyfood.food;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.vunilly.freshlyfood.FreshlyFood;
import com.vunilly.freshlyfood.food.items.CookedVeganFish;
import com.vunilly.freshlyfood.food.items.CookedVeganSteak;
import com.vunilly.freshlyfood.food.items.GoldenPear;
import com.vunilly.freshlyfood.food.items.Pear;
import com.vunilly.freshlyfood.food.items.VeganFish;
import com.vunilly.freshlyfood.food.items.VeganSteak;

public class RecipeListener implements Listener {

    private final FreshlyFood plugin;

    public RecipeListener(FreshlyFood plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // player.discoverRecipe(VeganSteak.RECIPE_KEY);
        // player.discoverRecipe(CookedVeganSteak.RECIPE_KEY);
        // player.discoverRecipe(VeganFish.RECIPE_KEY);
        // player.discoverRecipe(CookedVeganFish.RECIPE_KEY);
        // player.discoverRecipe(Pear.RECIPE_KEY);
        // player.discoverRecipe(GoldenPear.RECIPE_KEY);
    }
}