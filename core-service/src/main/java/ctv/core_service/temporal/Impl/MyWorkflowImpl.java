package ctv.core_service.temporal.Impl; // package demo.temporal.Impl;
//
// import demo.temporal.MyActivities;
// import demo.temporal.MyWorkFlow;
// import io.temporal.activity.ActivityOptions;
// import io.temporal.workflow.Workflow;
//
// import java.time.Duration;
//
// public class MyWorkflowImpl implements MyWorkFlow {
//
//    private final MyActivities activities = Workflow.newActivityStub(MyActivities.class,
//            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());
//
//    @Override
//    public String executeWorkflow(String input) {
//        return activities.performActivity(input);
//    }
// }
