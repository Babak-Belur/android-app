package com.babakbelur.studiary.core.data

import com.babakbelur.studiary.core.data.remote.RemoteDataSource
import com.babakbelur.studiary.core.data.remote.network.ApiResponse
import com.babakbelur.studiary.core.data.remote.response.BaseDeleteResponse
import com.babakbelur.studiary.core.domain.model.*
import com.babakbelur.studiary.core.domain.repository.IAppRepository
import com.babakbelur.studiary.core.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AppRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IAppRepository {

    override fun signIn(user: UserItem): Flow<ResultState<User>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.signIn(user.toDataUserResponse()).collect { apiResponse ->
                when (apiResponse) {
                    ApiResponse.Empty -> emit(ResultState.Success(User()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiResponse.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiResponse.data.toUser()))
                }
            }
        } as Flow<ResultState<User>>
    }

    override fun signUp(user: UserItem): Flow<ResultState<UserItem>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.signUp(user.toDataUserResponse()).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success(UserItem()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data.toUserItem()))
                }
            }
        } as Flow<ResultState<UserItem>>
    }

    override fun getTargetByUserId(idUser: Int): Flow<ResultState<List<TargetItem>>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.getTargetByUserId(idUser).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success<List<TargetItem>>(emptyList()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data.toListTargetItem()))
                }
            }
        } as Flow<ResultState<List<TargetItem>>>
    }

    override fun getTargetByTargetId(idTarget: Int): Flow<ResultState<List<TargetItem>>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.getTargetByTargetId(idTarget).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success<List<TargetItem>>(emptyList()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data.toListTargetItem()))
                }
            }
        } as Flow<ResultState<List<TargetItem>>>
    }

    override fun addTarget(target: TargetItem): Flow<ResultState<List<TargetItem>>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.addTarget(target.toTargetItemResponse()).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success<List<TargetItem>>(emptyList()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data.toListTargetItem()))
                }
            }
        } as Flow<ResultState<List<TargetItem>>>
    }

    override fun deleteTarget(idUser: Int): Flow<ResultState<BaseDeleteResponse>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.deleteTarget(idUser).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success(BaseDeleteResponse()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data))
                }
            }
        } as Flow<ResultState<BaseDeleteResponse>>
    }

    override fun getEvaluationById(idUser: Int): Flow<ResultState<EvaluationItem>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.getEvaluationById(idUser).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success(EvaluationItem()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data.toEvaluationItem()))
                }
            }
        } as Flow<ResultState<EvaluationItem>>
    }

    override fun addEvaluation(evaluation: EvaluationItem): Flow<ResultState<List<EvaluationItem>>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.addEvaluation(evaluation.toEvaluationItemResponse())
                .collect { apiState ->
                    when (apiState) {
                        ApiResponse.Empty -> emit(
                            ResultState.Success<List<EvaluationItem>>(
                                emptyList()
                            )
                        )
                        is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                        is ApiResponse.Success -> emit(ResultState.Success(apiState.data.toListEvaluationItem()))
                    }
                }
        } as Flow<ResultState<List<EvaluationItem>>>
    }

    override fun deleteEvaluation(idEvaluation: Int): Flow<ResultState<BaseDeleteResponse>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.deleteEvaluation(idEvaluation).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success(BaseDeleteResponse()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data))
                }
            }
        } as Flow<ResultState<BaseDeleteResponse>>
    }

    override fun getCourseById(idCourse: Int): Flow<ResultState<Course>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.getCourseById(idCourse).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success(Course()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data.toCourse()))
                }
            }
        } as Flow<ResultState<Course>>
    }

    override fun addCourse(course: Course): Flow<ResultState<List<Course>>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.addCourse(course.toCourseItemResponse()).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success<List<Course>>(emptyList()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data.toListCourse()))
                }
            }
        } as Flow<ResultState<List<Course>>>
    }

    override fun deleteCourse(idCourse: Int): Flow<ResultState<BaseDeleteResponse>> {
        return flow {
            emit(ResultState.Loading())
            remoteDataSource.deleteCourse(idCourse).collect { apiState ->
                when (apiState) {
                    ApiResponse.Empty -> emit(ResultState.Success(BaseDeleteResponse()))
                    is ApiResponse.Error -> emit(ResultState.Error(null, apiState.message))
                    is ApiResponse.Success -> emit(ResultState.Success(apiState.data))
                }
            }
        } as Flow<ResultState<BaseDeleteResponse>>
    }
}