package io.codebyexample.servicea.core.usecase;

import io.codebyexample.servicea.core.entity.Greeting;
import io.codebyexample.servicea.dataprovider.serviceb.MessageB;
import io.codebyexample.servicea.dataprovider.serviceb.ServiceBClient;
import io.codebyexample.servicea.dataprovider.servicec.MessageC;
import io.codebyexample.servicea.dataprovider.servicec.ServiceCClient;
import io.codebyexample.servicea.dataprovider.servicec.ServiceCClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author huypva
 */
@Component
public class GreetUseCaseImpl implements GreetUseCase {

  ServiceBClient serviceBClient;
  ServiceCClient serviceCClient;

  @Autowired
  public GreetUseCaseImpl(ServiceBClient serviceBClient, @Lazy ServiceCClient serviceCClient) {
    this.serviceBClient = serviceBClient;
    this.serviceCClient = serviceCClient;
  }

  @Override
  public Greeting greetB(int id) {
    MessageB messageB = serviceBClient.greet(id);
    return new Greeting(id, "MessageA -> " + messageB.getMessage());
  }

  @Override
  public Greeting greetC(int id) {
    MessageC messageC = serviceCClient.greet(id);
    return new Greeting(id, "MessageA -> " + messageC.getMessage());
  }
}
