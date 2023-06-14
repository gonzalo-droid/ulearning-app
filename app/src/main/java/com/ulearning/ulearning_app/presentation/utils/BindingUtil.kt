package com.ulearning.ulearning_app.presentation.utils

import android.util.Log
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign

class BindingUtil {

    companion object {

        fun reducerFailure(failure: Failure): MessageDesign {
            return when (failure) {
                is Failure.UnauthorizedOrForbidden -> MessageDesign.Builder()
                    .idMessage(R.string.error_user_message)
                    .state(R.string.fail).build()

                is Failure.None -> MessageDesign.Builder()
                    .idMessage(R.string.error_user_message)
                    .state(R.string.fail).build()

                is Failure.NetworkConnectionLostSuddenly -> MessageDesign.Builder()
                    .idMessage(R.string.error_no_internet)
                    .state(R.string.fail).build()

                is Failure.NoNetworkDetected -> MessageDesign.Builder()
                    .idMessage(R.string.error_no_internet)
                    .state(R.string.fail).build()

                is Failure.SSLError -> MessageDesign.Builder()
                    .idMessage(R.string.error_no_internet)
                    .state(R.string.fail).build()

                is Failure.TimeOut -> {
                    MessageDesign.Builder()
                        .idMessage(R.string.error_server)
                        .state(R.string.fail).build()
                }

                is Failure.ServerBodyError -> MessageDesign.Builder()
                    .idMessage(R.string.error_user_message)
                    .state(R.string.fail).build()

                is Failure.DataToDomainMapperFailure -> MessageDesign.Builder()
                    .idMessage(R.string.error_user_message)
                    .state(R.string.fail).build()

                is Failure.ServiceUncaughtFailure -> {
                    Log.d("Error serve", failure.uncaughtFailureMessage)
                    MessageDesign.Builder()
                        .idMessage(R.string.error_server)
                        .state(R.string.fail).build()
                }

                is Failure.DefaultError -> MessageDesign.Builder()
                    .idMessage(failure.idMessage)
                    .state(R.string.fail).build()
            }
        }
    }
}
