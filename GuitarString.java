/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    private RingBuffer buf;
    private int time;

    public GuitarString(double frequency) 
    {
        int cap = (int) Math.round(44100/ frequency);

        buf = new RingBuffer(cap);
        time = 0;

        for(int i = 0; i < cap; i++){
            buf.add(0.0);
        }
    }

    public GuitarString(double[] array) 
    {
        buf = new RingBuffer(array.length);
        time = 0;

        for(int i = 0; i < array.length; i++){
            buf.add(array[i]);
        }
    }

    public void pluck() 
    {
        while (!buf.isEmpty())
        {
            buf.remove();
        }

        while(true){
            try{
                double randomValue = Math.random() - 0.5;
                buf.add(randomValue);
            }catch (InvalidActionException e){
                break;
            }
        }

    }

    // advance the simulation one time step
    public void tic() 
    {
        double first = buf.remove();
        double second = buf.peek();

        double newValue = 0.996 * (first + second) / 2.0;

        buf.add(newValue);
        time++;
    }

    // return the current sample
    public double sample() 
    {
        return buf.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return time;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
