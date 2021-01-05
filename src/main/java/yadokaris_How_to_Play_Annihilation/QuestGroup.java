package yadokaris_How_to_Play_Annihilation;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.MinecraftForge;

public class QuestGroup {

	protected String name;
	public List<Quest> quests = new ArrayList<>();
	protected List<QuestGroup> dependency = new ArrayList<>();
	protected boolean isCurrent;

	public String getName() {
		return name;
	}

	public boolean doClear() {
		return quests.stream().filter(q -> !q.doClear).count() == 0;
	}

	public void load() {
		quests.forEach(q -> {
			if (H2PA.status.containsKey(q.name)) {
				q.doClear = H2PA.status.get(q.name);
				if (q.doClear) q.currentValue = q.value;
			}
			else {
				H2PA.saveXML(q.name, false);
				H2PA.status.put(q.name, false);
			}
		});
	}

	public boolean canStart() {
		return dependency.stream().filter(g -> !g.doClear()).count() == 0;
	}

	public void registerQuest(Quest quest) {
		quests.add(quest);
		quest.parent = this;
	}

	public int getColor() {
		return isCurrent ? 0x00FF00 : 0xFFFFFF;
	}

	public void registerEvent() {
		quests.forEach(MinecraftForge.EVENT_BUS::register);
	}

	public void unregisterEvent() {
		quests.forEach(MinecraftForge.EVENT_BUS::unregister);
	}
}
