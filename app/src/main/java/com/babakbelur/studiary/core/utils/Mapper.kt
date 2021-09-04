package com.babakbelur.studiary.core.utils

import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.data.remote.response.BaseListResponse
import com.babakbelur.studiary.core.data.remote.response.BaseObjectResponse
import com.babakbelur.studiary.core.data.remote.response.course.CourseItemResponse
import com.babakbelur.studiary.core.data.remote.response.evaluation.DataEvaluationResponse
import com.babakbelur.studiary.core.data.remote.response.evaluation.EvaluationItemResponse
import com.babakbelur.studiary.core.data.remote.response.predict.PredictResponse
import com.babakbelur.studiary.core.data.remote.response.target.DataTargetResponse
import com.babakbelur.studiary.core.data.remote.response.target.TargetItemResponse
import com.babakbelur.studiary.core.data.remote.response.target.TargetResponse
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

fun BaseObjectResponse<DataLoginResponse>.toBaseResponseOfUser(): BaseObjectResponse<User> {
    return BaseObjectResponse(this.data.toUser())
}

fun BaseObjectResponse<DataLoginResponse>.toResultModelOfUser(): ResultModel<User> {
    return ResultModel(this.data.toUser())
}

fun BaseObjectResponse<DataEvaluationResponse>.toResultModelOfEvaluation(): ResultModel<Evaluation> {
    return ResultModel(this.data.toEvaluation())
}

fun BaseAddResponse<DataUserResponse>.toResultAddModelOfListUserItem(): ResultAddModel<UserItem> {
    return ResultAddModel(this.data.toListUser())
}

fun BaseAddResponse<TargetItemResponse>.toResultAddModelOfListTargetItem(): ResultAddModel<TargetItem> {
    return ResultAddModel(this.data.toListTargetItem())
}

fun BaseAddResponse<EvaluationItemResponse>.toResultAddModelOfListEvaluationItem(): ResultAddModel<EvaluationItem> {
    return ResultAddModel(this.data.toListEvaluationItem())
}

fun BaseAddResponse<CourseItemResponse>.toResultAddModelOfListCourse(): ResultAddModel<Course> {
    return ResultAddModel(this.data.toListCourse())
}

fun BaseListResponse<PredictResponse>.toResultListModelOfPredict(): ResultListModel<Predict> {
    return ResultListModel(this.data.toListPredict())
}

fun BaseListResponse<CourseItemResponse>.toResultListModelOfCourse(): ResultListModel<Course> {
    return ResultListModel(this.data.toListCourse())
}

fun BaseObjectResponse<DataTargetResponse>.toResultModelOfTarget(): ResultModel<Target> {
    return ResultModel(this.data.toTarget())
}

fun PredictResponse.toPredict() = Predict(
    freeTime = this.freeTime,
    preTestScore = this.preTestScore,
    grade = this.grade,
    predictedScore = this.predictedScore,
    studyTime = this.studyTime
)

fun TargetResponse.toResultTarget() = ResultTarget(data.toListTargetItem())

fun DataEvaluationResponse.toEvaluation() = Evaluation(
    evaluation = this.evaluation.toListEvaluationItem(),
    idUser = this.idUser,
    name = this.name,
    role = this.role,
    username = this.username
)

fun DataTargetResponse.toTarget() = Target(
    idUser = this.idUser,
    name = this.name,
    role = this.role,
    target = this.target.toListTargetItem(),
    username = this.username
)

fun DataLoginResponse.toUser() = User(
    accessToken = this.accessToken,
    user = this.data.toListUser(),
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
    preTestScore = this.preTestScore,
    gradeTarget = this.gradeTarget,
    idCourse = this.idCourse,
    course = this.course.toListCourse(),
    targetTime = this.targetTime,
    idTarget = this.idTarget,
    idUser = this.idUser
)

fun TargetItem.toTargetItemResponse() = TargetItemResponse(
    achieved = this.achieved,
    preTestScore = this.preTestScore,
    course = this.course.toListCourseItemResponse(),
    gradeTarget = this.gradeTarget,
    idCourse = this.idCourse,
    idTarget = this.idTarget,
    targetTime = this.targetTime,
    idUser = this.idUser
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

fun List<PredictResponse>.toListPredict(): List<Predict> {
    val data = ArrayList<Predict>()
    this.map {
        val predict = Predict(
            freeTime = it.freeTime,
            preTestScore = it.preTestScore,
            grade = it.grade,
            predictedScore = it.predictedScore,
            studyTime = it.studyTime
        )
        data.add(predict)
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
            preTestScore = it.preTestScore,
            course = it.course.toListCourseItemResponse(),
            gradeTarget = it.gradeTarget,
            idCourse = it.idCourse,
            idTarget = it.idTarget,
            targetTime = it.targetTime,
            idUser = it.idUser
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
            preTestScore = it.preTestScore,
            course = it.course.toListCourse(),
            gradeTarget = it.gradeTarget,
            idCourse = it.idCourse,
            idTarget = it.idTarget,
            targetTime = it.targetTime,
            idUser = it.idUser
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