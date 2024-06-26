# Spring Batch Plus

Spring Batch Plus provides useful classes available in [Spring Batch](https://github.com/spring-projects/spring-batch). Being originally designed to be served as a repository for the Kotlin DSL, it has been expanded to cover functions useful for Spring Batch.

## User guide

The Kotlin DSL helps you declaratively declare a `Job`, `Step`, and `Flow` by using Kotlin’s [type-safe builders](https://kotlinlang.org/docs/type-safe-builders.html), without using `JobBuilder`, `StepBuilder`, or `FlowBuilder`. `ClearRunIdIncrementer` is a class that can replace the `RunIdIncrementer` of Spring Batch which reuses JobParameters in the previous JobExecution. `DeleteMetadataJob` is a `Job` that deletes old metadata. `ItemStreamFluxReaderProcessorWriter`, `ItemStreamIterableReaderProcessorWriter`, `ItemStreamIteratorReaderProcessorWriter` and `ItemStreamSimpleReaderProcessorWriter` helps you implement `ItemStreamReader`, `ItemProcessor`, and `ItemStreamWriter` as a single class.

- [Kotlin DSL](./configuration/kotlin-dsl/README.md)
- [ClearRunIdIncrementer](./job/clear-run-id-incrementer.md)
- [DeleteMetadataJob](./job/delete-metadata-job.md)
- [ItemStreamFluxReaderProcessorWriter](./step/item-stream-flux-reader-processor-writer.md)
- [ItemStreamIterableReaderProcessorWriter](./step/item-stream-iterable-reader-processor-writer.md)
- [ItemStreamIteratorReaderProcessorWriter](./step/item-stream-iterator-reader-processor-writer.md)
- [ItemStreamSimpleReaderProcessorWriter](./step/item-stream-simple-reader-processor-writer.md)

## Code samples

See [sample](../../spring-batch-plus-sample/) for various code samples.
