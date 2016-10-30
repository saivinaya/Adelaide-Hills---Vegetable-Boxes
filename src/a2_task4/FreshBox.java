/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2_task4;

import java.util.*;
/**
 * To Create a FreshBox with four trays of fruits or vegetables randomly,
 * display the contents of the box and allow the user to substitute any one, 
 * add brochures only if the FreshBox contains duplicate items.
 * @author VinayaSaiD
 */

public class FreshBox {
    
   private String[] itemsList = {"Banana","Apple","Cauliflower","Potato","Capsicum"};
   private String[] brochureList = {"Banana Milkshake Recipe","Apple Pie Recipe","Cauliflower Curry Recipe","Potato Mash Recipe","Capisum Masala Recipe"};
   public static String[] box= new String[4];
   
   //default constructor
   public FreshBox(){
       
   }
   public FreshBox( String[] itemsPresent, String[] customerSelection, String[] brochurePresent)
   {
      itemsList = itemsPresent;
      brochureList = brochurePresent;
      box = customerSelection;

   } // end FreshBox constructor
   
   // get the items available in the store
   public String[] getItems()
   {
      return itemsList;
   } // end method getItems
   
   // change items in the store
   public void addItems( String[] itemsPresent )
   {
      itemsList = itemsPresent;
      
   } // end method addItems
   
   // get the brochures available in the store
   public String[] getBrochure()
   {
      return brochureList;
   } // end method getBrochure

   // change the bronchures in the store
   public void addBrochure( String[] addBrochure )
   {
      brochureList = addBrochure;
      
   } // end method addBrochure
   
   // set the items in the customer box
   public void setselectedItems( String[] customerSelection )
   {
      box = customerSelection;
   } // end method setselectedItems
   
   // get the selected items in the customer box
   public void toBoxString(String[] box)
   {
       System.out.println("Items in box are : "+ Arrays.toString(box));
   }
   
   // generate the fresh box for the customer randomly
   public static void main(String[] args) 
   {
       Random generator = new Random();
       FreshBox newFreshBox = new FreshBox();
       String[] itemList = newFreshBox.itemsList;
       // generating random trays for the customer
       box[0] = itemList[generator.nextInt(itemList.length)];
       box[1] = itemList[generator.nextInt(itemList.length)];
       box[2] = itemList[generator.nextInt(itemList.length)];
       box[3] = itemList[generator.nextInt(itemList.length)];
       newFreshBox.toBoxString(box);
       newFreshBox.getDecision();
   }
   
   // ask the customer if he wants to swap any item
   public void getDecision(){
       Scanner input = new Scanner(System.in);
       System.out.print("Do you want to change the contents of the tray(Y/N):");
       // get the choice from the customer whether to change one Tray - y or n
       String choice= input.next();
       //System.out.println(choice=="y");
       // go to chooseTray if the customer choose to swap
       if (choice.equals("Y") || choice.equals("y"))
       {
            chooseTray();
       }
       // else print the contents of the box and add brochures if any that needs to be added
       else
       {
            toBoxString(box);
            String[] brochure = getCustomerBrochure();
            printBrochure(brochure);  
       } 
       
    }
   
   //get the postion that needs to be changed from customer and ask him to choose new one from the stores itemslist and replace it
   public void chooseTray()
   {
       Scanner input = new Scanner(System.in);
       FreshBox newFreshBox = new FreshBox();
       System.out.print("Please enter the tray position you want to replace (positions are 0,1,2,3): ");
       // get the position in the customer box which he/ she wants to change
       int replacePosition = input.nextInt();
       System.out.println("Available vegetables/Fruits are:");
       // display the items available in the store
       String[] itemsInStore = newFreshBox.getItems();
       System.out.println(Arrays.toString(itemsInStore));
       System.out.print("Please enter the position of vegetable/Fruit you want (positions being 0,1,2,3,4): ");
       // get the choice of customer which he wants to add to the box
       int selectedItemPosition = input.nextInt();
       // replace the item with the selected one
       box[replacePosition] = itemsInStore[selectedItemPosition];
       // print the new custoner box after the change
       newFreshBox.toBoxString(box);
       
       String[] brochure = getCustomerBrochure();
       //go to print the brochure if any added
       printBrochure(brochure);
   }
   
    // print the brochures if any were added
    public void printBrochure(String[] brochure){
       //we can have maximum of 2 brochures as only max of 2 duplicates can occur in a list of 4 items
       if (brochure[0]!= "" && brochure[1]!="")
        System.out.println("Brochure: " + Arrays.asList(brochure));
       else if (brochure[0]!= "" && brochure[1]=="")
        System.out.println("Brochure: " + brochure[0]); 
       else if (brochure[0]== "" && brochure[1]!="")
        System.out.println("Brochure: " + brochure[1]); 
       else
        System.out.println();   
    }
   
   // check for duplicates in the customer box and add brochures if present    
   public String[] getCustomerBrochure(){
       int[] repeatedTrays = {0,0,0,0,0};
       //we can have maximum of 2 brochures as only max of 2 duplicates can occur in a list of 4 items
       String[] brochure = {"",""};
       // checking for duplicates using repeatedTrays array by maintaning a count of times the item is in the customers box
       for(int i = 0; i < 5; i++)
       {   
           for(int j = 0; j < 4 ; j++)
            { if(itemsList[i] == box[j])
              {   repeatedTrays[i] = repeatedTrays[i]+1;
              }
            }
        }
       int brochureCount = 0;
       for (int i=0;i<5;i++){
           // if the number is >1 means there are dupllicates for that item, so add a brochure
           if (repeatedTrays[i] >1){
               brochure[brochureCount] = brochureList[i];
               brochureCount = brochureCount+1;
           }
       }
       return brochure;
   }
}


