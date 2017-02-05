package loretips;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;

public final class Loretips {
	public static final Logger logger = LogManager.getLogger("Loretips");
	
	public static ArrayList<CachedTooltip> allTooltips = new ArrayList<CachedTooltip>(128);
	private static boolean inited;
	
	/** Called after reloading a localization to rebuild caches. */
	public static void rebuildLocalizations() {
		
	}
	
	public static final void init() {
		if(!inited) {
			inited = true;
			
			// log
			logger.info("Initializing Loretips...");
			
			// register reload handler
			IResourceManager resourceManager = Minecraft.getMinecraft().getResourceManager();
			
			if(resourceManager instanceof IReloadableResourceManager) {
				IReloadableResourceManager reloadableResourceManager = (IReloadableResourceManager)resourceManager;
				
				reloadableResourceManager.registerReloadListener(new ReloadHandler());
			}
			else {
				logger.warn("resource manager not instance of IReloadableResourceManager: cached tooltips will not be automatically reloaded on resource reload");
			}
		}
	}
	
	protected static class ReloadHandler implements IResourceManagerReloadListener {
		@Override
		public void onResourceManagerReload(IResourceManager parResourceManager) {
			// log
			logger.info("Refetching tooltip localizations");
		}
	}
}
