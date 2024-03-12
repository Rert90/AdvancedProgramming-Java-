class Statue implements Visitable, Comparable<Statue> {
    private String name;

    public Statue(String name) {
        this.name = name;
    }

    @Override
    public void visit() {
        System.out.println("Visiting the statue of " + name);
    }

    @Override
    public int compareTo(Statue other) {
        return this.name.compareTo(other.name);
    }
}
