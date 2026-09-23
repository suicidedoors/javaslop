public class Guest {

    private String name;
    private int size;
    private Cave cave;

    public Guest(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public boolean checkIn(Cave newCave) {
        if (this.cave == null && newCave.isFree() && newCave.getCapacity() >= this.size){
            this.cave = newCave;
            newCave.setGuest(this);
            return true;
        }
        return false;
    }

    public boolean checkOut() {
        if (this.cave != null){
            Cave oldCave = this.cave;
            this.cave = null;
            oldCave.setGuest(null);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Guest[name=" + this.name + ", size=" + this.size + ']';
    }
}
