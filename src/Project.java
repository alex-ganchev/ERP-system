public class Project {
    private String name;

    public String getName() {
        return name;
    }

    public Project(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
