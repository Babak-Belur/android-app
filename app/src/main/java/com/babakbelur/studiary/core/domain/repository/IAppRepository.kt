package com.babakbelur.studiary.core.domain.repository

import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.data.remote.response.BaseDeleteResponse
import com.babakbelur.studiary.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface IAppRepository {

    fun signIn(user: UserItem): Flow<ResultState<User>>

    fun signUp(user: UserItem): Flow<ResultState<UserItem>>

    fun getTargetByUserId(idUser: Int): Flow<ResultState<List<TargetItem>>>

    fun getTargetByTargetId(idTarget: Int): Flow<ResultState<List<TargetItem>>>

    fun addTarget(target: TargetItem): Flow<ResultState<List<TargetItem>>>

    fun deleteTarget(idUser: Int): Flow<ResultState<BaseDeleteResponse>>

    fun getEvaluationById(idUser: Int): Flow<ResultState<EvaluationItem>>

    fun addEvaluation(evaluation: EvaluationItem): Flow<ResultState<List<EvaluationItem>>>

    fun deleteEvaluation(idEvaluation: Int): Flow<ResultState<BaseDeleteResponse>>

    fun getCourseById(idCourse: Int): Flow<ResultState<Course>>

    fun addCourse(course: Course): Flow<ResultState<List<Course>>>

    fun deleteCourse(idCourse: Int): Flow<ResultState<BaseDeleteResponse>>
}