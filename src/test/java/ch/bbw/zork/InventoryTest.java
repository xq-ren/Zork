package ch.bbw.zork;

import junit.framework.TestCase;

import java.util.ArrayList;

public class InventoryTest extends TestCase {
    private Inventory inventory = new Inventory(new ArrayList<>());

    public void testIsInInventory() {
        Item flower = new Item();
        flower.setName("rose");
        inventory.addToInv(flower);
        assertTrue(inventory.isInInventory(flower));
    }

    public void testIsInInventoryEmpty(){
        Item flower = new Item();
        flower.setName("rose");
        assertFalse(inventory.isInInventory(flower));
    }

    public void testRemoveFromInv() {
        Item flower = new Item();
        flower.setName("rose");
        inventory.removeFromInv(flower);
        assertFalse(inventory.removeFromInv(flower));
    }
}