package de.envite.carbon.springboot.fibonacci.camunda;

import java.time.Duration;
import java.time.Instant;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("fibonacciRecursively")
public class FibonacciRecursively implements JavaDelegate {

  @Override
  public void execute(DelegateExecution delegateExecution) {

    Instant start = Instant.now();
    long result = fibonacciRecursive(40);
    Duration timeElapsed = Duration.between(start, Instant.now());

    delegateExecution.setVariable("fibonacciRecursively", result);
    System.out.printf("recursive: %d ms\n", timeElapsed.toMillis());
  }

  private int fibonacciRecursive(int n) {
    if (n <= 1) {
      return n;
    }
    return fibonacciRecursive(n - 2) + fibonacciRecursive(n - 1);
  }
}
