import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

class SubarrZeroSum {
    public List<int[]> findSubarrays(int[] arr) {
        HashMap<Integer, List<Integer>> sumMap = new HashMap<>();
        List<int[]> result = new ArrayList<>();
        int sum = 0;
        
        sumMap.put(0, new ArrayList<>());
        
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            
            if (sumMap.containsKey(sum)) {
                for (Integer start : sumMap.get(sum)) {
                    int[] subarray = new int[i - start];
                    for (int j = start + 1; j <= i; j++) {
                        subarray[j - start - 1] = arr[j];
                    }
                    result.add(subarray);
                }
            }
            
            sumMap.putIfAbsent(sum, new ArrayList<>());
            sumMap.get(sum).add(i);
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {6, -1, 3, -2, -1, 4, -4};
        List<int[]> subarrays = solver.findSubarrays(arr);
        
        for (int[] subarray : subarrays) {
            for (int num : subarray) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
