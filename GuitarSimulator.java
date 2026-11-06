/**
 * This GuitarSimulator object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarSimulator
{
    public static void main(String[] args){
    String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    GuitarString[] string = new GuitarString[37];

    for (int i = 0; i < string.length; i++)
    {
        double frequency = 440 * Math.pow(1.05956, i - 24);
        string[i] = new GuitarString(frequency);
    }

    while(true){
        if(StdDraw.hasNextKeyTyped()){
            char key = StdDraw.nextKeyTyped();
            int index = keyboard.indexOf(key);

            if(index != -1){
                string[index].pluck();
            }

            double sample = 0.0;

            for (int i = 0; i < string.length; i++){
                sample += string[i].sample();
            }

            StdAudio.play(sample);

            for(int i = 0; i < string.length; i++){
                string[i].tic();
            }
        }
    }

    }
}
