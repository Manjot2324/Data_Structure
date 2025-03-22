import java.util.HashSet;

class GivenSum {
    public boolean hasPairWithSum(int[] arr, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(target - num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 8, 12};
        int target = 10;
        System.out.println(solver.hasPairWithSum(arr, target));
    }
}
