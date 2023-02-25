import java.util.Scanner;

class PeaSoupAndPancakes {
    //
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int noOfRestaurants = sc.nextInt();

        for (int i = 0; i < noOfRestaurants; i++) {
            int noOfItems = sc.nextInt();
            sc.nextLine();
            String currentRestaurant = sc.nextLine();
            boolean peasoup = false;
            boolean pancakes = false;
            for (int j = 0; j < noOfItems; j++) {
                String currentItem = sc.nextLine();
                if (currentItem.equals("pea soup")) {
                    peasoup = true;
                } else if (currentItem.equals("pancakes")) {
                    pancakes = true;
                }
            }
            if (peasoup == true && pancakes == true) {
                System.out.println(currentRestaurant);
                return;
            }
        }
        System.out.println("Anywhere is fine I guess");
        sc.close();
    }
}