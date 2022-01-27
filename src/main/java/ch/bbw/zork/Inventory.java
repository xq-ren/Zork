package ch.bbw.zork;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {

    private ArrayList<Item> inventory;

    public Inventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public boolean isInInventory(Item anItem) {
        for (int i = 0; i < inventory.size();) {
            if(inventory.get(i).equals(anItem)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public void addToInv(Item item) {
        inventory.add(item);
    }

    public void removeFromInv(Item item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (item.getName().equals(inventory.get(i).getName()) && item.getWeight() == (inventory.get(i).getWeight())) {
                inventory.remove(i);
            }
        }
    }

    public void getInventory(){
        Iterator<Item> iter = inventory.iterator();

        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }
}