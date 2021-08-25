package com.babakbelur.studiary.core.domain.usecase

import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.data.remote.response.BaseDeleteResponse
import com.babakbelur.studiary.core.domain.model.*
import com.babakbelur.studiary.core.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppInteractors @Inject constructor(private val appRepository: IAppRepository): IAppUseCase {

    override fun signIn(user: UserItem): Flow<ResultState<User>> {
        return appRepository.signIn(user)
    }

    override fun signUp(user: UserItem): Flow<ResultState<UserItem>> {
        return appRepository.signUp(user)
    }

    override fun getTargetByUserId(idUser: Int): Flow<ResultState<List<TargetItem>>> {
        return appRepository.getTargetByUserId(idUser)
    }

    override fun getTargetByTargetId(idTarget: Int): Flow<ResultState<List<TargetItem>>> {
        return appRepository.getTargetByTargetId(idTarget)
    }

    override fun addTarget(target: TargetItem): Flow<ResultState<List<TargetItem>>> {
        return appRepository.addTarget(target)
    }

    override fun deleteTarget(idUser: Int): Flow<ResultState<BaseDeleteResponse>> {
        return appRepository.deleteTarget(idUser)
    }

    override fun getEvaluationById(idUser: Int): Flow<ResultState<EvaluationItem>> {
        return appRepository.getEvaluationById(idUser)
    }

    override fun addEvaluation(evaluation: EvaluationItem): Flow<ResultState<List<EvaluationItem>>> {
        return appRepository.addEvaluation(evaluation)
    }

    override fun deleteEvaluation(idEvaluation: Int): Flow<ResultState<BaseDeleteResponse>> {
        return appRepository.deleteEvaluation(idEvaluation)
    }

    override fun getCourseById(idCourse: Int): Flow<ResultState<Course>> {
        return appRepository.getCourseById(idCourse)
    }

    override fun addCourse(course: Course): Flow<ResultState<List<Course>>> {
        return appRepository.addCourse(course)
    }

    override fun deleteCourse(idCourse: Int): Flow<ResultState<BaseDeleteResponse>> {
        return appRepository.deleteCourse(idCourse)
    }
}