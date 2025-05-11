package me.zipestudio.cleansingpotion;

import me.zipestudio.cleansingpotion.effect.ModEffects;
import me.zipestudio.cleansingpotion.potion.ModPotions;
import org.slf4j.*;

import net.fabricmc.api.ModInitializer;

public class ModServer implements ModInitializer {

	public static final String MOD_NAME = /*$ mod_name*/ "Cleansing Potion";
	public static final String MOD_ID = /*$ mod_id*/ "cleansing-potion";
	public static final String YACL_DEPEND_VERSION = /*$ yacl*/ "3.6.6+1.21.4-fabric";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("{} Initialized", MOD_NAME);

		ModEffects.registerEffects();
		ModPotions.registerPotions();

	}
}