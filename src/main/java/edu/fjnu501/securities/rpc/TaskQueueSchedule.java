package edu.fjnu501.securities.rpc;

import edu.fjnu501.rpc.RPCProtocol;
import edu.fjnu501.rpc.domain.BankTrade;
import edu.fjnu501.rpc.domain.Result2;
import edu.fjnu501.securities.service.impl.TradeServiceImpl;
import edu.fjnu501.securities.state.ResultCodeState;
import edu.fjnu501.securities.state.TradeState;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskQueueSchedule implements RPCProtocol {

   public static final int sendQueueCapacity = 10;
   public static final int waitQueueCapacity = 10;

   public static ArrayBlockingQueue<BankTrade> sendQueue;
   private ArrayBlockingQueue<BankTrade> waitQueue;

   private TaskProducer taskProducer;
   private TaskWorker taskWorker;

   public static final Lock lock = new ReentrantLock();
   public static final Condition wait = lock.newCondition();

   private TradeServiceImpl tradeServiceImpl;
   public static BankTrade waitReceiveTrade;

   public static boolean isReceived;

   public TaskQueueSchedule() {
      init();
   }

   public TaskQueueSchedule(int i) {}

   public void init() {
      RPC.Server server = null;
      try {
         server = new RPC.Builder(new Configuration())
                 .setBindAddress("localhost")
                 .setPort(3000)
                 .setProtocol(RPCProtocol.class)
                 .setInstance(new TaskQueueSchedule(1))
                 .build();
      } catch (IOException e) {
         e.printStackTrace();
      }
      server.start();
   }

   private void initTaskQueue() {
      sendQueue = new ArrayBlockingQueue<>(sendQueueCapacity);
      waitQueue = new ArrayBlockingQueue<>(waitQueueCapacity);

      taskProducer = new TaskProducer(sendQueue, waitQueue, tradeServiceImpl);
//      taskWorker = new TaskWorker(sendQueue);

      Thread producer = new Thread(taskProducer, "producer");
      Thread producer1 = new Thread(taskProducer, "producer");
      producer.start();
      producer1.start();
   }

   public int getSendQueueCapacity() {
      return sendQueueCapacity;
   }

   public int getWaitQueueCapacity() {
      return waitQueueCapacity;
   }

   public ArrayBlockingQueue<BankTrade> getSendQueue() {
      return sendQueue;
   }

   public void setSendQueue(ArrayBlockingQueue<BankTrade> sendQueue) {
      this.sendQueue = sendQueue;
   }

   public ArrayBlockingQueue<BankTrade> getWaitQueue() {
      return waitQueue;
   }

   public void setWaitQueue(ArrayBlockingQueue<BankTrade> waitQueue) {
      this.waitQueue = waitQueue;
   }


   @Override
   public void responseTrade(Result2 result) throws InterruptedException, IOException {
      if (ResultCodeState.SUCCESS.getState() == result.getCode()) {
         if (result.getMsg() == waitReceiveTrade.getTradeId()) {
            sendQueue.take();
            isReceived = true;
            TaskProducer.tradeService.updateBankTradeState(waitReceiveTrade.getTradeId(), TradeState.COMPLETED.getState());
            TaskProducer.tradeService.addMoneyByBankCard(waitReceiveTrade);
            System.out.println("处理成功");
//            RPCProtocol protocol = RPC.getProxy(RPCProtocol.class, RPCProtocol.versionID, new InetSocketAddress("localhost", 9871), new Configuration());
//            Result2 result2 = new Result2();
//            result2.setCode(ResultCodeState.SUCCESS.getState());
//            result2.setMsg(waitReceiveTrade.getTradeId());
//            protocol.responseTrade(result2);
//            Thread.sleep(1000);
         }
      }
   }

   public void setTradeServiceImpl(TradeServiceImpl tradeServiceImpl) {
      this.tradeServiceImpl = tradeServiceImpl;
      initTaskQueue();
   }

}
