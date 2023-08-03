package kalimod.one;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.GenerationStep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("kalimod");
    
    // Cobalt Ore
    public static final Block COBALT_ORE = new Block(FabricBlockSettings.create().strength(3.0f).requiresTool());
    public static final Item UNREFINED_COBALT = new Item(new FabricItemSettings());
    public static final Item REFINED_COBALT = new Item(new FabricItemSettings());
    public static final RegistryKey<PlacedFeature> COBALT_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("kalimod","cobalt_ore"));
    
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// LOGGER.info("Hello Fabric world!");
		
		// Cobalt Ore
        Registry.register(Registries.BLOCK, new Identifier("kalimod", "cobalt_ore"), COBALT_ORE);
        Registry.register(Registries.ITEM, new Identifier("kalimod", "cobalt_ore"), new BlockItem(COBALT_ORE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier("kalimod", "unrefined_cobalt"), UNREFINED_COBALT);
        Registry.register(Registries.ITEM, new Identifier("kalimod", "refined_cobalt"), REFINED_COBALT);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, COBALT_ORE_PLACED_KEY);
        
        // Creative
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
        	content.addAfter(Items.COPPER_ORE, COBALT_ORE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
        	content.addAfter(Items.RAW_COPPER, UNREFINED_COBALT);
        	content.addAfter(Items.COPPER_INGOT, REFINED_COBALT);
        });
	}
}