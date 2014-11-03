package cn.cowboy.activitiedu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import junit.framework.TestCase;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class HeloWorldActiviti2 {

	private ProcessEngine	processEngine;

	@Before
	public void setUp() throws Exception {
		processEngine = ProcessEngines.getDefaultProcessEngine();
	}

	/**
	 * 是用zip发布process
	 */
	@Test
	public void deployProcessByZip(){
		ZipInputStream zipInputStream = new ZipInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream("diagrams/helloworld.zip"));
		Deployment deployment=processEngine.getRepositoryService()//
				.createDeployment()//
				.addZipInputStream(zipInputStream)//
				.name("helloworldProcess")//
				.deploy();
		System.out.println("deploymentName:"+deployment.getName());
		System.out.println("deploymentId:"+deployment.getId());
	}


	/**
	 * 流程定义查询
	 */
	@Test
	public void findProcess(){
		ProcessDefinitionQuery processDefinitionQuery =processEngine.getRepositoryService().createProcessDefinitionQuery();
		List<ProcessDefinition> deployments =processDefinitionQuery.list();
		for(ProcessDefinition d: deployments){
			System.out.println("getId:"+d.getId());
			System.out.println("getDeploymentId:"+d.getDeploymentId());
			System.out.println("getVersion:"+d.getVersion());
			System.out.println("getKey:"+d.getKey());
			System.out.println("--------------------------------------");
		}
	}
	
	
	/**
	 * 查看资源流程图
	 * @throws IOException 
	 */
	@Test
	public void viewResourceImg() throws IOException{
		String deploymentId ="12501";
		List<String> resourceNames=processEngine.getRepositoryService().getDeploymentResourceNames(deploymentId);
		String resourceName=null;
		for(String s:resourceNames){
			if(s.indexOf("png")!=-1){
				resourceName = s;
				System.out.println("find resource name is "+resourceName);
				break;
			};
		}
		InputStream ins =processEngine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
		FileUtils.copyInputStreamToFile(ins, new File("h://helloworld.png"));
	}
}
