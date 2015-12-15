package foo;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AutoconhecimentoTest {

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();
	
	@Before
	public void setUp() {
		init(rule.getProcessEngine());
	}
	
	@Test
	@Deployment(resources = "autoconhecimento.bpmn")
	public void testFluxoNormal() {
		
		ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByKey("ID02");
		
		assertThat(pi).isStarted();
	}
}
