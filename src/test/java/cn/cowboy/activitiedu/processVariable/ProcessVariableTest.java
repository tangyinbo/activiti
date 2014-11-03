package cn.cowboy.activitiedu.processVariable;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Before;
import org.junit.Test;

public class ProcessVariableTest {
	private ProcessEngine processEngine;
	@Before
	public void setUp(){
		processEngine = ProcessEngines.getDefaultProcessEngine();
	}
	
	/**
	 * 发布流程
	 */
	@Test
	public void deployProcess(){
		InputStream bmpnIns = this.getClass().getClassLoader().getResourceAsStream("diagrams/processVariable.bpmn");
		InputStream imgIns = this.getClass().getClassLoader().getResourceAsStream("diagrams/processVariable.png");
		String bmpnResourceName ="processVariable.bpmn";
		String imgResourceName="processVariable.png";
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment=repositoryService//
		.createDeployment()//
		.name("流程变量测试")//
		.addInputStream(bmpnResourceName, bmpnIns)//
		.addInputStream(imgResourceName, imgIns)//
		.deploy();
		
		System.out.println("getId:"+deployment.getId());
		System.out.println("getName:"+deployment.getName());
		System.out.println("getDeploymentTime:"+deployment.getDeploymentTime());
	}
	
	/**
	 * 查询所有的流程实例
	 */
	@Test
	public void getAllProcess(){
		ProcessDefinitionQuery processDefinitionQuery=processEngine.getRepositoryService().createProcessDefinitionQuery();
		List<ProcessDefinition> processDefinitions=processDefinitionQuery.list();
		
		for(ProcessDefinition p:processDefinitions){
			System.out.println("getKey:"+p.getKey());
			System.out.println("getName:"+p.getName());
			System.out.println("getVersion:"+p.getVersion());
		}
	}
	
	/**
	 * 启动流程实例
	 * 设置流程实例变量
	 */
	@Test
	public void startProcessInstance(){
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance  processInstance=runtimeService.startProcessInstanceByKey("myProcess");
		System.out.println(processInstance.getId());//2501
	}
	/**
	 * 设置流程实例变量
	 */
	@Test
	public void setProcessVariables(){
		String executionId = "2501";
		RuntimeService runtimeService = processEngine.getRuntimeService();
		runtimeService.setVariable(executionId, "runtime_申请人", "tangyinbo");
	}
	
	/**
	 * 
	 */
	@Test
	public void setProcessVariables2(){
		String taskId = "2504";
		TaskService taskService=processEngine.getTaskService();
		taskService.setVariable(taskId, "task_申请人", "haha");
		//local 跟当前任务绑定,执行实例的其他执行对象不可见该变量
		taskService.setVariableLocal(taskId, "备注", "请你hi吃饭呀");
	}
	
	@Test
	public void getProcessVariable(){
		String assignee="张三";
		TaskService taskService = processEngine.getTaskService();
		TaskQuery taskQuery=taskService.createTaskQuery().taskAssignee(assignee);
		List<Task> task=taskQuery.list();
		for(Task t:task){
			System.out.println(t.getId());
			Map<String,Object> maps =taskService.getVariables(t.getId());
			System.out.println(maps);
		}
	}
	
	@Test
	public void completeTask(){
		String taskId ="2504";
		processEngine.getTaskService().complete(taskId);
	}
}
