package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.*
import com.ulearning.ulearning_app.domain.model.*
import javax.inject.Singleton

@Singleton
class CourseMapperImpl : CourseMapper {
    private suspend fun listCoursePackageItemToDomain(data: List<LearningPackageItemResponse>): List<LearningPackageItem> {
        return data.map {
            LearningPackageItem(
                course = it.course?.let { course -> courseToDomain(course) },
                courseId = it.courseId,
                id = it.id,
                isRequired = it.isRequired,
                learningPackageId = it.learningPackageId
            )
        }
    }

    override suspend fun subscriptionToDomain(data: SubscriptionResponse): Subscription {
        return data.let {
            Subscription(
                course = if (it.courseId != null) Course(
                    category = if (it.course?.category != null) Category(
                        color = it.course?.category?.color,
                        description = it.course?.category?.description,
                        id = it.course?.category?.id,
                        name = it.course?.category?.name,
                        type = it.course?.category?.type
                    ) else null,
                    categoryId = it.course?.categoryId,
                    descriptionLarge = it.course?.descriptionLarge,
                    descriptionShort = it.course?.descriptionShort,
                    mainImage = if (it.course?.mainImage != null) MainImage(
                        originalUrl = it.course?.mainImage?.originalUrl,
                        previewUrl = it.course?.mainImage?.previewUrl,
                    ) else null,
                    title = it.course?.title,
                    id = it.course?.id,
                    lessonsCount = it.course?.lessonsCount,
                    modality = it.course?.modality,
                    asynchronousHour = it.course?.asynchronousHour,
                    amount = 0,
                    benefits = null,
                    certificate = it.course?.certificate,
                    code = null,
                    currency = null,
                    duration = null,
                    externalId = null,
                    externalLink = null,
                    groups = listOf(),
                    instructions = null,
                    languageId = null,
                    methodology = null,
                    nature = null,
                    origin = null,
                    politicsLink = null,
                    presentationLink = null,
                    ratingAverage = null,
                    ratingCount = null,
                    record = it.course?.record,
                    selfStudyHour = it.course?.selfStudyHour,
                    slug = null,
                    studentsCount = null,
                    syllabusLink = it.course?.syllabusLink,
                    synchronousHour = it.course?.synchronousHour,
                    target = null,
                ) else null,
                user = User(
                    name = it.userResponse?.name,
                ),
                courseId = it.courseId,
                group = if (it.groupId != null) Group(
                    id = it.group?.id,
                    name = it.group?.name,
                    teachers = if (!it.group?.teachers.isNullOrEmpty()) listTeacherToDomain(it.group?.teachers) else arrayListOf(),
                ) else null,
                groupId = it.groupId,
                isFinished = it.isFinished,
                amount = it.amount,
                hasCertificate = it.hasCertificate,
                hasDegree = it.hasDegree,
                hasRecord = it.hasRecord,
                id = it.id,
                purchasedCertificate = it.purchasedCertificate,
                purchasedRecord = it.purchasedRecord,
                status = it.status,
                timeSession = it.timeSession,
                type = it.status,
                userId = it.userId,
                learningPackageId = it.learningPackageId,
                learningPackage = LearningPackage(
                    amount = null,
                    code = null,
                    descriptionLarge = it.learningPackage?.descriptionLarge,
                    descriptionShort = it.learningPackage?.descriptionShort,
                    id = it.learningPackage?.id,
                    isShop = null,
                    mainImage = it.learningPackage?.mainImage?.let { mainImage ->
                        MainImage(
                            originalUrl = mainImage.originalUrl,
                            previewUrl = mainImage.previewUrl,
                        )
                    },
                    title = it.learningPackage?.title,
                    type = it.learningPackage?.type,
                    items = it.learningPackage?.items?.let { items ->
                        listCoursePackageItemToDomain(items)
                    },
                ),
            )
        }
    }

