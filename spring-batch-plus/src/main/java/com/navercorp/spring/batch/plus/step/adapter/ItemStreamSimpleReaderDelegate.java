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

package com.navercorp.spring.batch.plus.step.adapter;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * A simple delegate for {@link ItemStreamReader}.
 *
 * @since 1.1.0
 */
public interface ItemStreamSimpleReaderDelegate<T> {

	/**
	 * A delegate method for {@link ItemStreamReader#open(ExecutionContext)}.
	 *
	 * @param executionContext an execution context
	 */
	default void onOpenRead(@NonNull ExecutionContext executionContext) {
	}

	/**
	 * Read each item.
	 * @return an item to read. null if it's end of data.
	 *         Return contract is same as {@link org.springframework.batch.item.ItemReader<T>}
	 */
	@Nullable
	T read();

	/**
	 * A delegate method for {@link ItemStreamReader#update(ExecutionContext)}.
	 *
	 * @param executionContext an execution context
	 */
	default void onUpdateRead(@NonNull ExecutionContext executionContext) {
	}

	/**
	 * A delegate method for {@link ItemStreamReader#close()}.
	 */
	default void onCloseRead() {
	}
}
