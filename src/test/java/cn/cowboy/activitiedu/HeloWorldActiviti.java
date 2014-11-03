package cn.cowboy.activitiedu;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

public class HeloWorldActiviti {

	private ProcessEngine	processEngine;

	/*
	 * @Test public void prepareActivitiProcessEngine1(){ ProcessEngineConfiguration processEngineConfiguration =ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration(); processEngineConfiguration.setJdbcDriver(""); processEngineConfiguration.setJdbcUrl(""); processEngineConfiguration.setJdbcUsername(""); processEngineConfiguration.setJdbcPassword(""); //没有表的时候自动创建所需要的表 processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE); }
	 */

	/**
	 * 初始化流程定义需要的表,实用配置文件的方式
	 */
	@Test
	public void prepareActivitiProcessEngine() {
		// ProcessEngineConfiguration processEngineConfiguration =ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault().buildProcessEngine();
		System.out.println(processEngine);
	}

	/**
	 * 定义流程实例
	 */
	@Test
	public void deployProcess() {
		// 流程定义和管理
		RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
		// 获取发布流程定义对象
		Deployment deployment = repositoryService.createDeployment()//
				.addClasspathResource("diagrams/helloworld.bpmn")//
				.addClasspathResource("diagrams/helloworld.png")//
				.deploy();

		System.out.println("deployMentId:" + deployment.getId());
		// System.out.println("deployMentName:"+deployment.getName());
		System.out.println("deployMentTime:" + deployment.getDeploymentTime());
	}

	/**
	 * 启动流程实例
	 */
	@Test
	public void startProcessInstance() {
		String processDefinitionKey = "myProcessId";
		RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);// 这里使用key的话,就能取得最新的定义流程实例

		System.out.println("流程实例id: " + processInstance.getId());
		System.out.println("流程定义id: " + processInstance.getProcessDefinitionId());
		System.out.println("BusinessKey: " + processInstance.getBusinessKey());
		/**
		 * 流程实例id: 2501 流程定义id: myProcessId:1:4 BusinessKey: null
		 */
	}

	/**
	 * 查询指定用户的任务
	 */
	@Test
	public void findAssigneePersonTask() {
		String assignee = "王五";
		// 任务管理对象
		TaskService taskServer = ProcessEngines.getDefaultProcessEngine().getTaskService();
		// 任务查询对象
		TaskQuery taskQuery = taskServer.createTaskQuery();
		// 获取指定用户的所有任务
		List<Task> tasks = taskQuery.taskAssignee(assignee).list();

		if (tasks != null && tasks.size() > 0) {
			for (Task t : tasks) {
				System.out.println("getName:"+t.getName());
				System.out.println("getAssignee:"+t.getAssignee());
				System.out.println("taskId:"+t.getId());
				/**
				 * getName:提交申请
					getAssignee:张三
					taskId:2504
				 */
			}
		} else {
			System.out.println("no task find on the assigneed use " + assignee);
		}
	}
	
	/**
	 * 完成任务
	 */
	@Test
	public void completeTask(){
		ProcessEngines.getDefaultProcessEngine().getTaskService().complete("17504");
	}
	
	
	@Test
	public void deleteProcessDeploy(){
		ProcessEngines.getDefaultProcessEngine().getRepositoryService().deleteDeployment("12501");
	}
}
