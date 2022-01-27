package ch.bbw.zork;

import junit.framework.TestCase;

public class ItemTest extends TestCase {
    private Item item = new Item();

    public void testTestGetName() {
        item.getName();
        item.setName("hammer");
    }
}