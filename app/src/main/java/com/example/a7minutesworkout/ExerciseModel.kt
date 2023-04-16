package com.example.a7minutesworkout

class ExerciseModel (
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean
) {
    fun getId(): Int{
        return id
    }

    fun setId() {
        this.id = id
    }

    fun getName(): String{
        return name
    }

    fun setName() {
        this.name = name
    }

    fun getImage(): Int{
        return image
    }

    fun setImage() {
        this.image = image
    }

    fun getIsCompleted(): Boolean{
        return isCompleted
    }

    fun setIsCompleted() {
        this.isCompleted = isCompleted
    }

    fun getIsSelected(): Boolean{
        return isSelected
    }

    fun setIsSelected() {
        this.isSelected = isSelected
    }
}