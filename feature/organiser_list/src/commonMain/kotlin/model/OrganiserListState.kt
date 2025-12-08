package model

data class OrganiserListState(
    val organisers : List<OrganiserUi>,
    val error : Throwable?
)