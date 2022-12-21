package de.kaitokuntatsu.random;

import de.joshua.DataStructures.Queue;
import de.joshua.DataStructures.Stack;

public class ReversedNumbers {

    Queue<Integer> queue = new Queue<Integer>();
    Stack<Integer> stack = new Stack<Integer>();


    public void fillQueue(int[] values)
    {
        for (int val : values) queue.enqueue(val);
    }

    public void fromQueueToStack()
    {
        while(!queue.isEmpty()) { stack.push(queue.front()); queue.dequeue(); }
    }

    public void ausgabe_zahlenfolge()
    {
        while(!stack.isEmpty())
        {
            System.out.println(stack.top());
            stack.pop();
        }
    }

    public static void main(String[] args)
    {
        ReversedNumbers yee = new ReversedNumbers();
        int[] vals = {1,2,3,4,34,5,6,5,67,6,78,78,9,89};
        yee.fillQueue(vals);
        yee.fromQueueToStack();
        yee.ausgabe_zahlenfolge();
    }
}
