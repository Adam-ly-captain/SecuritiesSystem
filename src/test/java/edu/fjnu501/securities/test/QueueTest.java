package edu.fjnu501.securities.test;

import edu.fjnu501.rpc.RPCProtocol;
import edu.fjnu501.rpc.domain.BankTrade;
import edu.fjnu501.rpc.domain.Result2;
import edu.fjnu501.securities.state.ResultCodeState;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

public class QueueTest implements RPCProtocol {

    private BankTrade waitBankTrade;

    @Test
    public void test1() throws InterruptedException {
//        ArrayBlockingQueue<User> queue = new ArrayBlockingQueue<>(2);
//        queue.take();
//        User user = new User();
//        User user1 = new User();
//        user.setUsername("123");
//        user1.setUsername("1234");
//        queue.put(user);
//        System.out.println(queue.size());
//        queue.put(user1);
//        System.out.println(queue.size());
//        User poll = queue.poll();
//        System.out.println(queue.size());
//        User poll1 = queue.poll();
//        System.out.println(poll);
//        System.out.println(poll1);
        Configuration configuration = new Configuration();
        configuration.set("ipc.client.rpc-timeout.ms", "300");
        System.out.println(RPC.getRpcTimeout(configuration));
    }

    public static void main(String[] args) throws IOException {
        RPC.Server server = new RPC.Builder(new Configuration())
                .setBindAddress("localhost")
                .setPort(9871)
                .setProtocol(RPCProtocol.class)
                .setInstance(new QueueTest())
                .build();
        server.start();
    }

    @Override
    public void receiveTrade(BankTrade bankTrade) throws IOException, InterruptedException {
        waitBankTrade = bankTrade;
        Result2 result = new Result2();
        result.setCode(ResultCodeState.SUCCESS.getState());
        result.setMsg(bankTrade.getTradeId());
        RPCProtocol protocol = RPC.getProxy(RPCProtocol.class, RPCProtocol.versionID, new InetSocketAddress("localhost", 9870), new Configuration());
        protocol.responseTrade(result);
    }

    @Override
    public void responseTrade(Result2 result) throws InterruptedException, IOException {
        if (ResultCodeState.SUCCESS.getState() == result.getCode()) {
            if (result.getMsg() == waitBankTrade.getTradeId()) {
                System.out.println("银行处理成功");  // 更新银行的余额
            }
        }
    }
}
