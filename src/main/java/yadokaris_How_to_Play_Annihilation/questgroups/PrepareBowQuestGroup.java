package yadokaris_How_to_Play_Annihilation.questgroups;

import yadokaris_How_to_Play_Annihilation.QuestGroup;
import yadokaris_How_to_Play_Annihilation.quests.CreateArrowsQuest;
import yadokaris_How_to_Play_Annihilation.quests.CreateBowQuest;
import yadokaris_How_to_Play_Annihilation.quests.GetBowMaterialsQuest;

public class PrepareBowQuestGroup extends QuestGroup{

	private static PrepareBowQuestGroup instance = new PrepareBowQuestGroup();

	private PrepareBowQuestGroup() {
		this.name = "弓矢の作成";

		registerQuest(GetBowMaterialsQuest.getInstance());
		registerQuest(CreateBowQuest.getInstance());
		registerQuest(CreateArrowsQuest.getInstance());

		this.dependency.add(GetIronsQuestGroup.getInstance());
	}

	public static PrepareBowQuestGroup getInstance() {
		return instance;
	}
}
