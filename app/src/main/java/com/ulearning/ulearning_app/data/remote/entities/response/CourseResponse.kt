package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName


data class CourseResponse(
    @SerializedName("amount")
    var amount: Int?= 0,
    @SerializedName("asynchronous_hour")
    var asynchronousHour: String?= "",
    @SerializedName("benefits")
    var benefits: String?= "",
    @SerializedName("category")
    var category: CategoryResponse?= null,
    @SerializedName("category_id")
    var categoryId: Int?= 0,
    @SerializedName("certificate")
    var certificate: Boolean?= false,
    @SerializedName("code")
    var code: String?= "",
    @SerializedName("coupon")
    var coupon: Any?= Any(),
    @SerializedName("coupon_id")
    var couponId: Any?= Any(),
    @SerializedName("currency")
    var currency: String?= "",
    @SerializedName("description_large")
    var descriptionLarge: String?= "",
    @SerializedName("description_short")
    var descriptionShort: String? = "",
    @SerializedName("discount")
    var discount: Any?= Any(),
    @SerializedName("duration")
    var duration: String?= "",
    @SerializedName("external_id")
    var externalId: String?= "",
    @SerializedName("external_link")
    var externalLink: Any?= Any(),
    @SerializedName("groups")
    var groups: List<GroupResponse>?= arrayListOf(),
    @SerializedName("id")
    var id: Int?= 0,
    @SerializedName("instructions")
    var instructions: String?= "",
    @SerializedName("is_free")
    var isFree: Boolean?= false,
    @SerializedName("is_purchased")
    var isPurchased: Boolean?= false,
    @SerializedName("is_shop")
    var isShop: Boolean?= false,
    @SerializedName("language_id")
    var languageId: Int?= 0,
    @SerializedName("lessons_count")
    var lessonsCount: Int?= 0,
    @SerializedName("main_image")
    var mainImage: MainImageResponse?= null,
    @SerializedName("methodology")
    var methodology: String?= "",
    @SerializedName("modality")
    var modality: String?= "",
    @SerializedName("nature")
    var nature: String?= "",
    @SerializedName("origin")
    var origin: String?= "",
    @SerializedName("politics_link")
    var politicsLink: String?= "",
    @SerializedName("presentation_link")
    var presentationLink: String?= "",
    @SerializedName("rating_average")
    var ratingAverage: Int?= 0,
    @SerializedName("rating_count")
    var ratingCount: Int?= 0,
    @SerializedName("record")
    var record: Boolean?= false,
    @SerializedName("schedule_link")
    var scheduleLink: Any?= Any(),
    @SerializedName("secondary_images")
    var secondaryImages: List<SecondaryImage>?= arrayListOf(),
    @SerializedName("self_study_hour")
    var selfStudyHour: String?= "",
    @SerializedName("slug")
    var slug: String?= "",
    @SerializedName("students_count")
    var studentsCount: Int?= 0,
    @SerializedName("suspended_at")
    var suspendedAt: Any?= Any(),
    @SerializedName("syllabus_link")
    var syllabusLink: String?= "",
    @SerializedName("synchronous_hour")
    var synchronousHour: String?= "",
    @SerializedName("target")
    var target: String?= "",
    @SerializedName("title")
    var title: String?= ""
)