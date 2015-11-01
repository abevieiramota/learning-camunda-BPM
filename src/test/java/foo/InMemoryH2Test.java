package foo;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.impl.cmmn.cmd.CompleteCaseExecutionCmd;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();

	private static final String PROCESS_DEFINITION_KEY = "learning-camunda-BPM";

	// enable more detailed logging
	static {
		// LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
		// LogFactory.useJdkLogging(); // MyBatis
	}

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = "process.bpmn")
	public void testParsingAndDeployment() {

		HashMap<String, Object> processVariables = new HashMap<String, Object>();
		processVariables.put(
				"content",
				"camunda training - User task gateway brhuehuehue em: "
						+ new SimpleDateFormat("HH:mm:ss")
								.format(new GregorianCalendar().getTime()));

		ProcessInstance processInstance = rule.getRuntimeService()
				.startProcessInstanceByKey("learning-camunda-BPM",
						processVariables);

		assertThat(processInstance).isStarted().isWaitingAt("user_task_review_process");
		
		complete(task(), Variables.createVariables().putValue("approved", Boolean.TRUE));
		
		assertThat(processInstance).isEnded().hasPassed("hello_world", "groovy_script", "user_task_review_process", "create_twitter");
	}
	
	@Test
	@Deployment(resources = "process.bpmn")
	public void testParsingAndDeployment2() {

		HashMap<String, Object> processVariables = new HashMap<String, Object>();
		processVariables.put(
				"content",
				"camunda training - User task gateway - approved FALSE brhuehuehue em: "
						+ new SimpleDateFormat("HH:mm:ss")
								.format(new GregorianCalendar().getTime()));

		ProcessInstance processInstance = rule.getRuntimeService()
				.startProcessInstanceByKey("learning-camunda-BPM",
						processVariables);

		assertThat(processInstance).isStarted().isWaitingAt("user_task_review_process");
		
		complete(task(), Variables.createVariables().putValue("approved", Boolean.FALSE));
		
		assertThat(processInstance).isEnded().hasPassed("hello_world", "groovy_script", "user_task_review_process");
	}

}
