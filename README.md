# flexible-transcation  
分布式事物 
##使用文档   
###1 启动 server         
   

###2 向server申请，创建一个生产者，消息提供方  
>curl -X POST --header "Content-Type: application/json" --header "Accept: */*"  -d "{    
>>     \"appName\": \"mytest-producer\",     
>>     \"appDesc\": \"偶是一个测试生产者\",     
>>     \"callBackUrl\": \"http://127.0.0.1:8080/\"         
> }" "http://127.0.0.1:8080/flexible-transcation/api/v1/manager/app"  
返回结果： 这个结果需要给生成方，发布消息时候 需要用到 
{"appName":"mytest-producer","appId":"d37137cc-f3d4-4af8-8dc1-e7e4aa904555","appKey":"4442fad0-5b45-4ac1-9d62-b10b7a607bad"} 

###3 向server申请  创建一个订阅者， 消息消费方
> curl -X POST --header "Content-Type: application/json" --header "Accept: */*"  -d "{    
>>     \"appName\": \"mytest-consumer\",     
>>     \"appDesc\": \"偶是一个测试消费者\",     
>>     \"callBackUrl\": \"/\"         
> }" "http://127.0.0.1:8080/flexible-transcation/api/v1/manager/app"  
返回结果：
{"appName":"mytest-consumer","appId":"8b441880-8f37-431c-b8b8-3abed6f51858","appKey":"8fef4f7a-0928-45aa-9bd6-74e1092abb8d"}    

###4 创建主题

> curl -X POST --header "Content-Type: application/json" --header "Accept: */*"  -d "{
>>     \"topic\": \"ftmtest\",
>>     \"topicDesc\": \"测试主题哦\",
>>     \"maxRetryTime\": 3,
>>     \"retryInterval\": 30
> }" "http://192.168.20.50:8080/flexible-transcation/api/v1/manager/topic"  
返回结果：{"topicId":"d2fed037-649e-4e69-8906-69c5932d8369","topicName":"ftmtest"}

####5 消费者向server申请 订阅主题

>curl -X POST --header "Content-Type: application/json" --header "Accept: */*"  -d "{
>>     \"subId\": \"8b441880-8f37-431c-b8b8-3abed6f51858\",
>>     \"rountingKey\": \"mytest-routingkey\",
>>     \"topicId\": \"d2fed037-649e-4e69-8906-69c5932d8369\"
>}" "http://192.168.20.50:8080/flexible-transcation/api/v1/manager/subscribe"

##准备工作结束，接入开发流程 示列： 可参考example
###生产者示列：
##### 1 创建工程，添加配置
ftm.publish.app.key=    
ftm.publish.app.id= 
ftm.asking.target.server.addr=  
ftm.transcation.client.queue=   
ftm.transcation.message.mq.address= 
ftm.transcation.message.mq.username=    
ftm.transcation.message.mq.password=    
ftm.transcation.message.mq.virtual-host=  
##### 2 引入client包
```xml
        <dependency>
            <groupId>com.liangbo.xing</groupId>
            <artifactId>client</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```
  
##### 3 实现 ProducerCheker
```java
@Configuration
public class ProducerCheckImpl extends ProducerChecker {

    private Logger logger = LoggerFactory.getLogger(ProducerCheckImpl.class);

    @Override
    public String check(FtmProducerCheckDto ftmProducerCheckDto) {

        logger.info("test check" + ftmProducerCheckDto.getMessageBody());
        /*
            回调检查方法
            1 自己根据mq消息 确认 业务是否可以确认 发送了
         */

        return TranscationStatus.OK.getVal();
    }
}

```
#####4 生产者示列
```java
@Component
public class ProducerExcample {

    @Autowired
    private TranscationMsgClient transcationMsgClient;

    public void test() {
        try {
            FtmPublishDto ftmPublishDto = new FtmPublishDto();
            FtmResponse ftmResponse = transcationMsgClient.publish(ftmPublishDto);
            /*
                执行业务
            */
            FtmConfirmDto ftmConfirmDto = new FtmConfirmDto();
            transcationMsgClient.comfirm(ftmConfirmDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```


####5 消费者示列：
```java
@Configuration
public class ConsumerCheckerImpl extends ConsumerChecker {

    private Logger logger = LoggerFactory.getLogger(ConsumerCheckerImpl.class);

    @Override
    public boolean consume(FtmMqContent ftmMqContent) {
        //处理 mq消息 即可,注意 处理幂等性
        logger.info("接受mq消息 {}", ftmMqContent);
        return true;
    }
}
```



















