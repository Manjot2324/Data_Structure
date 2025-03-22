class QuickSort {
    public void quickSort(int[] prices, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(prices, low, high);
            quickSort(prices, low, pivotIndex - 1);
            quickSort(prices, pivotIndex + 1, high);
        }
    }

    private int partition(int[] prices, int low, int high) {
        int pivot = prices[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (prices[j] <= pivot) {
                i++;
                swap(prices, i, j);
            }
        }
        swap(prices, i + 1, high);
        return i + 1;
    }

    private void swap(int[] prices, int i, int j) {
        int temp = prices[i];
        prices[i] = prices[j];
        prices[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();
        int[] productPrices = {199, 299, 129, 399, 149, 249};

        System.out.println("Before Sorting:");
        for (int price : productPrices) {
            System.out.print(price + " ");
        }

        sorter.quickSort(productPrices, 0, productPrices.length - 1);

        System.out.println("\nAfter Sorting:");
        for (int price : productPrices) {
            System.out.print(price + " ");
        }
    }
}
