package com.congwiny.basic

class User(val id: Int, val name: String, val address: String)

fun saveUser1(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException(
                "Cannot save user ${user.id}: empty name")
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException(
                "Cannot save user ${user.id}: empty address")
    }
}


fun saveUser2(user: User) {
    /**
     * 方法内的局部函数
     * 可摆脱代码重复，并保持清晰的代码结构
     */
    //声明一个局部函数来验证所有字段
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                    "Cannot save user ${user.id}: empty $fieldName")
        }
    }

    validate(user.name, "Name")
    validate(user.address, fieldName = "Address")
}

/**
 * 再次优化，把验证逻辑放到User类的扩展函数中
 */
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                    //可以直接访问User的属性
                    "Cannot save user $id: empty $fieldName")
        }
    }
    validate(value = name, fieldName = "Name")
    validate(value = address, fieldName = "Address")
}

fun saveUser(user: User){
    //调用User扩展函数
    user.validateBeforeSave()
}