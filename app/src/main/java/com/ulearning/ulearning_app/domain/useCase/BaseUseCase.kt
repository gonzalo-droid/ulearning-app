package com.ulearning.ulearning_app.domain.useCase

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type, in Params> where Type : Any {
    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {},
    ) {
        val job = GlobalScope.async { run(params) }

        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    class None
}
