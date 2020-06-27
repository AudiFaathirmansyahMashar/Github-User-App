package id.sharekom.githubuser.models.searchdata

data class SearchData(
    val incomplete_results: Boolean,
    val items: List<SearchDataItem>,
    val total_count: Long
)