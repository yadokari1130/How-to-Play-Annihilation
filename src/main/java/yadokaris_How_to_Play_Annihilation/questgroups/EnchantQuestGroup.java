package yadokaris_How_to_Play_Annihilation.questgroups;

import yadokaris_How_to_Play_Annihilation.QuestGroup;
import yadokaris_How_to_Play_Annihilation.quests.EnchantArmorsQuest;
import yadokaris_How_to_Play_Annihilation.quests.EnchantToolsQuest;
import yadokaris_How_to_Play_Annihilation.quests.GetXPQuest;

public class EnchantQuestGroup extends QuestGroup {

	private static EnchantQuestGroup instance = new EnchantQuestGroup();

	private EnchantQuestGroup() {
		this.name = "道具の強化";

		registerQuest(GetXPQuest.getInstance());
		registerQuest(EnchantToolsQuest.getInstance());
		registerQuest(EnchantArmorsQuest.getInstance());

		dependency.add(PrepareToolsQuestGroup.getInstance());
	}

	public static EnchantQuestGroup getInstance() {
		return instance;
	}
}
