class InsertionSort {
    public void sort(int[] employeeIds) {
        int n = employeeIds.length;
        for (int i = 1; i < n; i++) {
            int key = employeeIds[i];
            int j = i - 1;
            while (j >= 0 && employeeIds[j] > key) {
                employeeIds[j + 1] = employeeIds[j];
                j--;
            }
            employeeIds[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        InsertionSort sorter = new InsertionSort();
        int[] employeeIds = {101, 305, 202, 401, 303, 506};
        
        System.out.println("Before Sorting:");
        for (int id : employeeIds) {
            System.out.print(id + " ");
        }

        sorter.sort(employeeIds);

        System.out.println("\nAfter Sorting:");
        for (int id : employeeIds) {
            System.out.print(id + " ");
        }
    }
}
