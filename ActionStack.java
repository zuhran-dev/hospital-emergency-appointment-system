public class ActionStack {
    static class Action{
        String type;
        Patient patient;
        Action next;

        Action(String type, Patient patient){
            this.type = type;
            this.patient = patient;
        }
    }
    private Action top;

    boolean isEmpty(){
        return top == null;
    }
    void push(String type, Patient p){
        Action a = new Action(type,p);
        a.next = top;
        top = a;
    }

    Action pop(){
        if (top == null){
            return null;
        }
        Action a = top;
        top = top.next;
        return a;
    }
}
