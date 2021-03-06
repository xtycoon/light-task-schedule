package com.lts.job.common.domain;


import com.lts.job.remoting.annotation.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Robert HG (254963746@qq.com) on 8/13/14.
 */
public class Job {

    protected String jobId;
    @NotNull
    protected String taskId;
    /**
     * 优先级 (数值越大 优先级越低)
     */
    protected Integer priority = 0;
    // 提交的节点
    @NotNull
    protected String nodeGroup;
    // 执行的节点
    @NotNull
    protected String taskTrackerNodeGroup;

    protected Map<String, String> extParams;
    // 是否要反馈给客户端
    protected boolean needFeedback = true;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNodeGroup() {
        return nodeGroup;
    }

    public void setNodeGroup(String nodeGroup) {
        this.nodeGroup = nodeGroup;
    }

    public String getTaskTrackerNodeGroup() {
        return taskTrackerNodeGroup;
    }

    public void setTaskTrackerNodeGroup(String taskTrackerNodeGroup) {
        this.taskTrackerNodeGroup = taskTrackerNodeGroup;
    }

    public boolean isNeedFeedback() {
        return needFeedback;
    }

    public void setNeedFeedback(boolean needFeedback) {
        this.needFeedback = needFeedback;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Map<String, String> getExtParams() {
        return extParams;
    }

    public void setExtParams(Map<String, String> extParams) {
        this.extParams = extParams;
    }

    public String getParam(String key) {
        if (extParams == null) {
            return null;
        }
        return extParams.get(key);
    }

    public void setParam(String key, String value) {
        if (extParams == null) {
            extParams = new HashMap<String, String>();
        }
        extParams.put(key, value);
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", priority=" + priority +
                ", nodeGroup='" + nodeGroup + '\'' +
                ", taskTrackerNodeGroup='" + taskTrackerNodeGroup + '\'' +
                ", extParams=" + extParams +
                ", needFeedback=" + needFeedback +
                '}';
    }
}
