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

package com.navercorp.spring.batch.plus.sample.simple.readerwriter

import com.navercorp.spring.batch.plus.item.adapter.ItemStreamSimpleReaderWriter
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.Chunk
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
@StepScope
open class SampleTasklet(
    @Value("#{jobParameters['totalCount']}") private var totalCount: Long,
) : ItemStreamSimpleReaderWriter<Int> {
    private var count = 0

    override fun read(): Int? {
        return if (count < totalCount) {
            count++
        } else {
            null
        }
    }

    override fun write(chunk: Chunk<out Int>) {
        println(chunk.items)
    }
}