package de.envite.carbon.springboot.fibonacci.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("fibonacciRecursively")
public class FibonacciRecursively implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {

        long start = System.nanoTime();
        delegateExecution.setVariable("fibonacciRecursively", fibonacciRecursive(40));
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Rekursiv: " + timeElapsed);
    }

    private int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 2) + fibonacciRecursive(n - 1);
    }
}
