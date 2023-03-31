package de.envite.carbon.springboot.fibonacci.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

@Component("fibonacciIteratively")
public class FibonacciIteratively implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        long start = System.nanoTime();
        delegateExecution.setVariable("fibonacciIteratively", fibonacciIterative(40));
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("iterativ: " + timeElapsed);
    }

    private int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }

        int fib = 1;
        int prevFib = 1;

        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib += prevFib;
            prevFib = temp;
        }
        return fib;
    }
}
