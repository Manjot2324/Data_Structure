class SelectionSort {
    public void sort(int[] scores) {
        int n = scores.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (scores[j] < scores[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = scores[minIndex];
            scores[minIndex] = scores[i];
            scores[i] = temp;
        }
    }

    public static void main(String[] args) {
        SelectionSort sorter = new SelectionSort();
        int[] examScores = {85, 92, 75, 68, 88, 79};

        System.out.println("Before Sorting:");
        for (int score : examScores) {
            System.out.print(score + " ");
        }

        sorter.sort(examScores);

        System.out.println("\nAfter Sorting:");
        for (int score : examScores) {
            System.out.print(score + " ");
        }
    }
}
