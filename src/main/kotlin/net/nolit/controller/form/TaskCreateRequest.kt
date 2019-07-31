package net.nolit.controller.form

import javax.validation.constraints.*

class TaskCreateRequest {
    @NotBlank
    lateinit var title: String

    @NotBlank
    lateinit var dueDate: String
    var amount: Int = 0

    @NotBlank
    @Pattern(regexp = "[012]")
    lateinit var type: String
}