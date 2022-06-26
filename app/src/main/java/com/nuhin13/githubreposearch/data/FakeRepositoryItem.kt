package com.nuhin13.githubreposearch.data

object FakeRepositoryItem {

    private val owner1 = Owner("http::/aaaaaa", "flutter1")
    private val owner2 = Owner("http::/aaaaaa", "flutter2")
    private val owner3 = Owner("http::/aaaaaa", "flutter3")
    private val owner4 = Owner("http::/aaaaaa", "flutter4")
    private val owner5 = Owner("http::/aaaaaa", "flutter5")
    private val owner6 = Owner("http::/aaaaaa", "flutter6")
    private val owner7 = Owner("http::/aaaaaa", "flutter7")
    private val owner8 = Owner("http::/aaaaaa", "flutter8")
    private val owner9 = Owner("http::/aaaaaa", "flutter9")
    private val owner10 = Owner("http::/aaaaaa", "flutter10")

    private val repo1 = RepositoryItem("test1", "testUrl", owner1, 1, "2022-09-29")
    private val repo2 = RepositoryItem("test2", "testUrl", owner2, 2, "2022-09-28")
    private val repo3 = RepositoryItem("test3", "testUrl", owner3, 3, "2022-09-27")
    private val repo4 = RepositoryItem("test4", "testUrl", owner4, 4, "2022-09-26")
    private val repo5 = RepositoryItem("test5", "testUrl", owner5, 5, "2022-09-25")
    private val repo6 = RepositoryItem("test6", "testUrl", owner6, 6, "2022-09-24")
    private val repo7 = RepositoryItem("test7", "testUrl", owner7, 7, "2022-09-23")
    private val repo8 = RepositoryItem("test8", "testUrl", owner8, 8, "2022-09-22")
    private val repo9 = RepositoryItem("test9", "testUrl", owner9, 9, "2022-09-21")
    private val repo10 = RepositoryItem("test10", "testUrl", owner10, 10, "2022-09-20")


    val repoList = RepositoryList(
        items = arrayListOf(
            repo1,
            repo2,
            repo3,
            repo4,
            repo5,
            repo6,
            repo7,
            repo8,
            repo9,
            repo10
        )
    )
}