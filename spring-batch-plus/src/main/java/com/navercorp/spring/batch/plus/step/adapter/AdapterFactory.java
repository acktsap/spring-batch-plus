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

import java.util.Objects;

import org.springframework.batch.core.scope.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.lang.NonNull;

/**
 * An adapter factory for these interfaces
 * {@link ItemStreamFluxReaderDelegate}
 * {@link ItemStreamIterableReaderDelegate}
 * {@link ItemStreamIteratorReaderDelegate}
 * {@link ItemStreamSimpleReaderDelegate}
 * {@link ItemProcessorDelegate}
 * {@link ItemStreamWriterDelegate}
 *
 * @since 1.1.0
 */
public final class AdapterFactory {

	/**
	 * Create an adapter which adapt {@link ItemStreamFluxReaderDelegate} to {@link ItemStreamReader}
	 * with {@link StepScope} bound proxy implementation. It creates new instance for every {@link StepScope}.
	 *
	 * @param <T> a read item type
	 * @param delegate a delegate
	 * @return an adapted ItemStreamReader
	 */
	public static <T> ItemStreamReader<T> itemStreamReader(@NonNull ItemStreamFluxReaderDelegate<T> delegate) {
		Objects.requireNonNull(delegate, "ItemStreamReader delegate is null");
		return StepScopeItemStreamReader.of(() -> ItemStreamFluxReaderAdapter.of(delegate));
	}

	/**
	 * Create an adapter which adapt {@link ItemStreamIterableReaderDelegate} to {@link ItemStreamReader}
	 * with {@link StepScope} bound proxy implementation. It creates new instance for every {@link StepScope}.
	 *
	 * @param <T> a read item type
	 * @param delegate a delegate
	 * @return an adapted ItemStreamReader
	 */
	public static <T> ItemStreamReader<T> itemStreamReader(@NonNull ItemStreamIterableReaderDelegate<T> delegate) {
		Objects.requireNonNull(delegate, "ItemStreamReader delegate is null");
		return StepScopeItemStreamReader.of(() -> ItemStreamIterableReaderAdapter.of(delegate));
	}

	/**
	 * Create an adapter which adapt {@link ItemStreamIteratorReaderDelegate} to {@link ItemStreamReader}
	 * with {@link StepScope} bound proxy implementation. It creates new instance for every {@link StepScope}.
	 *
	 * @param <T> a read item type
	 * @param delegate a delegate
	 * @return an adapted ItemStreamReader
	 */
	public static <T> ItemStreamReader<T> itemStreamReader(@NonNull ItemStreamIteratorReaderDelegate<T> delegate) {
		Objects.requireNonNull(delegate, "ItemStreamReader delegate is null");
		return StepScopeItemStreamReader.of(() -> ItemStreamIteratorReaderAdapter.of(delegate));
	}

	/**
	 * Create an adapter which adapt {@link ItemStreamSimpleReaderDelegate} to {@link ItemStreamReader}
	 * with {@link StepScope} bound proxy implementation. It creates new instance for every {@link StepScope}.
	 *
	 * @param <T> a read item type
	 * @param delegate a delegate
	 * @return an adapted ItemStreamReader
	 */
	public static <T> ItemStreamReader<T> itemStreamReader(@NonNull ItemStreamSimpleReaderDelegate<T> delegate) {
		Objects.requireNonNull(delegate, "ItemStreamReader delegate is null");
		return StepScopeItemStreamReader.of(() -> ItemStreamSimpleReaderAdapter.of(delegate));
	}

	/**
	 * Create an adapter which adapt {@link ItemProcessorDelegate} to {@link ItemProcessor}.
	 *
	 * @param <I> an item type to process
	 * @param <O> a processed item type
	 * @param delegate a delegate
	 * @return an adapted ItemProcessor
	 */
	public static <I, O> ItemProcessor<I, O> itemProcessor(@NonNull ItemProcessorDelegate<I, O> delegate) {
		return ItemProcessorAdapter.of(delegate);
	}

	/**
	 * Create an adapter which adapt {@link ItemStreamWriterDelegate} to {@link ItemStreamWriter}.
	 *
	 * @param <T> an item type to write
	 * @param delegate a delegate
	 * @return an adapted ItemStreamWriter
	 */
	public static <T> ItemStreamWriter<T> itemStreamWriter(@NonNull ItemStreamWriterDelegate<T> delegate) {
		return ItemStreamWriterAdapter.of(delegate);
	}

	private AdapterFactory() {
	}
}
