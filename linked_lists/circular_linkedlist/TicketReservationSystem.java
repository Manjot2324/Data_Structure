import java.util.*;

class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    Ticket head;
    Ticket tail;
    int totalTickets;

    public TicketReservationSystem() {
        head = null;
        tail = null;
        totalTickets = 0;
    }

    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            newTicket.next = head;
            tail = newTicket;
        }
        totalTickets++;
    }

    public void removeTicketByID(int ticketID) {
        if (head == null) return;

        if (head.ticketID == ticketID) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            totalTickets--;
            return;
        }

        Ticket current = head;
        while (current.next != head) {
            if (current.next.ticketID == ticketID) {
                current.next = current.next.next;
                if (current.next == head) {
                    tail = current;
                }
                totalTickets--;
                return;
            }
            current = current.next;
        }
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketID + ", Customer Name: " + current.customerName +
                    ", Movie Name: " + current.movieName + ", Seat Number: " + current.seatNumber +
                    ", Booking Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    public Ticket searchTicketByCustomerName(String customerName) {
        if (head == null) return null;

        Ticket current = head;
        do {
            if (current.customerName.equalsIgnoreCase(customerName)) {
                return current;
            }
            current = current.next;
        } while (current != head);
        return null;
    }

    public Ticket searchTicketByMovieName(String movieName) {
        if (head == null) return null;

        Ticket current = head;
        do {
            if (current.movieName.equalsIgnoreCase(movieName)) {
                return current;
            }
            current = current.next;
        } while (current != head);
        return null;
    }

    public int getTotalBookedTickets() {
        return totalTickets;
    }
}

public class Main {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        system.addTicket(1, "John Doe", "Avatar 2", "A1", "2025-03-15 10:00");
        system.addTicket(2, "Jane Smith", "Titanic", "B2", "2025-03-15 12:00");
        system.addTicket(3, "Sam Wilson", "Avengers: Endgame", "C3", "2025-03-15 14:00");
        system.addTicket(4, "Alice Johnson", "Avatar 2", "D4", "2025-03-15 16:00");

        System.out.println("All Ticket Reservations:");
        system.displayTickets();

        System.out.println("\nSearch for a ticket by Customer Name (John Doe):");
        Ticket foundTicket = system.searchTicketByCustomerName("John Doe");
        if (foundTicket != null) {
            System.out.println("Found Ticket: " + foundTicket.ticketID + ", " + foundTicket.movieName + ", " + foundTicket.seatNumber);
        } else {
            System.out.println("No ticket found for the specified customer.");
        }

        System.out.println("\nSearch for a ticket by Movie Name (Avatar 2):");
        foundTicket = system.searchTicketByMovieName("Avatar 2");
        if (foundTicket != null) {
            System.out.println("Found Ticket: " + foundTicket.ticketID + ", " + foundTicket.customerName + ", " + foundTicket.seatNumber);
        } else {
            System.out.println("No ticket found for the specified movie.");
        }

        System.out.println("\nTotal number of booked tickets: " + system.getTotalBookedTickets());

        System.out.println("\nRemoving ticket with Ticket ID 2:");
        system.removeTicketByID(2);
        system.displayTickets();

        System.out.println("\nTotal number of booked tickets after removal: " + system.getTotalBookedTickets());
    }
}
