class CountingSort {
    public void countingSort(int[] ages) {
        int maxAge = 18;
        int minAge = 10;
        int range = maxAge - minAge + 1;
        int[] count = new int[range];
        int[] output = new int[ages.length];

        for (int i = 0; i < ages.length; i++) {
            count[ages[i] - minAge]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = ages.length - 1; i >= 0; i--) {
            output[count[ages[i] - minAge] - 1] = ages[i];
            count[ages[i] - minAge]--;
        }

        System.arraycopy(output, 0, ages, 0, ages.length);
    }

    public static void main(String[] args) {
        CountingSort sorter = new CountingSort();
        int[] studentAges = {15, 18, 10, 14, 17, 12, 16, 13, 11};

        System.out.println("Before Sorting:");
        for (int age : studentAges) {
            System.out.print(age + " ");
        }

        sorter.countingSort(studentAges);

        System.out.println("\nAfter Sorting:");
        for (int age : studentAges) {
            System.out.print(age + " ");
        }
    }
}
