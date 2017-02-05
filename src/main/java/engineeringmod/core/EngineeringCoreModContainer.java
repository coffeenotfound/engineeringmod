package engineeringmod.core;

import java.util.Arrays;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class EngineeringCoreModContainer extends DummyModContainer {
	
	public EngineeringCoreModContainer() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "engineeringcore";
		meta.name = "engineeringcore";
		meta.description = "Coremod of the unnamed Engineering Mod";
		meta.version = "1.7.10-1.0";
		meta.authorList = Arrays.asList("me");
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}
	
	@Subscribe
	public void preInit(FMLPreInitializationEvent event) {
//		ConfigHandler.init(event.getSuggestedConfigurationFile());
	}
}
