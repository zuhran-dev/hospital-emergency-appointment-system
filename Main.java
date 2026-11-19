import  java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static EmergencyQueue emergency = new EmergencyQueue();
    static AppointmentQueue appointment = new AppointmentQueue();
    static ActionStack undoStack = new ActionStack();
    static PatientRecord records = new PatientRecord();
    static int nextId = 1;

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = readInt("Enter choice");
            switch (choice){
                case 1:
                    addPatient("emergency");
                    break;
                case 2:
                    addPatient("appointment");
                    break;
                case 3:
                    treatNext();
                    break;
                case 4:
                    emergency.display();
                    appointment.display();
                    break;
                case 5:
                    undoLast();
                    break;
                case 6:
                    records.display();
                    break;
                case 7:
                    System.out.println("Good bye");
                    break;
                default:
                    System.out.println("invalid choice");
            }
        }while (choice != 7);


    }

    static void showMenu(){
        System.out.println("\n==== Hospital Emergency & Appointment System ====");
        System.out.println("1. Add emergency patient");
        System.out.println("2. Book Appointment");
        System.out.println("3. treat next patient");
        System.out.println("4. Show waiting lists");
        System.out.println("5. Undo last action");
        System.out.println("6. Show all records");
        System.out.println("7. Exit");
    }

    static int readInt(String msg){
        while (true){
            System.out.println(msg);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            }catch (NumberFormatException e){
                System.out.println("Please enter a number,");
            }
        }
    }

    static void addPatient(String type){
        System.out.println("Name: ");
        String name = sc.nextLine();
        int age = readInt("Age: ");
        int severity = 1;

        if (type.equals("Emergency")){
            severity = readInt("Severity (1-5, 5 = critical): ");
            while (severity < 1 || severity > 5){
                severity = readInt("Enter severity between 1 and 5: ");
            }
        }
        Patient p = new Patient(nextId++, name , age, severity, type);
        records.add(p);
        if (type.equals("EMERGENCY")) {
            emergency.enqueue(p);
        }else{
            appointment.enqueue(p);
            undoStack.push("Add",p);
            System.out.println("Added: " + p);
        }

    }
    static void treatNext(){
        Patient p;
        if (!emergency.isEmpty()) {
            p = emergency.dequeue();
        } else if (!appointment.isEmpty()) {
            p = appointment.dequeue();
        }
        else {
            System.out.println("No patients waiting.");
            return;
        }
        p.status = "TREATS";
        undoStack.push("TREAT",p);
        System.out.println("Treating: " + p);
        }
        static void undoLast(){
        ActionStack.Action a = undoStack.pop();
        if (a == null){
            System.out.println("Nothing to undo.");
            return;
        }
        Patient p = a.patient;
        if (a.type.equals("ADD")){
            if (p.type.equals("EMERGENCY")){
                emergency.remove(p.id);
            }
            else {
                appointment.remove(p.id);
                p.status = "CANCELLED";
                System.out.println("Undo: removed" + p.name);
            }
        }
        else {
            p.status = "WAITING";
            if (p.type.equals("EMERGENCY")){
                emergency.enqueueFront(p);
            }else{
                appointment.enqueueFront(p);
                System.out.println("undo:" + p.name + "is back in the queue");
            }
        }

        }

        }
