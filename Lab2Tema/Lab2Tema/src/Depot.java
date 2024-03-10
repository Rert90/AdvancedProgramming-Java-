public class Depot {
    private String id;
    public Depot(String id){
        this.id=id;
    }

    public String getId() {
        return id;
    }
    @Override
    public boolean equals(Object o){
    if(this==o) return true;
    if(o==null||getClass()!=o.getClass()) return false;
    Depot depot=(Depot) o;
    return id.equals(depot.id);
    }
}
