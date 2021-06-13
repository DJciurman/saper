package code;

/**
 * Klasa odpowiadająca za działanie stopera
 * (razem z klasą FatherOfGameTime przedstawiają polimorfizm)
 */
public class GameTime extends FatherOfGameTime {
    public GameTime(int czas) {
        super(czas);
    }

    @Override
    public void incrementCzas() {
        this.czas++;
    }

    @Override
    public String toString() {
        String out = "";
        int hours, minutes, seconds;
        hours = czas / 3600;
        if (hours < 10) {
            out += "0";
        }
        out += "" + hours + ":";
        minutes = (czas % 3600) / 60;
        if (minutes < 10) {
            out += "0";
        }
        out += "" + minutes + ":";
        seconds = czas % 60;
        if (seconds < 10) {
            out += "0";
        }
        out += "" + seconds;
        return out;
    }
}
