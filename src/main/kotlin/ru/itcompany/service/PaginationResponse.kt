package ru.itcompany.service

import ru.itcompany.model.Meta

data class PaginationResponse<T>(
    val data: List<T>,
    val meta: Meta
)
{
    override fun equals(obj: Any?): Boolean
    {
        if (this === obj) return true
        if (obj == null || javaClass != obj.javaClass) return false
        return if (obj is PaginationResponse<*>)
        {
            this.data.containsAll(obj.data) and (this.meta.equals(obj.meta))
        }
        else
        {
            false
        }

    }
}