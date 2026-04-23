public class Staff {
    private String name;
    private String role;
    private String branchName;
    private String email;

    public Staff(String name, String role, String branchName, String email) {
        this.name = name;
        this.role = role;
        this.branchName = branchName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", branchName='" + branchName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}