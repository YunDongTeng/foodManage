/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nochi.pet.manage;

import cn.stylefeng.roses.core.config.WebAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot方式启动类
 *  启动参数调优： -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1g -Xmx1g -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
 *              -Xmx：最大堆大小 -Xms初始化堆大小  -Xmn 年轻代堆大小
 */
@SpringBootApplication(exclude = {WebAutoConfiguration.class})
@EnableScheduling
public class FoodManageApplication {

    private final static Logger logger = LoggerFactory.getLogger(FoodManageApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FoodManageApplication.class, args);
        logger.info(FoodManageApplication.class.getSimpleName() + " is success!");
    }
}
