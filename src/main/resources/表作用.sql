select * from act_re_deployment  #部署对象表

select * from act_re_procdef   #流程定义表

select * from act_ge_bytearray  #资源文件表

select * from  act_ge_property   #主键生成策略表


#流程实例, 执行对象, 任务


#执行对象表
select * from act_ru_execution

#正在执行任务表,只有节点是usertask 时该表才有数据
select * from act_ru_task; 

select * from act_ru_job


select * from act_ru_variable

select * from act_ru_event_subscr


#------------------
#流程实例历史表

# 一个流程, 流程实例只有一个,执行对象可以有多个
select * from act_hi_procinst

#任务历史表
select * from act_hi_taskinst

#所有活动历史表
select * from act_hi_actinst  


#-----------------------------------------

#获取流程实例变量
select * from act_ru_variable

#流程实例历史变量
select * from act_hi_varinst