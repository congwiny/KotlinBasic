package text

/**
 * 和扩展函数差不多，只是将fun改为了var/val
 * 然后提供了setter和getter方法
 * （没有字段支持，没有地方存储值，访问时是动态计算的，前面说过自定义访问器）
 */
var StringBuilder.lastChar2: Char
    get() = get(length - 1)
    set(value) {
        setCharAt(length - 1, value)
    }