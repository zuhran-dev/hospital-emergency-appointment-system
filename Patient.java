public class Patient {
    int id;
    String name;
    int age;
    int severity;
    String type;
    String status;

    Patient(int id, String name, int age, int severity, String type){
        this.id = id;
        this.name = name;
        this.age = age;
        this.severity = severity;
        this.type = type;
        this.status = "WAITING";
    }
    public String toString(){
        return "ID" + " | " + name + " | Age: " + age + " | Severity: " + severity + " | " + type + " | " + status;
    }
}
