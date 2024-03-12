class Church implements Visitable, Payable, Comparable<Church> {
    private String name;
    private double entranceFee;

    public Church(String name, double entranceFee) {
        this.name = name;
        this.entranceFee = entranceFee;
    }

    @Override
    public void visit() {
        System.out.println("Visiting the church of " + name);
    }

    @Override
    public double getPrice() {
        return entranceFee;
    }

    @Override
    public int compareTo(Church other) {
        return this.name.compareTo(other.name);
    }
}
