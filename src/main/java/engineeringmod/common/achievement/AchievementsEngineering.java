package engineeringmod.common.achievement;

import java.util.ArrayList;
import engineeringmod.common.item.EngineeringItems;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public final class AchievementsEngineering {
	public static final Achievement achievementTest1 = reg(new Achievement("test1", "test1", 0, 0, EngineeringItems.engineersScrewdriver, null));
	public static final Achievement achievementTest2 = reg(new Achievement("test2", "test2", 2, 1, EngineeringItems.engineersScrewdriverRusty, achievementTest1));
	
	public static final AchievementPage achievementPageEngineering = new AchievementPage("Engineering Mod", popAchievementArray());
	
	public static final void registerAchievements() {
		AchievementPage.registerAchievementPage(achievementPageEngineering);
	}
	
	private static ArrayList<Achievement> achievementList;
	private static final <T extends Achievement> T reg(T parAchievement) {
		if(achievementList == null) achievementList = new ArrayList<Achievement>(32);
		achievementList.add(parAchievement);
		return parAchievement;
	}
	private static Achievement[] popAchievementArray() {
		Achievement[] array = achievementList.toArray(new Achievement[achievementList.size()]);
		achievementList = null;
		return array;
	}
}
