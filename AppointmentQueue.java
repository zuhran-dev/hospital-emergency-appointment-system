public class AppointmentQueue {
    private PatientNode front;
    private PatientNode rear;
    private int size;

    boolean isEmpty(){
        return front == null;
    }
    void enqueue(Patient p){
        PatientNode n = new PatientNode(p);
        if (rear == null){
            front = rear = n;
        }else {
            rear.next = n;
            rear = n;
        }
        size++;
    }
    void enqueueFront(Patient p ){
        PatientNode n = new PatientNode(p);
        n.next = front;
        front = n;
        if (rear == null){
            rear = n;
        }
        size ++;
    }
    Patient dequeue(){
        if (front == null){
            return null;
        }
        Patient p = front.data;
        front = front.next;
        if (front == null){
            rear = null;
        }
        size--;
        return p;
    }
    boolean remove(int id) {
        if (front == null) {
            return false;
        }
        if (front.data.id == id) {
            front = front.next;

            if (front == null) {
                rear = null;
            }
            size--;
            return true;
        }
        PatientNode cur = front;
        while (cur.next != null && cur.next.data.id != id){
            cur = cur.next;
        }
        if (cur.next == null){
            return false;
        }
        if (cur.next == rear){
            rear = cur;
        }
        cur.next = cur.next.next;
        size--;
        return true;
    }
    void display(){
        System.out.println("--- Appointment Queue (" + size + ") ---");
        if (front == null){
            System.out.println(" (empty) ");
            return;
        }
        PatientNode cur = front;
        while (cur != null){
            System.out.println(cur.data);
            cur = cur.next;
        }
    }
}
