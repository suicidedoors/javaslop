public class CoralCastle {

    private String name;
    private Cave[] caves;

    public CoralCastle(String name, int numberOfCaves) {
        this.name = name;
        this.caves = new Cave[numberOfCaves];
        int number = 101;
        for (int i = 0; i < numberOfCaves; i++){
            this.caves[i] = new Cave(number++, (i % 4) + 2);
        }
    }

    public Cave checkIn(String guestName, int guestSize) {
        Guest newGuest = new Guest(guestName, guestSize);
        for (Cave cave : this.caves) {
            if (newGuest.checkIn(cave)) {
                return cave;
            }
        }
        return null;
    }

    public boolean checkOut(String guestName) {
        for (Cave cave : this.caves){
            if (cave.getGuest() != null && cave.getGuest().getName().equals(guestName)){
                return cave.getGuest().checkOut();
            }
        }
        return false;
    }

    public Cave getCaveByGuestName(String guestName) {
        for (Cave cave : this.caves){
            if (cave.getGuest() != null && cave.getGuest().getName().equals(guestName)){
                return cave;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "Castle " + this.name;
        for (Cave cave : this.caves){
            result += "\n" + cave.toString();
        }
        return result;
    }
}
