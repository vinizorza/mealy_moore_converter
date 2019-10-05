package entity;

public enum MachineType {

    MEALY("mealy"), MOORE("moore");

    private String type;

    MachineType(String type) {
        this.type = type;
    }
}
