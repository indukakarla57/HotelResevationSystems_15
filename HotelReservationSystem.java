import java.util.ArrayList;
import java.util.Scanner;

class Room {

    int roomNumber;
    String category;
    double price;
    boolean available;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    void displayRoom() {
        System.out.println(
            "Room No: " + roomNumber +
            " | Category: " + category +
            " | Price: ₹" + price +
            " | Available: " + available
        );
    }
}

class Reservation {

    String customerName;
    int roomNumber;
    String category;
    double amount;

    Reservation(String customerName, int roomNumber,
                String category, double amount) {

        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.amount = amount;
    }

    void displayReservation() {

        System.out.println("\n===== BOOKING DETAILS =====");
        System.out.println("Customer Name : " + customerName);
        System.out.println("Room Number   : " + roomNumber);
        System.out.println("Room Category : " + category);
        System.out.println("Amount Paid   : ₹" + amount);
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    // Display available rooms
    static void searchRooms() {

        System.out.println("\n===== AVAILABLE ROOMS =====");

        for (Room room : rooms) {

            if (room.available) {
                room.displayRoom();
            }
        }
    }

    // Book a room
    static void bookRoom() {

        searchRooms();

        System.out.print("\nEnter room number to book: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {

            if (room.roomNumber == roomNumber && room.available) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room is not available.");
            return;
        }

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.println("\n===== PAYMENT =====");
        System.out.println("Amount: ₹" + selectedRoom.price);

        System.out.print("Enter 1 to confirm payment: ");
        int payment = sc.nextInt();

        if (payment == 1) {

            selectedRoom.available = false;

            Reservation reservation = new Reservation(
                name,
                selectedRoom.roomNumber,
                selectedRoom.category,
                selectedRoom.price
            );

            reservations.add(reservation);

            System.out.println("\nPayment successful!");
            System.out.println("Room booked successfully!");

            reservation.displayReservation();

        } else {

            System.out.println("Payment cancelled.");
        }
    }

    // Cancel reservation
    static void cancelReservation() {

        System.out.print("\nEnter room number to cancel: ");
        int roomNumber = sc.nextInt();

        Reservation found = null;

        for (Reservation reservation : reservations) {

            if (reservation.roomNumber == roomNumber) {
                found = reservation;
                break;
            }
        }

        if (found == null) {

            System.out.println("No reservation found.");

        } else {

            for (Room room : rooms) {

                if (room.roomNumber == roomNumber) {
                    room.available = true;
                    break;
                }
            }

            reservations.remove(found);

            System.out.println("Reservation cancelled successfully.");
        }
    }

    // Display all reservations
    static void displayReservations() {

        if (reservations.isEmpty()) {

            System.out.println("\nNo reservations found.");

        } else {

            System.out.println("\n===== ALL RESERVATIONS =====");

            for (Reservation reservation : reservations) {
                reservation.displayReservation();
            }
        }
    }

    public static void main(String[] args) {

        // Adding rooms
        rooms.add(new Room(101, "Single", 1500));
        rooms.add(new Room(102, "Single", 1500));
        rooms.add(new Room(201, "Double", 2500));
        rooms.add(new Room(202, "Double", 2500));
        rooms.add(new Room(301, "Deluxe", 4000));

        while (true) {

            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    searchRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelReservation();
                    break;

                case 4:
                    displayReservations();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}