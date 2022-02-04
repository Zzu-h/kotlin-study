package com.example.kotlinproject
var n = 0
val visited = BooleanArray(16){false}
val city = IntArray(16){ -1 }
var minCost = 987654321
val minCity = IntArray(16){ -1 }

// node is start point
fun tsp(node: Int, costSum: Int, count: Int,  W : Array<IntArray>){
    visited[node] = true
    city[count-1] = node
    if(count == n){
        if(costSum < minCost) {
            for (i in 0..(n - 1)) {
                minCity[i] = city[i]
            }
            minCost = costSum
        }
        visited[node] = false
        city[count-1] = -1
        return
    }
    for(i in 0..(n-1)){
        if(!visited[i] && W[node][i] != 0)
            tsp(i, costSum+W[node][i], count+1, W)
    }
    visited[node] = false
    city[count-1] = -1
}

fun main() = with(System.out.bufferedWriter()){
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()
    var W = Array(n){
        br.readLine().split(' ').map{it.toInt()}.toIntArray()
    }
    tsp(n-1,0,1, W)
    for(i in 0..(n-1)){
        print("${minCity[i]} ")
    }
    println(" : ${minCost} ")
}
/*
코루틴
mv vm
4
0 10 15 20
5 0 9 10
6 13 0 12
8 8 9 0

3 0 1 2  : 27
3 0 2 1  : 36
3 1 0 2  : 28
3 1 2 0  : 23
3 2 0 1  : 25
3 2 1 0  : 27
*/

/*

*/
