package ch.bbw.zork;

import junit.framework.TestCase;

public class RoomTest extends TestCase {
    private Room room = new Room(new String());

    public void testAdd() {
        Item axe = new Item();
        room.add(axe);
        assertFalse(room.add(axe));
    }
}