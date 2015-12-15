package foo;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;

public class ProgrammaticStartup {

	public static void main(String[] args) {
		
		ProcessEngine processEngine = new StandaloneInMemProcessEngineConfiguration().buildProcessEngine();
		
		processEngine.getRepositoryService().createDeployment().addClasspathResource("process.bpmn").deploy();
		
		processEngine.getRuntimeService().startProcessInstanceByKey("learning-camunda-BPM");
	}

}
