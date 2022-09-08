package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Course(
    var amount: Int?= 0,
    var asynchronousHour: String?= "",
    var benefits: String?= "",
    var category: Category?= null,
    var categoryId: Int?= 0,
    var certificate: Boolean?= false,
    var code: String?= "",
    var coupon: Any?= null,
    var couponId: Any?= null,
    var currency: String?= "",
    var descriptionLarge: String?= "",
    var descriptionShort: String?= "",
    var discount: Any?= null,
    var duration: String?= "",
    var externalId: String?= "",
    var externalLink: Any?= null,
    var groups: List<Group>?= arrayListOf(),
    var id: Int?= 0,
    var instructions: String?= "",
    var isFree: Boolean?= false,
    var isPurchased: Boolean?= false,
    var isShop: Boolean?= false,
    var languageId: Int?= 0,
    var lessonsCount: Int?= 0,
    var mainImage: MainImage?= null,
    var methodology: String?= "",
    var modality: String?= "",
    var nature: String?= "",
    var origin: String?= "",
    var politicsLink: String?= "",
    var presentationLink: String?= "",
    var ratingAverage: Int?= 0,
    var ratingCount: Int?= 0,
    var record: Boolean?= false,
    var scheduleLink: Any?= null,
    var secondaryImages: List<SecondaryImage>?= arrayListOf(),
    var selfStudyHour: String?= "",
    var slug: String?= "",
    var studentsCount: Int?= 0,
    var suspendedAt: Any?= null,
    var syllabusLink: String?= "",
    var synchronousHour: String?= "",
    var target: String?= "",
    var title: String?= ""
) : Serializable {

    fun formatAsynchronousHour() : String {
        return  "${this.asynchronousHour.toString()} hr"
    }

    fun formatModality() : String {
        return when (this.modality) {
            "virtual" -> "Virtual"
            "onsite" -> "En sitio"
            "blend" -> "Semipresencial"
            "recorded" -> "Grabado"
            "online" -> "Virtual"
            "self_learning" -> "Autoaprendizaje"
            "teacher" -> "Con docente"
            "platform" -> "En Plataforma"
            "lms" -> "LMS"
            else -> ""
        }
    }
}