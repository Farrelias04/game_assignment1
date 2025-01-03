package org.uob.a1;

public class Inventory {
    final int MAX_ITEMS = 10;
    private String[] items; 
    private int size;

    public Inventory() {
        items = new String[MAX_ITEMS];
        size = 0;
    }

    public void addItem(String item) {
        if (size < MAX_ITEMS) {
            items[size] = item;
            size++;
        }
    }

    public int hasItem(String item) {
        for(int i = 0; i < size; i++) {
            if(items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public void removeItem(String item) {
        int position = this.hasItem(item);
         if(position > -1) {
             for(int i = position; i < size - 1; i++) {
                 items[i] = items[i+1];
             }
             size--;
            }
    }

    public String displayInventory() {
        String output = "";
        for(int i = 0; i < size; i++) {
            output += this.items[i] + " ";
        }
        return output;
    }
}