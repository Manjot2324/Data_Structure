class CircularTour {
    public int tour(int[] petrol, int[] distance) {
        int start = 0;
        int deficit = 0;
        int surplus = 0;
        
        for (int i = 0; i < petrol.length; i++) {
            surplus += petrol[i] - distance[i];
            if (surplus < 0) {
                deficit += surplus;
                surplus = 0;
                start = i + 1; // Try the next petrol pump
            }
        }
        
        if (surplus + deficit >= 0) {
            return start; // Return the starting point
        } else {
            return -1; // Not possible to complete the tour
        }
    }

    public static void main(String[] args) {
        CircularTour tour = new CircularTour();
        int[] petrol = {4, 6, 7, 4};
        int[] distance = {6, 5, 3, 5};

        int start = tour.tour(petrol, distance);
        if (start != -1) {
            System.out.println("Start at pump " + start);
        } else {
            System.out.println("Tour is not possible");
        }
    }
}
