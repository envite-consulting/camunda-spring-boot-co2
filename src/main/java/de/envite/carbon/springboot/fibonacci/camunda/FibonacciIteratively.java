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
    long result = fibonacciIterative(Long.MAX_VALUE);
    Duration timeElapsed = Duration.between(start, Instant.now());

    delegateExecution.setVariable("fibonacciIteratively", result);
    System.out.printf("iterative: %d ms\n", timeElapsed.toMillis());
  }

  private long fibonacciIterative(long n) {
    if (n <= 1) {
      return n;
    }

    int fib = 1;
    int prevFib = 1;
    long startTime = System.currentTimeMillis();

    for (int i = 2; i < n; i++) {
      if (System.currentTimeMillis() > startTime + 10000)
        break;
      int temp = fib;
      fib += prevFib;
      prevFib = temp;
    }
    return fib;
  }
}
