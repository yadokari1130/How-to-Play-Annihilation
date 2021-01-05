package yadokaris_How_to_Play_Annihilation.questgroups;

import yadokaris_How_to_Play_Annihilation.QuestGroup;
import yadokaris_How_to_Play_Annihilation.quests.CreateGoldenPickaxeQuest;
import yadokaris_How_to_Play_Annihilation.quests.GetGoldOresQuest;

public class PrepareInvisibleAttackQuestGroup extends QuestGroup {

	private static PrepareInvisibleAttackQuestGroup instance = new PrepareInvisibleAttackQuestGroup();

	private PrepareInvisibleAttackQuestGroup() {
		this.name = "透明凸の準備";

		registerQuest(GetGoldOresQuest.getInstance());
		registerQuest(CreateGoldenPickaxeQuest.getInstance());

		dependency.add(GetIronsQuestGroup.getInstance());
	}

	public static PrepareInvisibleAttackQuestGroup getInstance() {
		return instance;
	}
}
