/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */

public class RingBuffer 
{
    double[] array;
    int front;
    int rear;
    int size;

    public RingBuffer(int capacity)
    {
        array = new double[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public boolean isFull()
    {
        return size == array.length;
    }
    
    public void add(double value)
    {
        if(isFull()){
            throw new InvalidActionException("No");
        }
        array[rear] = value;
        rear = (rear + 1) % array.length;
        size++;
    }
    
    public double peek()
    {
        if(isEmpty())
        {
            throw new InvalidActionException("Nope");
        }
        return array[front];
    }
    
    public double remove()
    {
        if(isEmpty())
        {
            throw new InvalidActionException("Nup");
        }
        double value = array[front];
        front = (front + 1) % array.length;
        size--;
        return value;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
