package com.babakbelur.studiary.core.data.remote

import android.util.Log
import com.babakbelur.studiary.core.data.remote.network.ApiResponse
import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.data.remote.response.BaseDeleteResponse
import com.babakbelur.studiary.core.data.remote.response.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class HandleApiResponse {

    protected fun <T> handleGetResponse(response: BaseResponse<T>): Flow<ApiResponse<T>> {
        return flow {
            val data = response.data
            if (data != null) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    protected fun <T> handlePostResponse(response: BaseAddResponse<T>): Flow<ApiResponse<List<T>>> {
        return flow {
            val data = response.data
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    protected fun handleDeleteResponse(response: BaseDeleteResponse): Flow<ApiResponse<BaseDeleteResponse>> {
        return flow {
            emit(ApiResponse.Success(response))
        }.catch { e ->
            Log.e("Error", e.message.toString())
        }.flowOn(Dispatchers.IO)
    }
}