package yadokaris_How_to_Play_Annihilation.questgroups;

import yadokaris_How_to_Play_Annihilation.QuestGroup;
import yadokaris_How_to_Play_Annihilation.quests.GetMelonsQuest;
import yadokaris_How_to_Play_Annihilation.quests.GetLogsQuest;

public class GetInfinateResourcesQuestGroup extends QuestGroup {

	private static GetInfinateResourcesQuestGroup instance = new GetInfinateResourcesQuestGroup();

	private GetInfinateResourcesQuestGroup() {
		this.name = "無限資源の収集";

		registerQuest(GetMelonsQuest.getInstance());
		registerQuest(GetLogsQuest.getInstance());
	}

	public static  GetInfinateResourcesQuestGroup getInstance() {
		return instance;
	}

}