    override suspend fun listSubscriptionsToDomain(data: List<SubscriptionResponse>): List<Subscription> {
        return data.map {
            Subscription(
                course = if (it.courseId != null) Course(
                    category = if (it.course?.category != null) Category(
                        color = it.course?.category?.color,
                        description = it.course?.category?.description,
                        id = it.course?.category?.id,
                        name = it.course?.category?.name,
                        type = it.course?.category?.type
                    ) else null,
                    categoryId = it.course?.categoryId,
                    descriptionLarge = it.course?.descriptionLarge,
                    descriptionShort = it.course?.descriptionShort,
                    mainImage = if (it.course?.mainImage != null) MainImage(
                        originalUrl = it.course?.mainImage?.originalUrl,
                        previewUrl = it.course?.mainImage?.previewUrl,
                    ) else null,
                    title = it.course?.title,
                    id = it.course?.id,
                    lessonsCount = it.course?.lessonsCount,
                    modality = it.course?.modality,
                    asynchronousHour = it.course?.asynchronousHour,
                    amount = 0,
                    benefits = null,
                    certificate = it.course?.certificate,
                    code = null,
                    currency = null,
                    duration = null,
                    externalId = null,
                    externalLink = null,
                    groups = listOf(),
                    instructions = null,
                    languageId = null,
                    methodology = null,
                    nature = null,
                    origin = null,
                    politicsLink = null,
                    presentationLink = null,
                    ratingAverage = null,
                    ratingCount = null,
                    record = it.course?.record,
                    selfStudyHour = it.course?.selfStudyHour,
                    slug = null,
                    studentsCount = null,
                    syllabusLink = it.course?.syllabusLink,
                    synchronousHour = it.course?.synchronousHour,
                    target = null,
                ) else null,
                user = User(
                    name = it.userResponse?.name,
                ),
                courseId = it.courseId,
                group = if (it.groupId != null) Group(
                    id = it.group?.id,
                    name = it.group?.name,
                    teachers = if (!it.group?.teachers.isNullOrEmpty()) listTeacherToDomain(it.group?.teachers) else arrayListOf(),
                ) else null,
                groupId = it.groupId,
                isFinished = it.isFinished,
                amount = it.amount,
                hasCertificate = it.hasCertificate,
                hasDegree = it.hasDegree,
                hasRecord = it.hasRecord,
                id = it.id,
                purchasedCertificate = it.purchasedCertificate,
                purchasedRecord = it.purchasedRecord,
                status = it.status,
                timeSession = it.timeSession,
                type = it.status,
                userId = it.userId,
                learningPackage = it.learningPackage?.let { learningPackage ->
                    LearningPackage(
                        amount = learningPackage.amount,
                        code = learningPackage.code,
                        descriptionLarge = learningPackage.descriptionLarge,
                        descriptionShort = learningPackage.descriptionShort,
                        id = learningPackage.id,
                        isShop = learningPackage.isShop,
                        mainImage = learningPackage.mainImage?.let { mainImage ->
                            MainImage(
                                originalUrl = mainImage.originalUrl,
                                previewUrl = mainImage.previewUrl
                            )
                        },
                        title = it.learningPackage.title,
                        type = it.learningPackage.type,
                    )
                }
            )
        }
    }

    suspend fun courseToDomain(data: CourseResponse): Course {
        return data.let {
            Course(
                category = if (it.category != null) Category(
                    color = it.category?.color,
                    description = it.category?.description,
                    id = it.category?.id,
                    name = it.category?.name,
                    type = it.category?.type
                ) else null,
                categoryId = it.categoryId,
                descriptionLarge = it.descriptionLarge,
                descriptionShort = it.descriptionShort,
                mainImage = if (it.mainImage != null) MainImage(
                    originalUrl = it.mainImage?.originalUrl,
                    previewUrl = it.mainImage?.previewUrl,
                ) else null,
                title = it.title,
                id = it.id,
                lessonsCount = it.lessonsCount,
                modality = it.modality,
                asynchronousHour = it.asynchronousHour,
                amount = 0,
                benefits = null,
                certificate = null,
                code = null,
                currency = null,
                duration = null,
                externalId = null,
                externalLink = null,
                groups = listOf(),
                instructions = null,
                languageId = null,
                methodology = null,
                nature = null,
                origin = null,
                politicsLink = null,
                presentationLink = null,
                ratingAverage = null,
                ratingCount = null,
                record = null,
                selfStudyHour = it.selfStudyHour,
                slug = null,
                studentsCount = null,
                syllabusLink = null,
                synchronousHour = it.synchronousHour,
                target = null,
            )
        }
    }

