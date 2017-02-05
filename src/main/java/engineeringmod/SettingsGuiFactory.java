package engineeringmod;

import java.util.Set;
import cpw.mods.fml.client.IModGuiFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class SettingsGuiFactory implements IModGuiFactory {
	
	@Override
	public void initialize(Minecraft parMinecraft) {
		
	}
	
	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return null;
	}
	
	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}
	
	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
}
