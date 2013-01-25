/**
 * 
 */
package org.mspring.mlog.schedule.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Job;
import org.mspring.mlog.entity.JobLog;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO 更新系统统计信息
 */
public class UpdateStatInfoJob extends AbstractJob {

    private static final Logger log = Logger.getLogger(UpdateStatInfoJob.class);

    public static final Long JOB_ID = new Long(1);
    public static final String JOB_NAME = "UpdateStatInfoJob";

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org
     * .quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        // TODO Auto-generated method stub
        JobLog jobLog = new JobLog();
        jobLog.setTime(new Date());
        long start = System.currentTimeMillis();
        boolean success = true;
        try {
            log.debug("begin update post count...");
            ServiceFactory.getStatService().updatePostCount();
            log.debug("begin update comment count...");
            ServiceFactory.getStatService().updateCommentCount();
        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            jobLog.setMessage(e.getMessage());
        }

        long end = System.currentTimeMillis();
        jobLog.setUseTime(end - start);
        jobLog.setJob(new Job(JOB_ID));
        jobLog.setName(JOB_NAME);
        jobLog.setSuccess(success);
        ServiceFactory.getJobLogService().createJobLog(jobLog);
    }

}
