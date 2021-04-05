import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    int orderPrice = 0;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = getCurrentTime();
        return !openingTime.isBefore(currentTime) || !closingTime.isAfter(currentTime);

    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return Collections
                .unmodifiableList(menu);
    }

    public List <Item> MenuItemSelected = new ArrayList<>();

    public void selectedItemCountAndOrderPrice(String itemName, int price) {

        Item newlySelectedItem = new Item(itemName, price);
        MenuItemSelected.add(newlySelectedItem);
        System.out.println("From Menu you have selected Item : "+newlySelectedItem);

        if (MenuItemSelected.size() == 0)
        {
            System.out.println("Nothing selected , hence current order Price is : "+orderPrice);
        }
        else {
            orderPrice += price;
            System.out.println("Due to Selection of item now Current order Price is : "+orderPrice);
        }
    }




    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }


    public String getName() {
        return name;
    }

}
