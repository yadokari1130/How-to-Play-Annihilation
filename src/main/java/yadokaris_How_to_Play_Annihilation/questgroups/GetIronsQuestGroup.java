package yadokaris_How_to_Play_Annihilation.questgroups;

import yadokaris_How_to_Play_Annihilation.QuestGroup;
import yadokaris_How_to_Play_Annihilation.quests.GetCoalsQuest;
import yadokaris_How_to_Play_Annihilation.quests.GetIronOresQuest;

public class GetIronsQuestGroup extends QuestGroup {

	private static GetIronsQuestGroup instance = new GetIronsQuestGroup();

	private GetIronsQuestGroup() {
		this.name = "素材の収集";

		registerQuest(GetCoalsQuest.getInstance());
		registerQuest(GetIronOresQuest.getInstance());
	}

	public static  GetIronsQuestGroup getInstance() {
		return instance;
	}

}
