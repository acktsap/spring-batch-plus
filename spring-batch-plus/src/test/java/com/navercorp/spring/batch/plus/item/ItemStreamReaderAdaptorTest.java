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

package com.navercorp.spring.batch.plus.item;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;

import reactor.core.publisher.Flux;

class ItemStreamReaderAdaptorTest {

	@Test
	void testOpen() {
		// given
		AtomicInteger onOpenReadCallCount = new AtomicInteger();
		AtomicInteger readFluxCallCount = new AtomicInteger();
		ItemStreamReader<Integer> itemStreamReaderAdaptor = ItemStreamReaderAdaptor.withDelegate(
			new ItemStreamReaderDelegate<Integer>() {

				@Override
				public void onOpenRead(ExecutionContext executionContext) {
					onOpenReadCallCount.incrementAndGet();
				}

				@NotNull
				@Override
				public Flux<Integer> readFlux(ExecutionContext executionContext) {
					readFluxCallCount.incrementAndGet();
					return Flux.empty();
				}

				@Override
				public void onUpdateRead(ExecutionContext executionContext) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void onCloseRead() {
					throw new UnsupportedOperationException();
				}
			});

		// when
		itemStreamReaderAdaptor.open(new ExecutionContext());

		// then
		assertThat(onOpenReadCallCount.get()).isEqualTo(1);
		assertThat(readFluxCallCount.get()).isEqualTo(1);
	}

	@Test
	void testRead() throws Exception {
		// given
		ItemStreamReader<Integer> itemStreamReaderAdaptor = ItemStreamReaderAdaptor.withDelegate(
			new ItemStreamReaderDelegate<Integer>() {
				private int count = 0;

				@Override
				public void onOpenRead(ExecutionContext executionContext) {
					// do nothing
				}

				@NotNull
				@Override
				public Flux<Integer> readFlux(ExecutionContext executionContext) {
					return Flux.generate(sink -> {
						if (count < 10) {
							sink.next(count);
							++count;
						} else {
							sink.complete();
						}
					});
				}

				@Override
				public void onUpdateRead(ExecutionContext executionContext) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void onCloseRead() {
					throw new UnsupportedOperationException();
				}
			});

		// when
		itemStreamReaderAdaptor.open(new ExecutionContext());
		List<Integer> items = new ArrayList<>();
		Integer item;
		while ((item = itemStreamReaderAdaptor.read()) != null) {
			items.add(item);
		}

		// then
		assertThat(items).hasSize(10);
	}

	@Test
	void testReadWithOpenShouldThrowsException() {
		// given
		ItemStreamReader<Integer> itemStreamReaderAdaptor = ItemStreamReaderAdaptor.withDelegate(
			new ItemStreamReaderDelegate<Integer>() {
				private int count = 0;

				@Override
				public void onOpenRead(ExecutionContext executionContext) {
					// do nothing
				}

				@NotNull
				@Override
				public Flux<Integer> readFlux(ExecutionContext executionContext) {
					return Flux.generate(sink -> {
						if (count < 10) {
							sink.next(count);
							++count;
						} else {
							sink.complete();
						}
					});
				}

				@Override
				public void onUpdateRead(ExecutionContext executionContext) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void onCloseRead() {
					throw new UnsupportedOperationException();
				}
			});

		// when, then
		assertThatThrownBy(
			itemStreamReaderAdaptor::read
		).hasMessageContaining("Flux isn't set. Call 'open' first.");
	}

	@Test
	void testUpdate() {
		// given
		AtomicInteger onUpdateCallCount = new AtomicInteger();
		ItemStreamReader<Integer> itemStreamReaderAdaptor = ItemStreamReaderAdaptor.withDelegate(
			new ItemStreamReaderDelegate<Integer>() {

				@Override
				public void onOpenRead(ExecutionContext executionContext) {
					throw new UnsupportedOperationException();
				}

				@NotNull
				@Override
				public Flux<Integer> readFlux(ExecutionContext executionContext) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void onUpdateRead(ExecutionContext executionContext) {
					onUpdateCallCount.incrementAndGet();
				}

				@Override
				public void onCloseRead() {
					throw new UnsupportedOperationException();
				}
			});

		// when
		itemStreamReaderAdaptor.update(new ExecutionContext());

		// then
		assertThat(onUpdateCallCount.get()).isEqualTo(1);
	}

	@Test
	void testClose() {
		// given
		AtomicInteger onCloseReadCallCount = new AtomicInteger();
		ItemStreamReader<Integer> itemStreamReaderAdaptor = ItemStreamReaderAdaptor.withDelegate(
			new ItemStreamReaderDelegate<Integer>() {

				@Override
				public void onOpenRead(ExecutionContext executionContext) {
					throw new UnsupportedOperationException();
				}

				@NotNull
				@Override
				public Flux<Integer> readFlux(ExecutionContext executionContext) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void onUpdateRead(ExecutionContext executionContext) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void onCloseRead() {
					onCloseReadCallCount.incrementAndGet();
				}
			});

		// when
		itemStreamReaderAdaptor.close();

		// then
		assertThat(onCloseReadCallCount.get()).isEqualTo(1);
	}
}