    override suspend fun listCourseToDomain(data: List<CourseResponse>): List<Course> {
        return data.map {
            Course(
                category = if (it.category != null) Category(
                    color = it.category?.color,
                    description = it.category?.description,
                    id = it.category?.id,
                    name = it.category?.name,
                    type = it.category?.type
                ) else null,
                categoryId = it.categoryId,
                descriptionLarge = it.descriptionLarge,
                descriptionShort = it.descriptionShort,
                mainImage = if (it.mainImage != null) MainImage(
                    originalUrl = it.mainImage?.originalUrl,
                    previewUrl = it.mainImage?.previewUrl,
                ) else null,
                title = it.title,
                id = it.id,
                lessonsCount = it.lessonsCount,
                modality = it.modality,
                asynchronousHour = it.asynchronousHour,
                amount = 0,
                benefits = null,
                certificate = null,
                code = null,
                currency = null,
                duration = null,
                externalId = null,
                externalLink = null,
                groups = listOf(),
                instructions = null,
                languageId = null,
                methodology = null,
                nature = null,
                origin = null,
                politicsLink = null,
                presentationLink = null,
                ratingAverage = null,
                ratingCount = null,
                record = null,
                selfStudyHour = it.selfStudyHour,
                slug = null,
                studentsCount = null,
                syllabusLink = null,
                synchronousHour = it.synchronousHour,
                target = null,
            )
        }
    }

    override suspend fun listCoursePercentageToDomain(data: List<CoursePercentageResponse>): List<CoursePercentage> {
        return data.map {
            CoursePercentage(
                percentage = it.percentage, courseId = it.courseId
            )
        }
    }

    override suspend fun myFilesToDomain(data: List<FileItemResponse>): List<FileItem> {
        return data.map {
            FileItem(
                bucket = it.bucket,
                code = it.code,
                courseId = it.courseId,
                fileInfo = it.fileInfoResponse?.let { info -> fileInfoToDomain(info) },
                hash = it.hash,
                id = it.id,
                name = it.name,
                number = it.number,
                type = it.type,
                userId = it.userId,
                year = it.year
            )
        }
    }

    override suspend fun myFileToDomain(data: FileItemResponse): FileItem {
        return data.let {
            FileItem(
                bucket = it.bucket,
                code = it.code,
                courseId = it.courseId,
                fileInfo = it.fileInfoResponse?.let { info -> fileInfoToDomain(info) },
                hash = it.hash,
                id = it.id,
                name = it.name,
                number = it.number,
                type = it.type,
                userId = it.userId,
                year = it.year
            )
        }
    }

    private fun fileInfoToDomain(data: FileInfoResponse): FileInfo {
        return data.let {
            FileInfo(
                date = it.date,
                email = it.email,
                hours = it.hours,
                name = it.name,
                period = it.period,
                rating = it.rating,
                sendMail = it.sendMail,
                title = it.title,
                topics = it.topics,
            )
        }
    }

    override suspend fun checkAvailableFilesToDomain(data: CheckAvailableFilesResponse): CheckAvailableFiles {
        return data.let {
            CheckAvailableFiles(
                certificate = it.certificate, record = it.record
            )
        }
    }

    override suspend fun downloadFileToDomain(data: DownloadFileResponse): DownloadFile {
        return data.let {
            DownloadFile(
                file = it.file, filename = it.filename, fileUrl = it.fileUrl
            )
        }
    }

    private fun listTeacherToDomain(data: List<TeacherResponse>?): List<Teacher>? {
        return data?.map {
            Teacher(
                // avatar = it.avatar,
                firstName = it.firstName,
                id = it.id,
                lastName = it.lastName,
                subtype = it.subtype,
                type = it.type
            )
        }
    }
}
