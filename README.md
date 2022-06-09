# SecuritiesSystem

| 接口地址                                              | 功能说明                                                     | 参数说明                                                     | 样例                                                         |
| ----------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| /company/add                                          | 新增公司                                                     | asset代表公司总资产、stockNum代表公司初始股、companyName代表公司名、stockName代表股票名 | ![image-20220603062747722](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603062747722.png) |
| /company/update/name                                  | 更新公司名以及股票名                                         | companyId代表公司ID，companyName代表公司新名、stockName代表股票新名 | ![image-20220603063104129](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603063104129.png) |
| /company/get/page                                     | 获取股票的分页数据                                           | pageNum代表呈现第几页的数据，pageSize代表每页呈现多少数据。右图样例代表返回第1页且该页数据量为2的股票数据信息。 | ![image-20220603063247502](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603063247502.png) |
| /company/get/{cid}                                    | 获取指定公司（股票）信息                                     | {cid}代表公司ID                                              | http://localhost:8080/company/get/8                          |
| /company/update/asset?cid=公司ID&asset=公司总资产     | 更新公司总资产                                               | cid代表公司ID，asset代表新的公司总资产                       | http://localhost:8080/company/update/asset?cid=8&asset=102   |
| /company/delete/{cid}                                 | 删除指定公司信息                                             | {cid}代表公司ID                                              | http://localhost:8080/company/delete/8                       |
| /login                                                | 登录                                                         | username代表账号，password代表密码；普通用户不需要传入type，而管理员参数需要加上type:1，且管理员账号为root，密码为123（后台已加密） | ![image-20220603064223103](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603064223103.png)![image-20220603064306347](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603064306347.png) |
| /register/client                                      | 注册普通用户                                                 | idNumber保证账号唯一性，即确保一个用户一个账号，如果用idNumber重复注册则会返回400状态码；注册成功会返回随机账号 | ![image-20220603064503604](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603064503604.png)![image-20220603064707082](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603064707082.png) |
| /stock/get/all                                        | 获取所有股票信息                                             | 不需要传入参数，获取成功则返回200状态码                      | http://localhost:8080/stock/get/all                          |
| /stock/get/{uid}                                      | 返回用户购买的股票交易信息（包括股票ID[cid]以及向该股票购入的总股数） | {uid}代表股民ID，区别于username                              | http://localhost:8080/stock/get/1                            |
| /trade/page                                           | 获取用户购买股票的分页交易记录数据                           | sid代表股民ID，pageNum代表第几页，pageSize代表每页需呈现的数据量 | ![image-20220603065317599](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603065317599.png) |
| /trade/buy                                            | 买股票                                                       | stockHolderId代表股民ID、stockId代表股票ID、stockNum代表购买的股票数 | ![image-20220603071510058](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603071510058.png) |
| /trade/sale                                           | 卖股票                                                       | 参数与买股票接口一致，如果卖出的股票数大于已买入的股票数会返回400且如果买卖的股票不存在也会返回400 | ![image-20220603075752086](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603075752086.png) |
| /user/get/{uid}                                       | 获取股民基本信息                                             | {uid}代表股民ID                                              | http://localhost:8080/user/get/1 ![image-20220603080147400](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603080147400.png) |
| /user/bind?username=银行端用户名&stockHolderId=股民ID | 绑定银行账户                                                 | username代表银行系统账号、stockHolderId代表股民ID            | /user/bind?username=root&stockHolderId=1                     |
| /trade/bank/add                                       | 银行转账                                                     | stockHolderId表示股民ID、bankCardId表示银行卡ID、addedMoney表示此次交易涉及的总金额（绝对值）、type为2代表存钱，type为3代表取钱，state为0表示未完成，为1表示已完成，默认需要传入state:0 | ![image-20220603081220210](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603081220210.png)![image-20220603083001365](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220603083001365.png) |
| /trade/bank/get/page                                  | 获取转账分页交易记录信息                                     | sid代表股民ID，pageNum代表第N页，pageSize代表该页数据量      | ![image-20220610052926391](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220610052926391.png) |
| /trade/bank/update/{orderId}                          | 超时订单重新提交                                             | 如果一个订单处理超时则state为2                               | /trade/bank/update/485                                       |
| /stock/get/page                                       | 获取用户已购买股票信息的分页数据                             | sid为股民ID，pageSize为每一页的数据量，pageNum为需要返回的指定页数的数据 | ![image-20220606105609859](https://github.com/Adam-ly-captain/SecuritiesSystem/blob/master/img/image-20220606105609859.png) |
| /logout                                               | 注销登录                                                     | 不需要传值                                                   | http://localhost:8080/logout                                 |

