package ru.itcompany.model

import ru.itcompany.service.PaginationResponse

class Meta(
    val totalCounts: Int,
    val offset: Int,
    val limit: Int
)
{
    override fun equals(obj: Any?): Boolean
    {
        if (this === obj) return true
        if (obj == null || javaClass != obj.javaClass) return false
        return if (obj is Meta)
        {
            this.totalCounts.equals(obj.totalCounts) and this.limit.equals(obj.limit) and this.offset.equals(obj.offset)
        }
        else
        {
            false
        }

    }
}