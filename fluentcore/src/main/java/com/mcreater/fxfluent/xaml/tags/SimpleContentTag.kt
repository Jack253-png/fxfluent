package com.mcreater.fxfluent.xaml.tags

import com.mcreater.fxfluent.xaml.ResourceDict
import org.dom4j.Element


abstract class SimpleContentTag<T> : AbstractContentTag<T> {
    protected var element: Element? = null
    var dict: ResourceDict? = null
    override fun writeElement(element: Element): SimpleContentTag<T> {
        this.element = element
        return this
    }

    override fun setResourceDict(dict: ResourceDict): SimpleContentTag<T> {
        this.dict = dict
        return this
    }

    override fun element(): Element {
        return element!!
    }

    override fun toString(): String {
        return toObject().toString()
    }
}
