package foo;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
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

	/**
	 * Just tests if the process definition is deployable.
	 */
	@Test
	@Deployment(resources = "process.bpmn")
	public void testParsingAndDeployment() {

		HashMap<String, Object> processVariables = new HashMap<String, Object>();
		processVariables.put(
				"content",
				"camunda training - XOR gateway brhuehuehue em: "
						+ new SimpleDateFormat("HH:mm:ss")
								.format(new GregorianCalendar().getTime()));
		processVariables.put("xoraBola", Boolean.TRUE);

		ProcessInstance processInstance = rule.getRuntimeService()
				.startProcessInstanceByKey("learning-camunda-BPM",
						processVariables);

		assertThat(processInstance).isEnded();
	}

}
