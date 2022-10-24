/*
 * Spring Batch Plus
 *
 * Copyright 2022-present NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.spring.batch.plus.sample.step.creation

import com.navercorp.spring.batch.plus.kotlin.configuration.BatchDsl
import org.springframework.batch.core.Job
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class TestJobConfig(
    private val batch: BatchDsl
) {

    @Bean
    open fun testJob(): Job = batch {
        job("testJob") {
            // within job
            step("testStep1") {
                tasklet { _, _ -> RepeatStatus.FINISHED }
            }
            step("testStep2") {
                tasklet { _, _ -> RepeatStatus.FINISHED }
            }
            flow(testFlow())
            step(testStep())
        }
    }

    @Bean
    open fun testStep() = batch {
        step("testStep") {
            tasklet { _, _ -> RepeatStatus.FINISHED }
        }
    }

    @Bean
    open fun testFlow() = batch {
        flow("testFlow") {
            // within flow
            step("flowStep1") {
                tasklet { _, _ -> RepeatStatus.FINISHED }
            }
            step("flowStep2") {
                tasklet { _, _ -> RepeatStatus.FINISHED }
            }
        }
    }
}
