package ctv.core_service.service.Impl;//package demo.service.Impl;
//
//import demo.service.WorkflowService;
//import demo.temporal.MyWorkFlow;
//import io.temporal.client.WorkflowClient;
//import io.temporal.client.WorkflowOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WorkflowServiceImpl implements WorkflowService {
//    @Autowired
//    private WorkflowClient workflowClient;
//
//    @Override
//    public String startWorkflow(String input) {
//        WorkflowOptions options =WorkflowOptions.newBuilder()
//                .setTaskQueue("MyTaskQueue")
//                .build();
//        MyWorkFlow workflow = workflowClient.newWorkflowStub(MyWorkFlow.class, options);
//        return workflow.executeWorkflow(input);
//    }
//}
