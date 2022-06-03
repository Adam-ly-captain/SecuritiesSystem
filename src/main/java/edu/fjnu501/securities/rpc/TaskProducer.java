package edu.fjnu501.securities.rpc;

import edu.fjnu501.rpc.RPCProtocol;
import edu.fjnu501.rpc.domain.BankTrade;
import edu.fjnu501.securities.service.TradeService;
import edu.fjnu501.securities.state.TradeState;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class TaskProducer implements Runnable, RPCProtocol {

    private ArrayBlockingQueue<BankTrade> queue;
    private ArrayBlockingQueue<BankTrade> waitQueue;
    private final Semaphore mutex = new Semaphore(1);
    private int count;

    public static TradeService tradeService;

    public TaskProducer() {}

    public TaskProducer(ArrayBlockingQueue<BankTrade> queue, ArrayBlockingQueue<BankTrade> waitQueue, TradeService tradeService)  {
        this.queue = queue;
        this.waitQueue = waitQueue;
        this.tradeService = tradeService;
    }

    private List<BankTrade> getLimitedBankTrade(int amount) {
        return tradeService.getLimitedBankTrade(amount);
    }

    @Override
    public void run() {
        while (true) {

//                if (queue.size() == TaskQueueSchedule.sendQueueCapacity) {
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock");
            List<BankTrade> limitedBankTrade = getLimitedBankTrade(1);
            if (limitedBankTrade.size() > 0) {
                for (int i = 0; i < limitedBankTrade.size(); i++) {
                    TaskQueueSchedule.waitReceiveTrade = limitedBankTrade.get(i);
                    if (TaskQueueSchedule.waitReceiveTrade != null) {
                        TaskQueueSchedule.isReceived = false;
                        try {
                            TaskQueueSchedule.sendQueue.put(TaskQueueSchedule.waitReceiveTrade);
//                    waitQueue.put(bankTrade);
                        } catch (InterruptedException e) {
//                        e.printStackTrace();
                        } finally {
                            try {
                                count = 0;
                                sendTrade(TaskQueueSchedule.waitReceiveTrade);
                                Thread.sleep(100);
                                while (!TaskQueueSchedule.isReceived) {
                                    if (count >= 5) {
                                        System.out.println("超时");
                                        tradeService.updateBankTradeState(TaskQueueSchedule.waitReceiveTrade.getTradeId(), TradeState.TIMEOUT.getState());
                                        break;
                                    }
                                    sendTrade(TaskQueueSchedule.waitReceiveTrade);
//                                    Thread.sleep(1000);
                                    count++;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            mutex.release();
                        }
                    }
                }
            } else {
                try {
                    mutex.release();
                    TaskQueueSchedule.lock.lock();
                    TaskQueueSchedule.wait.await();
                    TaskQueueSchedule.lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendTrade(BankTrade bankTrade) throws IOException, InterruptedException {
//        System.out.println(bankTrade.getTradeId());
        Configuration configuration = new Configuration();
        configuration.set("ipc.client.rpc-timeout.ms", "1000");
        RPCProtocol target = RPC.getProxy(RPCProtocol.class, RPCProtocol.versionID, new InetSocketAddress("120.79.139.163", 3101), configuration);
        try {
            target.receiveTrade(bankTrade);
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

}
