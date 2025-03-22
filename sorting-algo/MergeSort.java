class MergeSort {
    public void mergeSort(int[] prices) {
        if (prices.length > 1) {
            int mid = prices.length / 2;
            int[] left = new int[mid];
            int[] right = new int[prices.length - mid];

            System.arraycopy(prices, 0, left, 0, mid);
            System.arraycopy(prices, mid, right, 0, prices.length - mid);

            mergeSort(left);
            mergeSort(right);

            merge(prices, left, right);
        }
    }

    private void merge(int[] prices, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                prices[k] = left[i];
                i++;
            } else {
                prices[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            prices[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            prices[k] = right[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        MergeSort sorter = new MergeSort();
        int[] bookPrices = {299, 499, 129, 349, 199, 249};

        System.out.println("Before Sorting:");
        for (int price : bookPrices) {
            System.out.print(price + " ");
        }

        sorter.mergeSort(bookPrices);

        System.out.println("\nAfter Sorting:");
        for (int price : bookPrices) {
            System.out.print(price + " ");
        }
    }
}
