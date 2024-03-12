import java.util.ArrayList;
import java.util.List;

abstract class Attraction implements Visitable, Payable {
    private String name;
    private String description;

    public Attraction(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
