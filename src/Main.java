import java.util.ArrayList;

public class Main {
    static ArrayList<Integer> min = new ArrayList<>();
    static ArrayList<Integer> max = new ArrayList<>();


    public static void main(String[] args) {
        int[] a = {20, 30, 40, 10, 5, 80, 100, 60};
        int[] b = {20, 5, 15, 35, 10, 50, 80, 40};
        //
        // findMinMax(a);
        feedMinMaxLists(b);
        System.out.println(getProfit(b));

    }


    // The list contains all the indexes of buying and sellings in order(buy-sell-buy....sell)
    private static ArrayList<Integer> getIndexList() {
        ArrayList<Integer> mergedList = new ArrayList<>();
        int len = min.size() + max.size();
        for (int i = 0; i < len / 2; i++) {
            mergedList.add(min.get(i));
            mergedList.add(max.get(i));
        }

        return mergedList;
    }

    // calculate Profit via Buying lots in Local min and selling lots in Local max
    public static int getProfit(int nums[]) {
        // first buy and first sell and find profit;
        int lot = 0;
        int first_buy = nums[min.get(0)];
        buy(lot);
        int first_sell = nums[max.get(0)];
        sell(lot);
        int profit = first_sell - first_buy;

        ArrayList<Integer> ListOfBuyOrSell = getIndexList();
        int index;
        for (int i = 2; i < ListOfBuyOrSell.size(); i++) {
            index = ListOfBuyOrSell.get(i);
            if (isMin(i)) {
                lot = profit / nums[index];

            } else {
                profit = lot * nums[index];
                sell(lot);
            }
        }
        return profit;
    }

    // function for selling first lot
    private static void sell(int lot) {
        lot = 0;
    }
    //function for buying first lot
    private static void buy(int lot) {
        lot++;
    }

    // check the number is min
    private static boolean isMin(int i) {
        return i % 2 == 0;
    }

    //finding local min and max
    public static void feedMinMaxLists(int[] nums) {
        int len = nums.length;

        if (nums[0] < nums[1]) {
            min.add(0);
        }


        for (int i = 1; i < len - 1; i++) {
            if (nums[i + 1] > nums[i] && nums[i - 1] > nums[i]) {
                min.add(i);
            } else if (nums[i + 1] < nums[i] && nums[i - 1] < nums[i]) {
                max.add(i);
            }
        }

        if (nums[len - 1] > nums[len - 2])
            max.add(len - 1);
        else if (nums[len - 1] < nums[len - 2])
            min.add(len - 1);

    }


}
