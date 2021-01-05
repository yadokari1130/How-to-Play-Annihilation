package yadokaris_How_to_Play_Annihilation.questgroups;

import yadokaris_How_to_Play_Annihilation.QuestGroup;
import yadokaris_How_to_Play_Annihilation.quests.BuyBrewingStandQuest;
import yadokaris_How_to_Play_Annihilation.quests.BuyMaterialsQuest;

public class BuyInvisiblePotionMaterialsQuestGroup extends QuestGroup {

	private static BuyInvisiblePotionMaterialsQuestGroup instance = new BuyInvisiblePotionMaterialsQuestGroup();

	private BuyInvisiblePotionMaterialsQuestGroup() {
		this.name = "ポーション素材の購入";

		registerQuest(BuyBrewingStandQuest.getInstance());
		registerQuest(BuyMaterialsQuest.getInstance());

		dependency.add(PrepareInvisibleAttackQuestGroup.getInstance());
	}

	public static BuyInvisiblePotionMaterialsQuestGroup getInstance() {
		return instance;
	}
}
