package com.dankmemer.scripts.autofisher.nodes;

import com.dankmemer.scripts.autofisher.DankFisher;
import com.dankmemer.scripts.dependencies.framework.Priority;
import com.dankmemer.scripts.dependencies.framework.Node;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;

public class FishNode extends Node {

    private DankFisher main;

    public FishNode(DankFisher main) {
        this.main = main;
    }

    @Override
    public boolean validate() {
        return !Inventory.isFull() && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {
        if(main.userSettings.getFishArea().getArea().contains(Players.getLocal())) {
            Npc spot = Npcs.getNearest(main.spotId);
            if(spot != null) {
                spot.interact(main.userSettings.getFish().getInteractAction());
                Time.sleepUntil(() -> Players.getLocal().isAnimating(), 5000);
            }
        } else {
            Movement.walkTo(main.userSettings.getFishArea().getWalkPosition());
        }
    }

    @Override
    public Priority priority() {
        return Priority.MEDIUM;
    }

    @Override
    public String getName() {
        return "Fishing...";
    }
}
