package com.calestu.squadscbfa.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatImageView
import com.calestu.squadscbfa.R

class SelectedButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr), Checkable {

    data class CustomAttribute(
        val checked: Boolean,
        val checkedColorTint: Int,
        val uncheckedColorTint: Int
    )

    private var isChecked: Boolean = false
    private val customAttribute: CustomAttribute

    init {
        customAttribute = customAttributeFrom(attrs)

        isChecked = customAttribute.checked
        updateDrawableWithoutAnimation()
        setOnClickListener(null)
    }

    private fun customAttributeFrom(attrs: AttributeSet?): CustomAttribute {
        val defaultAttribute = CustomAttribute(false, 0, 0)
        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(attrs,
                R.styleable.SelectedButton,
                0, 0)
            val checked = a.getBoolean(R.styleable.SelectedButton_android_checked,
                defaultAttribute.checked)
            val checkedColorTint = a.getColor(R.styleable.SelectedButton_checkedColorTint,
                defaultAttribute.checkedColorTint)
            val uncheckedColorTint = a.getColor(R.styleable.SelectedButton_uncheckedColorTint,
                defaultAttribute.uncheckedColorTint)
            a.recycle()

            return CustomAttribute(checked, checkedColorTint, uncheckedColorTint)
        } else {
            return defaultAttribute
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener { view ->
            l?.onClick(view)
            toggle()
        }
    }

    override fun setChecked(b: Boolean) {
        if (isChecked != b) {
            isChecked = b
            updateDrawableWithoutAnimation()
        }
    }

    override fun isChecked(): Boolean {
        return isChecked
    }

    override fun toggle() {
        isChecked = !isChecked
        updateDrawable()
    }

    private fun updateDrawableWithoutAnimation() {
        if (isChecked) {
            setImageResource(R.drawable.ic_arrow_selected)
        } else {
            setImageResource(R.drawable.ic_arrow_unselected)
        }
    }

    private fun updateDrawable() {
        if (isChecked) {
            setImageResource(R.drawable.ic_arrow_selected)
        } else {
            setImageResource(R.drawable.ic_arrow_unselected)
        }
    }
}