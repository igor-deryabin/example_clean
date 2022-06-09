package com.clean.example.utils.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


/**
 * Делегат, который возвращает ViewBinding. ViewBinding пересоздаётся при каждом пересоздании View фрагмента.
 *
 * Пример использования:
 * ```
 * class MyFragment : Fragment(R.layout.my_fragment) {
 *     private val binding: MyFragmentBinding by viewBinding()
 * }
 * ```
 * Если попытаться получить значение до onViewCreated или после onViewDestroy, будет выброшено исключение.
 */
inline fun <reified T : ViewBinding> Fragment.viewBinding(): ViewBindingDelegate<T> {
    return ViewBindingDelegate(this, T::class)
}

/** @see viewBinding */
class ViewBindingDelegate<T : ViewBinding> @PublishedApi internal constructor(
    private val fragment: Fragment,
    private val viewBindingClass: KClass<T>
) : ReadOnlyProperty<Any?, T> {

    private var binding: T? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { lifecycleOwner ->
            lifecycleOwner.lifecycle.doOnDestroy { binding = null }
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = binding ?: obtainBinding()

    private fun obtainBinding(): T {
        val view = checkNotNull(fragment.view) {
            "ViewBinding is only valid between onCreateView and onDestroyView. " +
                    "Attention! Work with binding inside onDestroyView() returns InvocationTargetException "
        }
        return viewBindingClass.bind(view)
            .also { binding = it }
    }
}


/**
 * Получение биндинга заданного типа [T] из [View].
 */
inline fun <reified T : ViewBinding> View.getBinding(): T {
    return T::class.bind(this)
}

/**
 * Динамический вызов метода bind у ViewBinding.
 *
 * При помощи этого метода можно вызвать bind у любого ViewBinding, что делает возможным
 * упрощение этого вызова.
 * @see getBinding
 */
fun <T : ViewBinding> KClass<T>.bind(rootView: View): T {
    val bindMethod = java.getBindMethod()
    @Suppress("UNCHECKED_CAST")
    return bindMethod.invoke(null, rootView) as T
}


private val bindMethodsCache = mutableMapOf<Class<out ViewBinding>, Method>()

private fun Class<out ViewBinding>.getBindMethod(): Method {
    return bindMethodsCache.getOrPut(this) { getDeclaredMethod("bind", View::class.java) }
}