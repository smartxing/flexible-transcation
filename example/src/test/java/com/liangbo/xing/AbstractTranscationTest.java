package com.liangbo.xing;

import com.liangbo.xing.flexibletranscation.FlexibleTranscationApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/24 下午8:38 xingliangbo Exp $
 */
@SpringBootTest(classes = FlexibleTranscationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractTranscationTest extends AbstractTestNGSpringContextTests {

}
