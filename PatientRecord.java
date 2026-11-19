public class PatientRecord {
    private PatientNode head;
    private PatientNode tail;

    void add(Patient p){
        PatientNode n = new PatientNode(p);
        if (head == null){
            head = tail = n;
        }else {
            tail.next = n;
            tail = n;
        }
    }
    Patient find(int id){
        PatientNode cur = head;
        while (cur != null){
            if (cur.data.id == id){
                return cur.data;
            }
            cur = cur.next;
        }
        return null;
    }
    void display(){
        System.out.println("--- All Patient Records ---");
        if (head == null){
            System.out.println(" (no record) ");
            return;
        }
        PatientNode cur = head;
        while (cur != null){
            System.out.println(cur.data);
            cur = cur.next;
        }
    }
}
