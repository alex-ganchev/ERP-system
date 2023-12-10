public enum Role {
    ADMIN("АДМИНИСТРАТОР"),
    EMPLOYEE("СЛУЖИТЕЛ");
    private String name;
    Role(String name){
       this.name = name;
    }
    public String getName() {
        return name;
    }
}

