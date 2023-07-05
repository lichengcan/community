package com.example.community.rabbitmq;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题消费类型配置
 * @Author xiaolei
 * @Date 2021/10/29 11:03
 **/
@Configuration
public class TopicRabbitConfig {

    /**
     *  给队列取名字
     * @return
     */
    @Bean
    public Queue firstQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue ("queue1",true);
    }
    @Bean
    public Queue SecondQueue() {
        return new Queue ("queue2",true);
    }

    @Bean
    public Queue ThreeQueue() {
        return new Queue ("queue3",true);
    }

    @Bean
    public Queue FourQueue() {
        return new Queue ("queue4",true);
    }

    /**
     * 给交换机取名
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("exchange_firm",true,false);
    }

    @Bean
    public Binding bindingTopic1(){
        return BindingBuilder.bind(firstQueue()).to(topicExchange()).with("*.吴京");
    }

    @Bean
    public Binding bindingTopic2(){
        return BindingBuilder.bind(SecondQueue()).to(topicExchange()).with("爱国.吴京");
    }

    @Bean
    public Binding bindingTopic3(){
        return BindingBuilder.bind(ThreeQueue()).to(topicExchange()).with("爱国.*");
    }
    @Bean
    public Binding bindingTopic4(){
        return BindingBuilder.bind(FourQueue()).to(topicExchange()).with("#.沈腾");
    }
}

