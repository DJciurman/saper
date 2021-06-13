package code;

public class FatherOfGameTime
{
    protected int czas;

    public FatherOfGameTime(int czas)
    {
        this.czas = czas;
    }

    public int getCzas()
    {
        return czas;
    }

    public void setCzas(int czas)
    {
        this.czas = czas;
    }

    public void incrementCzas()
    {
        this.czas = this.czas + 2;
    }

    @Override
    public String toString()
    {
        String out = "";
        out = "" + czas;
        return out;
    }
}
