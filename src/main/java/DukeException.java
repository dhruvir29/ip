public class DukeException extends Exception {
    private String message;
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    public void printMessage() {
        System.out.println(this.message);
    }
}