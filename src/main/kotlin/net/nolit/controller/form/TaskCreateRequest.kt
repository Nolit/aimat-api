package net.nolit.controller.form

import javax.validation.constraints.*

class TaskCreateRequest {
    @NotBlank(message = "タイトルを入力してください")
    lateinit var title: String

    @NotBlank(message = "実施日を選択してください")
    lateinit var dueDate: String
    var amount: Int = 0

    @NotBlank(message = "タスクの種類が正しくありません")
    @Pattern(regexp = "[012]")
    lateinit var type: String
}