package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liukang on 2017/7/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckills=seckillService.getSeckillList();
        logger.info("list={}",seckills);

    }

    @Test
    public void getById() throws Exception {
        long seckillId=1000;
        Seckill seckill=seckillService.getById(seckillId);
        logger.info("seckill={}",seckill);
    }


    @Test//完整逻辑代码测试，注意可重复执行
    public void testSeckillLogic() throws Exception {
        long seckillId=1000;
        Exposer exposer=seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed())
        {

            logger.info("exposer={}",exposer);

            long userPhone=13476191876L;
            String md5=exposer.getMd5();

            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                System.out.println(seckillExecution);
            }catch (RepeatKillException e)
            {
                e.printStackTrace();
            }catch (SeckillCloseException e1)
            {
                e1.printStackTrace();
            }
        }else {
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }
    }
    @Test
    public void exportSeckillUrl() throws Exception {
        long id =1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
        //Exposer{exposed=true,
        // md5='56889f084d12834908f7d1dae7ca03e4',
        // seckillId=1000, now=0, start=0, end=0}

    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1000;
        long phone = 15957192524L;
        String md5 = "56889f084d12834908f7d1dae7ca03e4";

        try {
            SeckillExecution execution = seckillService.executeSeckill(id,phone,md5);
            //org.seckill.exception.SeckillException: seckill data rewrite
            logger.info("result={}",execution);

        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e ){
            logger.error(e.getMessage());
        }


        /**
         * 15:37:51.447 [main] DEBUG org.apache.ibatis.logging.LogFactory - Logging initialized using 'class org.apache.ibatis.logging.slf4j.Slf4jImpl' adapter.
         15:37:51.582 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Class not found: org.jboss.vfs.VFS
         15:37:51.585 [main] DEBUG org.apache.ibatis.io.ResolverUtil - JBoss 6 VFS API is not available in this environment.
         15:37:51.585 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Class not found: org.jboss.vfs.VirtualFile
         15:37:51.589 [main] DEBUG org.apache.ibatis.io.ResolverUtil - VFS implementation org.apache.ibatis.io.JBoss6VFS is not valid in this environment.
         15:37:51.590 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Using VFS adapter org.apache.ibatis.io.DefaultVFS
         15:37:51.590 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Find JAR URL: file:/Users/liukang/IdeaProjects/MSProject/target/classes/org/seckill/entity
         15:37:51.590 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Not a JAR: file:/Users/liukang/IdeaProjects/MSProject/target/classes/org/seckill/entity
         15:37:51.718 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Reader entry: Seckill.class
         15:37:51.719 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Reader entry: SuccessKilled.class
         15:37:51.719 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Listing file:/Users/liukang/IdeaProjects/MSProject/target/classes/org/seckill/entity
         15:37:51.719 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Find JAR URL: file:/Users/liukang/IdeaProjects/MSProject/target/classes/org/seckill/entity/Seckill.class
         15:37:51.719 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Not a JAR: file:/Users/liukang/IdeaProjects/MSProject/target/classes/org/seckill/entity/Seckill.class
         15:37:51.720 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Reader entry: ����   1 \
         15:37:51.721 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Find JAR URL: file:/Users/liukang/IdeaProjects/MSProject/target/classes/org/seckill/entity/SuccessKilled.class
         15:37:51.721 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Not a JAR: file:/Users/liukang/IdeaProjects/MSProject/target/classes/org/seckill/entity/SuccessKilled.class
         15:37:51.722 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Reader entry: ����   1 T
         15:37:51.722 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Checking to see if class org.seckill.entity.Seckill matches criteria [is assignable to Object]
         15:37:51.728 [main] DEBUG org.apache.ibatis.io.ResolverUtil - Checking to see if class org.seckill.entity.SuccessKilled matches criteria [is assignable to Object]
         15:37:51.730 [main] DEBUG o.m.spring.SqlSessionFactoryBean - Scanned package: 'org.seckill.entity' for aliases
         15:37:51.792 [main] DEBUG o.m.spring.SqlSessionFactoryBean - Parsed configuration file: 'class path resource [mybatis-config.xml]'
         15:37:51.875 [main] DEBUG o.m.spring.SqlSessionFactoryBean - Parsed mapper file: 'file [/Users/liukang/IdeaProjects/MSProject/target/classes/mapper/SeckillDao.xml]'
         15:37:51.894 [main] DEBUG o.m.spring.SqlSessionFactoryBean - Parsed mapper file: 'file [/Users/liukang/IdeaProjects/MSProject/target/classes/mapper/SuccessKilledDao.xml]'
         七月 19, 2017 3:37:52 下午 com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource getPoolManager
         信息: Initializing c3p0 pool... com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 3, acquireRetryAttempts -> 2, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 1000, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 1hge67l9powswui1m06idd|24313fcc, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 1hge67l9powswui1m06idd|24313fcc, idleConnectionTestPeriod -> 0, initialPoolSize -> 3, jdbcUrl -> jdbc:mysql://localhost:3306/seckill?useUnicode=true&characterEncoding=utf-8, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 0, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 30, maxStatements -> 0, maxStatementsPerConnection -> 0, minPoolSize -> 10, numHelperThreads -> 3, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ]
         15:37:52.628 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
         15:37:52.634 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@51a9ad5e]
         15:37:52.643 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@4f4c4b1a] will be managed by Spring
         15:37:52.647 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==>  Preparing: UPDATE seckill SET number = number-1 WHERE seckill_id=? AND start_time <= ? AND end_time >= ? AND number > 0;
         15:37:52.679 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==> Parameters: 1000(Long), 2017-07-19 15:37:52.622(Timestamp), 2017-07-19 15:37:52.622(Timestamp)
         15:37:52.698 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - <==    Updates: 1
         15:37:52.699 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@51a9ad5e]
         15:37:52.699 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@51a9ad5e] from current transaction
         15:37:52.700 [main] DEBUG o.s.d.S.insertSuccessKilled - ==>  Preparing: INSERT IGNORE INTO success_killed(seckill_id,user_phone,state) VALUES (?,?,0)
         15:37:52.701 [main] DEBUG o.s.d.S.insertSuccessKilled - ==> Parameters: 1000(Long), 15957192523(Long)
         15:37:52.706 [main] DEBUG o.s.d.S.insertSuccessKilled - <==    Updates: 1
         15:37:52.713 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@51a9ad5e]
         15:37:52.713 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@51a9ad5e] from current transaction
         15:37:52.715 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==>  Preparing: SELECT sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" FROM success_killed sk INNER JOIN seckill s ON sk.seckill_id=s.seckill_id WHERE sk.seckill_id=? and sk.user_phone=?
         15:37:52.715 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==> Parameters: 1000(Long), 15957192523(Long)
         15:37:52.735 [main] DEBUG o.s.d.S.queryByIdWithSeckill - <==      Total: 1
         15:37:52.744 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@51a9ad5e]
         15:37:52.745 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@51a9ad5e]
         15:37:52.745 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@51a9ad5e]
         15:37:52.745 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@51a9ad5e]
         七月 19, 2017 3:37:52 下午 org.springframework.context.support.GenericApplicationContext doClose
         信息: Closing org.springframework.context.support.GenericApplicationContext@3b0143d3: startup date [Wed Jul 19 15:37:50 CST 2017]; root of context hierarchy
         15:37:52.750 [main] INFO  o.seckill.service.SeckillServiceTest - result=org.seckill.dto.SeckillExecution@f14a7d4
         */
    }

}