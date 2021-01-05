package yadokaris_How_to_Play_Annihilation.questgroups;

import yadokaris_How_to_Play_Annihilation.QuestGroup;
import yadokaris_How_to_Play_Annihilation.quests.BrewInvisiblePotionQuest;

public class BrewInvisiblePotionQuestGroup extends QuestGroup {

	private static BrewInvisiblePotionQuestGroup instance = new BrewInvisiblePotionQuestGroup();

	private BrewInvisiblePotionQuestGroup() {
		this.name = "透明ポーションの醸造";

		registerQuest(BrewInvisiblePotionQuest.getInstance());

		dependency.add(BuyInvisiblePotionMaterialsQuestGroup.getInstance());
	}

	public static BrewInvisiblePotionQuestGroup getInstance() {
		return instance;
	}
}
