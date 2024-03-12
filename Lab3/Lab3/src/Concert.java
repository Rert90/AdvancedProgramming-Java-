class Concert implements Visitable, Payable, Comparable<Concert> {
    private String name;
    private double ticketPrice;

    public Concert(String name, double ticketPrice) {
        this.name = name;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public void visit() {
        System.out.println("Attending the concert: " + name);
    }

    @Override
    public double getPrice() {
        return ticketPrice;
    }

    @Override
    public int compareTo(Concert other) {
        return this.name.compareTo(other.name);
    }
}
