public class EmergencyQueue {
    private  PatientNode head;
    private int size;

    boolean isEmpty(){
        return head == null;
    }

    void enqueue(Patient p){
        PatientNode n = new PatientNode(p);
        if (head == null || p.severity > head.data.severity){
            n.next = head;
            head = n;
        }else {
            PatientNode cur = head;
            while (cur.next != null && cur.next.data.severity >= p.severity){
                cur = cur.next;
            }
            n.next = cur.next;
            cur.next = n;
        }
        size++;
    }
    void enqueueFront(Patient p){
        PatientNode n = new PatientNode(p);
        n.next = head;
        head = n;
        size++;
    }
    Patient dequeue(){
        if (head == null){
            return null;
        }
        Patient p = head.data;
        head = head.next;
        size--;
        return p;
    }
    boolean remove(int id){
        if (head == null){
            return false;
        }
        if (head.data.id == id){
            head = head.next;
            size--;
            return true;
        }
        PatientNode cur = head;
        while (cur.next != null && cur.next.data.id != id){
            cur = cur.next;
        }
        if (cur.next == null){
            return false;
        }
        cur.next = cur.next.next;
        size--;
        return true;
    }
    void display(){
        System.out.println(" --- Emergency Queue (" + size + ") ---");
        if (head == null){
            System.out.println("(empty)");
            return;
        }
        PatientNode cur = head;
        while (cur != null){
            System.out.println(cur.data);
            cur = cur.next;
        }
    }
}
