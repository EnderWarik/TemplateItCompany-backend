package ru.itcompany.model.enum

enum class StatusAppealEnum(status: String) {
    PendingReview("Pending review"),
    Reviewed("Reviewed"),
    PendingResponse("Pending response"),
    Closed("Closed")
}