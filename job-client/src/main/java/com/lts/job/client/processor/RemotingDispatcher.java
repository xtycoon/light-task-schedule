package com.lts.job.client.processor;

import com.lts.job.client.support.JobFinishedHandler;
import com.lts.job.common.protocol.JobProtos;
import com.lts.job.common.remoting.RemotingClientDelegate;
import com.lts.job.remoting.exception.RemotingCommandException;
import com.lts.job.remoting.netty.NettyRequestProcessor;
import com.lts.job.remoting.protocol.RemotingCommand;
import com.lts.job.remoting.protocol.RemotingProtos;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

import static com.lts.job.common.protocol.JobProtos.RequestCode.JOB_FINISHED;
import static com.lts.job.common.protocol.JobProtos.RequestCode.valueOf;

/**
 * @author Robert HG (254963746@qq.com) on 7/25/14.
 * 客户端默认通信处理器
 */
public class RemotingDispatcher extends AbstractProcessor {

    private static final Map<JobProtos.RequestCode, NettyRequestProcessor> processors = new HashMap<JobProtos.RequestCode, NettyRequestProcessor>();

    public RemotingDispatcher(RemotingClientDelegate remotingClient, JobFinishedHandler jobFinishedHandler) {
        super(remotingClient);
        processors.put(JOB_FINISHED, new JobFinishedProcessor(remotingClient, jobFinishedHandler));
    }

    @Override
    public RemotingCommand processRequest(ChannelHandlerContext ctx, RemotingCommand request) throws RemotingCommandException {
        JobProtos.RequestCode code = valueOf(request.getCode());
        NettyRequestProcessor processor = processors.get(code);
        if (processor == null) {
            return RemotingCommand.createResponseCommand(RemotingProtos.ResponseCode.REQUEST_CODE_NOT_SUPPORTED.code(), "request code not supported!");
        }
        return processor.processRequest(ctx, request);
    }
}
