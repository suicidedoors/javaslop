public class Guest {

    private String name;
    private int size;
    private Cave cave;

    public Guest(String name, int size) {
        // TODO: assign the parameters to the fields above
    }

    public String getName() {
        // TODO: return the name
    }

    public boolean checkIn(Cave newCave) {
        // TODO: only succeed if this.cave == null AND newCave.isFree()
        //       AND newCave.getCapacity() >= this.size
        // on success: set this.cave, return true
        // otherwise: return false, leave things unchanged
    }

    public boolean checkOut() {
        // TODO: if this.cave != null, clear it and return true
        //       otherwise return false
    }

    @Override
    public String toString() {
        // TODO: return "Guest[name=X, size=Y]"
    }
}
