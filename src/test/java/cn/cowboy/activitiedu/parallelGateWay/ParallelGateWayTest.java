package cn.cowboy.activitiedu.parallelGateWay;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

/**
 * 排他网关
 * 
 * @author canton_cowboy
 *
 */
public class ParallelGateWayTest {
	private ProcessEngine	processEngine;

	@Before
	public void setUp() {
		processEngine = ProcessEngines.getDefaultProcessEngine();
	}

	/**
	 * 发布流程实例
	 */
	@Test
	public void deployProcessInstance() {
		InputStream bmpmIns = this.getClass().getResourceAsStream("parallelGateWay.bpmn");
		InputStream imgIns = this.getClass().getResourceAsStream("parallelGateWay.png");
		Deployment deployment = processEngine.getRepositoryService()//
				.createDeployment()//
				.name("并行网关测试说")//
				.addInputStream("parallelGateWay.bpmn", bmpmIns)//
				.addInputStream("parallelGateWay.png", imgIns)//
				.deploy();

		System.out.println("deploymentId:" + deployment.getId());
		System.out.println("deploymentName:" + deployment.getName());
	}

	/**
	 * 启动流程实例
	 */
	@Test
	public void startProcessInstance() {
		String processKey = "parallelGateWay";
		ProcessInstance processInstance = processEngine.getRuntimeService()//
				.startProcessInstanceByKey(processKey);

		System.out.println("流程实例id:" + processInstance.getId());
		// 12501
	}

	/**
	 * 完成任务
	 */
	@Test
	public void completeTask() {
		String assignee = "张三";
		List<String> taskIds = getTaskId(assignee);
		
		for (String taskId : taskIds){
			//processEngine.getTaskService().setVariable(taskId, "money", 600);
			processEngine.getTaskService().complete(taskId);
			System.out.println(assignee+"  complete task: "+taskId);
		}
	}

	private List<String> getTaskId(String assignee) {
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
		List<String> taskIds = new ArrayList<String>();
		for (Task t : tasks) {
			taskIds.add(t.getId());
		}
		return taskIds;
	}

}
