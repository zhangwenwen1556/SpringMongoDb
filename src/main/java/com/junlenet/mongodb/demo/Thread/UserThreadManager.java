package com.junlenet.mongodb.demo.Thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserThreadManager {

	private static UserThreadManager utm = new UserThreadManager();

	// 线程池维护线程的最少数量
	private final static int CORE_POOL_SIZE = 4;

	// 线程池维护线程的最大数量
	private final static int MAX_POOL_SIZE = 10;

	// 线程池维护线程所允许的空闲时间
	private final static int KEEP_ALIVE_TIME = 0;

	// 线程池所使用的缓冲队列大小
	private final static int WORK_QUEUE_SIZE = 10;

	// 消息缓冲队列
	Queue<User> userQueue = new LinkedList<User>();

	private boolean hasMoreAcquire() {
		return !userQueue.isEmpty();
	}

	// 访问消息缓存的调度线程
	// 查看是否有待定请求，如果有，则创建一个新的UserThreadManager，并添加到线程池中
	final Runnable accessBufferThread = new Runnable() {
		@Override
		public void run() {
			if (hasMoreAcquire()) {
				User msg = (User) userQueue.poll();
				Runnable task = new UserDBThread(msg);
				// 重新调用线程池
				threadPool.execute( task );
			}
		}
	};

	final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			// TODO Auto-generated method stub
			User user = ((UserDBThread) r).getUser();
			System.out.println("消息放入到队列中了"+user);
			userQueue.offer(user);
		}
	};
	// 创建线程池
	final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
			TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE), this.handler);

	// 调度线程池
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
	// 创建一个比较弱一点的线程，一直执行队列中的数据
	final ScheduledFuture taskHandler = scheduler.scheduleAtFixedRate(accessBufferThread, 0, 1, TimeUnit.SECONDS);
	
	public static UserThreadManager newInstance(){
		return utm;
	}
	
	private UserThreadManager(){}
	
	public void addLogMsg( User user ) {
        Runnable userInfo = new UserDBThread( user );
        threadPool.execute( userInfo );
    }
	
}
