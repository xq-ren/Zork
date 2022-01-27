package ch.bbw.zork;

import java.util.ArrayList;

public class Item {
	private String name;
	private String description;
	private float weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}


	public void removeItem(Item item, ArrayList<Item> roomItems) {
      for(int i=0;i<roomItems.size();i++) {
             if(roomItems.get(i).equals(item))
                   roomItems.remove(i);
      }
    }

	public Item stringToItem(String aString) {
      Item item;
      if(!isItem(aString)) {
             return null;
      }else {
             item = new Item();
             return item;
      }
    }

    public boolean isItem(String aString) {
      if(aString.equals(name))
             return true;
      return false;
    }

}
