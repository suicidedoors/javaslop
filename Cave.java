public class Cave {

    private int number;
    private int capacity;
    private Guest guest;

    public Cave(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
    }

    public int getNumber() {
        return this.number;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public Guest getGuest() {
        return this.guest;
    }

    public boolean isFree() {
        if (this.guest == null){
            return true;
        }
        return false;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public String toString() {
        if (this.isFree()){
            return "Cave[" + this.number + ", capacity=" + this.capacity + ", free]";
        }
        return "Cave[" + this.number + ", capacity=" + this.capacity + ", guest=" + guest.toString() + ']';
    }
}
