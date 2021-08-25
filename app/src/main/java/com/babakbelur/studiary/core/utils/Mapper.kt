package com.babakbelur.studiary.core.utils

import com.babakbelur.studiary.core.data.remote.response.course.CourseItemResponse
import com.babakbelur.studiary.core.data.remote.response.evaluation.EvaluationItemResponse
import com.babakbelur.studiary.core.data.remote.response.target.TargetItemResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataLoginResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataUserResponse
import com.babakbelur.studiary.core.domain.model.*

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
    idTarget = this.idTarget
)

fun DataLoginResponse.toUser() = User(
    accessToken = this.accessToken,
    user = this.data.toListUser(),
    refreshToken = this.refreshToken
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