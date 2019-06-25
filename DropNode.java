package dankwoodcutter.nodes;


import dankwoodcutter.data.AxeType;
import dependencies.framework.Node;
import dependencies.framework.Priority;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;

public class DropNode extends Node {


    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public void execute() {

        dropAllExcept(AxeType.names());
    }

    @Override
    public Priority priority() {
        return Priority.HIGH;
    }

    @Override
    public String getName() {
        return "Dropping...";
    }

    private void dropAllExcept(String... itemNames) {
        for (int i = 0; i < 28; i++) {
            Item item = Inventory.getItemAt(i);
            if (item != null) {
                boolean drop = true;
                for (String itemName : itemNames) {
                    if (item.getDefinition().getName().equals(itemName)) {
                        drop = false;
                    }
                }
                if (drop) {
                    if (item.interact("Drop"))
                        Time.sleep(150, 500);
                    else
                        i--;
                }
            }
        }
    }
}
