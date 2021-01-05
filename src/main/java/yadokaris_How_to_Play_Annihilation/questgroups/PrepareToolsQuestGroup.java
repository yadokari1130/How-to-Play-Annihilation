package yadokaris_How_to_Play_Annihilation.questgroups;

import yadokaris_How_to_Play_Annihilation.QuestGroup;
import yadokaris_How_to_Play_Annihilation.quests.CreateArmorsQuest;
import yadokaris_How_to_Play_Annihilation.quests.CreateToolsQuest;

public class PrepareToolsQuestGroup extends QuestGroup{

	private static PrepareToolsQuestGroup instance = new PrepareToolsQuestGroup();

	private PrepareToolsQuestGroup() {
		this.name = "戦闘準備";

		registerQuest(CreateArmorsQuest.getInstance());
		registerQuest(CreateToolsQuest.getInstance());

		this.dependency.add(GetIronsQuestGroup.getInstance());
	}

	public static PrepareToolsQuestGroup getInstance() {
		return instance;
	}
}
