import java.util.*;

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    Date bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, Date bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head;

    public TicketReservationSystem() {
        head = null;
    }

    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, Date bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            newTicket.next = head;
        } else {
            Ticket temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
    }

    public void removeTicket(int ticketId) {
        if (head == null) return;

        Ticket temp = head;
        Ticket prev = null;
        do {
            if (temp.ticketId == ticketId) {
                if (prev == null) {
                    // Remove head
                    if (head.next == head) {
                        head = null;
                    } else {
                        prev = head;
                        while (prev.next != head) {
                            prev = prev.next;
                        }
                        head = head.next;
                        prev.next = head;
                    }
                } else {
                    prev.next = temp.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets to display.");
            return;
        }

        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Booking Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public int countTickets() {
        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}


public class TicketReservationApp {
    public static void main(String[] args) {
        TicketReservationSystem ticketSystem = new TicketReservationSystem();
        ticketSystem.addTicket(1, "John Doe", "Avatar", "A1", new Date());
        ticketSystem.addTicket(2, "Jane Doe", "Titanic", "B1", new Date());
        ticketSystem.displayTickets();
        System.out.println("Total tickets: " + ticketSystem.countTickets());
        ticketSystem.removeTicket(1);
        ticketSystem.displayTickets();
    }
}