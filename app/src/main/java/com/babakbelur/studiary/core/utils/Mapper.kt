package com.babakbelur.studiary.core.utils

import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.data.remote.response.BaseResponse
import com.babakbelur.studiary.core.data.remote.response.course.CourseItemResponse
import com.babakbelur.studiary.core.data.remote.response.evaluation.EvaluationItemResponse
import com.babakbelur.studiary.core.data.remote.response.target.DataTargetResponse
import com.babakbelur.studiary.core.data.remote.response.target.TargetItemResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataLoginResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataUserResponse
import com.babakbelur.studiary.core.domain.model.*
import com.babakbelur.studiary.core.domain.model.Target
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@ExperimentalCoroutinesApi
object Mapper {

    suspend inline fun <T : Any> mapResultToData(resultState: ResultState<T>): ResultState<T>? =
        suspendCancellableCoroutine { task ->
            resultState.onSuccess {
                val data = ResultState.Success(it)
                try {
                    task.resume(data)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    task.resume(null)
                }
            }
        }

    inline fun <T : Any, U : Any> mapResult(
        resultState: ResultState<out T>,
        mapper: T.() -> U
    ): ResultState<U> {
        return when (resultState) {
            is ResultState.Success -> {
                val data = resultState.data
                val mapData = mapper.invoke(data)
                ResultState.Success(mapData)
            }
            is ResultState.Idle -> ResultState.Idle()
            is ResultState.Error -> ResultState.Error(resultState.throwable)
            is ResultState.Loading -> ResultState.Loading()
        }
    }
}

fun BaseResponse<DataLoginResponse>.toBaseResponseOfUser(): BaseResponse<User> {
    return BaseResponse(this.data.toUser())
}

fun BaseResponse<DataLoginResponse>.toResultModelOfUser(): ResultModel<User> {
    return ResultModel(this.data.toUser())
}

fun BaseAddResponse<DataUserResponse>.toBaseResponseOfListUserItem(): BaseAddResponse<UserItem> {
    return BaseAddResponse(this.data.toListUser())
}

fun BaseResponse<DataTargetResponse>.toResultModelOfTarget(): ResultModel<Target> {
    return ResultModel(this.data.toTarget())
}

fun DataTargetResponse.toTarget() = Target(
    idUser = this.idUser,
    name = this.name,
    role = this.role,
    target = this.target?.toListTargetItem(),
    username = this.username
)

fun DataLoginResponse.toUser() = User(
    accessToken = this.accessToken,
    user = this.data?.toListUser(),
    refreshToken = this.refreshToken
)

fun UserItem.toDataUserResponse() = DataUserResponse(
    idUser = this.idUser,
    name = this.name,
    role = this.role,
    username = this.username,
    password = this.password
)

fun DataUserResponse.toUserItem() = UserItem(
    idUser = this.idUser,
    name = this.name,
    role = this.role,
    username = this.username,
    password = this.password
)

fun TargetItemResponse.toTargetItem() = TargetItem(
    achieved = this.achieved,
    g1 = this.g1,
    gradeTarget = this.gradeTarget,
    idCourse = this.idCourse,
    course = this.course.toListCourse(),
    targetTime = this.targetTime,
    idTarget = this.idTarget,
    idUser = this.idUser
)

fun TargetItem.toTargetItemResponse() = TargetItemResponse(
    achieved = this.achieved,
    g1 = this.g1,
    course = this.course.toListCourseItemResponse(),
    gradeTarget = this.gradeTarget,
    idCourse = this.idCourse,
    idTarget = this.idTarget,
    targetTime = this.targetTime
)

fun EvaluationItem.toEvaluationItemResponse() = EvaluationItemResponse(
    date = this.date,
    freeTime = this.freeTime,
    studyTime = this.studyTime,
    grade = this.grade,
    idEvaluation = this.idEvaluation,
    target = this.target.toListTargetItemResponse(),
)

fun EvaluationItemResponse.toEvaluationItem() = EvaluationItem(
    date, freeTime, studyTime, grade
)

fun CourseItemResponse.toCourse() = Course(
    courseName, description, idCourse
)

fun Course.toCourseItemResponse() = CourseItemResponse(
    courseName, description, idCourse
)

fun List<EvaluationItemResponse>.toListEvaluationItem(): List<EvaluationItem> {
    val data = ArrayList<EvaluationItem>()
    this.map {
        val evaluationItem = EvaluationItem(
            date = it.date,
            freeTime = it.freeTime,
            studyTime = it.studyTime,
            grade = it.grade,
            idEvaluation = it.idEvaluation,
            target = it.target.toListTargetItem()
        )
        data.add(evaluationItem)
    }
    return data
}

fun List<Course>.toListCourseItemResponse(): List<CourseItemResponse> {
    val data = ArrayList<CourseItemResponse>()
    this.map {
        val courseItemResponse = CourseItemResponse(
            courseName = it.courseName,
            description = it.description,
            idCourse = it.idCourse
        )
        data.add(courseItemResponse)
    }
    return data
}

fun List<TargetItem>.toListTargetItemResponse(): List<TargetItemResponse> {
    val data = ArrayList<TargetItemResponse>()
    this.map {
        val targetItem = TargetItemResponse(
            achieved = it.achieved,
            g1 = it.g1,
            course = it.course.toListCourseItemResponse(),
            gradeTarget = it.gradeTarget,
            idCourse = it.idCourse,
            idTarget = it.idTarget,
            targetTime = it.targetTime
        )
        data.add(targetItem)
    }
    return data
}

fun List<TargetItemResponse>.toListTargetItem(): List<TargetItem> {
    val data = ArrayList<TargetItem>()
    this.map {
        val targetItem = TargetItem(
            achieved = it.achieved,
            g1 = it.g1,
            course = it.course.toListCourse(),
            gradeTarget = it.gradeTarget,
            idCourse = it.idCourse,
            idTarget = it.idTarget,
            targetTime = it.targetTime
        )
        data.add(targetItem)
    }
    return data
}

fun List<CourseItemResponse>.toListCourse(): List<Course> {
    val data = ArrayList<Course>()
    this.map {
        val course = Course(
            courseName = it.courseName,
            description = it.description,
            idCourse = it.idCourse
        )
        data.add(course)
    }
    return data
}

fun List<DataUserResponse>.toListUser(): List<UserItem> {
    val data = ArrayList<UserItem>()
    this.map {
        val user = UserItem(
            idUser = it.idUser,
            username = it.username,
            password = it.password,
            name = it.name,
            role = it.role
        )
        data.add(user)
    }
    return data
}