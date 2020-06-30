package io.pixeloutlaw.minecraft.spigot.garbage

import org.bukkit.Bukkit
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * Reflection utilities for NMS/CraftBukkit.
 */
object ReflectionUtil {
    /**
     * Version string for NMS and CraftBukkit classes.
     */
    @JvmStatic
    val version: String by lazy {
        Bukkit.getServer().javaClass.`package`.name.let {
            it.substring(it.lastIndexOf('.') + 1) + "."
        }
    }

    // cache of loaded NMS classes
    private val loadedNmsClasses = mutableMapOf<String, Class<*>?>()

    // cache of loaded CraftBukkit classes
    private val loadedCbClasses = mutableMapOf<String, Class<*>?>()

    // cache of loaded methods
    private val loadedMethods = mutableMapOf<Class<*>, MutableMap<String, Method?>>()

    // cache of loaded fields
    private val loadedFields = mutableMapOf<Class<*>, MutableMap<String, Field?>>()

    /**
     * Attempts to get an NMS class from the cache if in the cache, tries to put it in the cache if it's not already
     * there.
     */
    @JvmStatic
    @Synchronized
    fun getNmsClass(nmsClassName: String): Class<*>? = loadedNmsClasses.getOrPut(nmsClassName) {
        val clazzName = "net.minecraft.server.${version}$nmsClassName"
        try {
            Class.forName(clazzName)
        } catch (ex: ClassNotFoundException) {
            null
        }
    }

    /**
     * Attempts to get a CraftBukkit class from the cache if in the cache, tries to put it in the cache if it's not
     * already there.
     */
    @JvmStatic
    @Synchronized
    fun getCbClass(nmsClassName: String): Class<*>? = loadedCbClasses.getOrPut(nmsClassName) {
        val clazzName = "org.bukkit.craftbukkit.${version}$nmsClassName"
        try {
            Class.forName(clazzName)
        } catch (ex: ClassNotFoundException) {
            null
        }
    }

    /**
     * Attempts to find the constructor on the class that has the same types of parameters.
     */
    @Suppress("detekt.SpreadOperator")
    fun getConstructor(clazz: Class<*>, vararg params: Class<*>): Constructor<*>? = try {
        clazz.getConstructor(*params)
    } catch (ex: NoSuchMethodException) {
        null
    }

    /**
     * Attempts to find the method on the class that has the same types of parameters and same name.
     */
    @Suppress("detekt.SpreadOperator")
    fun getMethod(clazz: Class<*>, methodName: String, vararg params: Class<*>): Method? {
        val methods = loadedMethods[clazz] ?: mutableMapOf()

        if (methods.containsKey(methodName)) {
            return methods[methodName]
        }

        val method = try {
            clazz.getMethod(methodName, *params)
        } catch (ex: NoSuchMethodException) {
            null
        }
        methods[methodName] = method
        loadedMethods[clazz] = methods
        return method
    }

    /**
     * Attempts to find the field on the class that has the same name.
     */
    @Suppress("detekt.SpreadOperator")
    fun getField(clazz: Class<*>, fieldName: String): Field? {
        val fields = loadedFields[clazz] ?: mutableMapOf()

        if (fields.containsKey(fieldName)) {
            return fields[fieldName]
        }

        val method = try {
            clazz.getField(fieldName)
        } catch (ex: NoSuchFieldException) {
            null
        }
        fields[fieldName] = method
        loadedFields[clazz] = fields
        return method
    }
}
