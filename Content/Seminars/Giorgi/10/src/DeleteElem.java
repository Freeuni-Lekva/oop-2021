public class DeleteElem {
    private StudentModel model;
    private int id;

    public DeleteElem(StudentModel model, int id) {
        this.model = model;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public StudentModel getModel() {
        return model;
    }
}
