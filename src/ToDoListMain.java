import java.util.*;

public class ToDoListMain {
    public static void main(String[] args) {
        HashMap<Integer, String> toDoList = new HashMap<>();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("What would you like to do?" + "\nadd items (a), delete items(d), show list(s), close program(c)");
            String choice = scan.next();
            if (choice.equalsIgnoreCase("a")) {
                System.out.println("How many items: ");
                String numItem = scan.next();

                if(isInt(numItem)){
                    int temp = 1;
                    while (temp <= Integer.parseInt(numItem)) {

                        System.out.println("Enter item " + temp);
                        String item = scan.next();
                        showMissingPriorities(toDoList);

                        System.out.println("Enter priority: ");
                        String priority = scan.next();

                        if(isInt(priority)) {
                            scan.nextLine();
                            while (Integer.parseInt(priority) < 1) {
                                System.out.println("Value must be an integer larger than 0 ");
                                showMissingPriorities(toDoList);
                                System.out.println("Enter priority: ");
                                priority = scan.next();
                            }
                            while (toDoList.containsKey(priority)) {
                                System.out.println("This priority is already used");
                                showMissingPriorities(toDoList);
                                System.out.println("Enter priority: ");
                                priority = scan.next();
                            }

                            toDoList.put(Integer.parseInt(priority), item);
                            temp++;
                        }


                    }
                }

            } else if( choice.equalsIgnoreCase("s")) {
                sortKey(toDoList);
                showMissingPriorities(toDoList);


            } else if (choice.equalsIgnoreCase("d")) {
                sortKey(toDoList);
                System.out.println("Which priority to delete?");
                int del = scan.nextInt();
                scan.nextLine();

                if (toDoList.containsKey(del)){
                    toDoList.remove(del);
                    showMissingPriorities(toDoList);
                } else {
                    System.out.println("The key doesn't exist");
                }
            } else if(choice.equalsIgnoreCase("c")) {
                break;
            }

            else {
                System.out.println("Not a valid input");
            }
        }
    }

    public static void sortKey(HashMap<Integer, String> map) {

        TreeMap<Integer, String> sortedMap = new TreeMap<>();
        sortedMap.putAll(map);

        for (Map.Entry<Integer, String> entry: sortedMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    public static boolean isInt(String input) {
        try{
            Integer.parseInt(input);
            return true;
        } catch (Exception ex) {
            System.out.println("Not a valid input");
            return false;
        }
    }

    public static void showMissingPriorities (HashMap<Integer, String> map ) {
        int maxPriority =0;

        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            if(entry.getKey() >= maxPriority) {
                maxPriority = entry.getKey();
            }
        }
        ArrayList missingNum = new ArrayList<Integer>() ;
        int counter = 0;
        for (int i = 1; i <= maxPriority; i++) {
            if(!map.containsKey(i)) {
                missingNum.add(i);
            }

        }
        System.out.print("Priorities missing: " + missingNum + "\n");

    }
}
