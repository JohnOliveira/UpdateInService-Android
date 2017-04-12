package "com.YOUR_PACKAGE.updateinbg";

public class TimerHandler
{
    private double ElapsedSeconds=10;
    private long begin;
    public boolean started=false;

    public TimerHandler() { }

    public TimerHandler(double ElapsedSeconds)
    {
        this.ElapsedSeconds = ElapsedSeconds;
    }

    public void start()
    {
        begin =  System.currentTimeMillis();
        started=true;
    }
    public boolean isFinished()
    {
        if(started)
            if(System.currentTimeMillis() - begin> ElapsedSeconds*1000)
                return true;

        return false;
    }
}