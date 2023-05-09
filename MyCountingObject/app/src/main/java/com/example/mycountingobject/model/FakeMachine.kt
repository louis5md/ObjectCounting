package com.example.mycountingobject.model

import javax.crypto.Mac

object FakeMachine {
    val dummyData = listOf<Machine>(
        Machine("Mesin 1", 10,10,0),
        Machine("Mesin 2", 10, 10, 1),
        Machine("Mesin 3", 10, 10, 2),
        Machine("Mesin 4", 20,18,3),
        Machine("Mesin 5", 15, 15,5),
        Machine("Mesin 6", 10,10,0),
        Machine("Mesin 7", 10, 10, 1),
        Machine("Mesin 8", 10, 10, 2),
        Machine("Mesin 9", 20,18,3),
        Machine("Mesin 10", 15, 15,5)
    )
}