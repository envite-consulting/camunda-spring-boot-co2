package de.envite.carbon.springboot.fibonacci.camunda;

import java.time.Duration;
import java.time.Instant;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("fibonacciIteratively")
public class FibonacciIteratively implements JavaDelegate {

  @Override
  public void execute(DelegateExecution delegateExecution) {
    Instant start = Instant.now();
    long result = fibonacciIterative(Integer.MAX_VALUE);
    Duration timeElapsed = Duration.between(start, Instant.now());

    delegateExecution.setVariable("fibonacciIteratively", result);
    System.out.printf("iterative: %d ms\n", timeElapsed.toMillis());
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
