package edu.fjnu501.securities.rpc;

import edu.fjnu501.rpc.RPCProtocol;
import edu.fjnu501.rpc.domain.BankTrade;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;


public class TaskWorker implements Runnable, RPCProtocol {

    private ArrayBlockingQueue<BankTrade> queue;

    public TaskWorker(ArrayBlockingQueue<BankTrade> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sendTrade();  // 发送订单数据
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendTrade() throws IOException, InterruptedException {
        BankTrade bankTrade = queue.take();
        RPCProtocol target = RPC.getProxy(RPCProtocol.class, RPCProtocol.versionID, new InetSocketAddress("localhost", 9871), new Configuration());
        target.receiveTrade(bankTrade);
    }

}
