package de.envite.carbon.springboot.fibonacci;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableProcessApplication
public class FibonacciApplication {

  @Autowired
  private RuntimeService runtimeService;

  public static void main(String... args) {
    SpringApplication.run(FibonacciApplication.class, args);
  }

  @EventListener
  public void processPostDeploy(PostDeployEvent event) {
    runtimeService.startProcessInstanceByKey("Process_Fibonacci");
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(60000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.exit(0);
      }
    });
    t1.start();
  }

}
