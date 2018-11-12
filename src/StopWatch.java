public class StopWatch
{
    private double startTime;
    private double endTime;

    public StopWatch() { }

    public void start()
    {
        startTime = System.currentTimeMillis();
    }

    public void stop()
    {
        endTime = System.currentTimeMillis();
    }

    public double elapsedMiliseconds()
    {
        return endTime - startTime;
    }

    public double elapsedSeconds()
    {
        return (endTime - startTime) / 1000.0d;
    }

    public double elapsedMinutes()
    {
        return (endTime - startTime) / 60000.0d;
    }

    public String formatedSeconds(int decimalPlaces)
    {
        String seconds = String.format("%."+ decimalPlaces +"f", elapsedSeconds());

        return seconds;
    }

    public String formatedMinutes(int decimalPlaces)
    {
        String minutes = String.format("%."+ decimalPlaces +"f", elapsedMinutes());

        return minutes;
    }

    public void displayElapsedTime()
    {
        System.out.println("Miliseconds: " + elapsedMiliseconds());
        System.out.println("Seconds: " + formatedSeconds(6));
        System.out.println("Minutes: " + formatedMinutes(6));
    }


}
