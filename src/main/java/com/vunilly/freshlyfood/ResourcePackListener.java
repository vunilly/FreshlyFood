package com.vunilly.freshlyfood;

import java.net.URI;
import java.util.HexFormat;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.vunilly.freshlyfood.utils.Lang;

import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;

public class ResourcePackListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ResourcePackRequest request = ResourcePackRequest.resourcePackRequest()
        .packs(
            ResourcePackInfo.resourcePackInfo(
                UUID.randomUUID(),
                URI.create("https://example.com/my-pack.zip"),
                "sha1-hash-of-pack"
            )
        )
        .required(true)
        .prompt(Lang.get("msg.rp.txt").getFirst())
        .build();

        event.getPlayer().sendResourcePacks(request);
    }
}