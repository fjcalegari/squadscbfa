package com.calestu.squadscbfa.ui.base.activity

import android.os.Bundle
import androidx.databinding.ObservableBoolean
import com.calestu.squadscbfa.data.usecase.SyncUseCase
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.util.ext.completableForUI
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(

    private val syncUseCase: SyncUseCase

) : BaseViewModel() {

    val dataLoading = ObservableBoolean(true)

    init {
        Timber.d("init")

        dataLoading.set(true)

        syncUseCase.syncApp()
            .subscribe(
                {
                    Timber.d("syncApp.success")
                }
                ,{
                    Timber.e(it, "syncApp.error: ")
                }
            )
            .addTo(compositeDisposable)

    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        Timber.d("onFirsTimeUiCreate")

//        verifyTasksCompleted()
//
//        updateTasksDailyMainResumeModelView(DayOfWeekEnum.getDayOfWeekByCurrentDay().shortName, null, null)
    }

    fun destroyDataLoading() {
        Timber.d("destroyDataLoading: ")
        dataLoading.set(false)
    }

    fun onResume() {
        Timber.d("onResume")

//        if (!dataLoading.get()) {
//            loadTasks()
//        }
    }

//    private fun verifyTasksCompleted() {
//        Timber.d("verifyTasksCompleted")
//        dataLoading.set(true)
//        addDisposable(
//            taskRepository.verifyTasksCompletedAndRenew()
//                .doOnSubscribe {
//                    dataLoading.set(true)
//                }
//                .doAfterTerminate {
//                    loadTasks()
//                }
//                .subscribe()
//        )
//
//    }
//
//    private fun loadTasks() {
//        Timber.d("loadTasks")
//        addDisposable(
//            taskRepository.getTodayTasks()
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe {
//                    dataLoading.set(true)
//                }
//                .map{
//                    mapTaskEntityToTaskItemModelView(it)
//                }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .doAfterTerminate {
//                    dataLoading.set(false)
//                }
//                .subscribe({onSuccessTasksList(it)}, {onErrorNotesList(it)})
//        )
//    }
//
//    private fun onSuccessTasksList(list: List<TaskItemModelView>) {
//        Timber.d("onSuccessTasksList")
//        updateTasksDailyMainResumeModelView(list)
////        list.forEach {
////            Timber.d("onSuccessTasksList: list.TaskEntity: $it")
////        }
//        tasksDailyList.value = list.toMutableList()
//    }
//
//    private fun onErrorNotesList(throwable: Throwable){
//        Timber.e(throwable,"onErrorNotesList")
//    }
//
//    private fun updateTask(taskItemModelView: TaskItemModelView) {
//        Timber.d("updateTask")
//        addDisposable(
//            taskRepository.updateCompleted(taskItemModelView.id, taskItemModelView.completed, taskItemModelView.completedDate)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({onSuccessUpdateTask()}, {Timber.e(it, "onErrorUpdateTask")})
//        )
//    }
//
//    private fun onSuccessUpdateTask() {
//        Timber.d("onSuccessUpdateTask")
//
//        updateTasksDailyMainResumeModelView(null, null, null)
//
//        loadTasks()
//    }
//
//    private fun updateTasksDailyMainResumeModelView(list: List<TaskItemModelView>) {
//        var totalDaily = list.size
//        var totalDailyCompleted = list.count { taskItemModelView -> taskItemModelView.completed }
//        updateTasksDailyMainResumeModelView(DayOfWeekEnum.getDayOfWeekByCurrentDay().shortName, totalDaily, totalDailyCompleted)
//    }
//
//    private fun updateTasksDailyMainResumeModelView(dayOfWeekDisplayName: String?,
//                                                    totalTasksDaily: Int?,
//                                                    totalTasksDailyCompleted: Int?) {
//        if (tasksDailyMainResumeModelView.value == null) {
//            tasksDailyMainResumeModelView.value = TasksDailyMainResumeModelView(DayOfWeekEnum.getDayOfWeekByCurrentDay().shortName, 0, 0)
//        } else {
//            tasksDailyMainResumeModelView.value?.let {modelView ->
//                dayOfWeekDisplayName?.let { modelView.dayOfWeekDisplayName = it }
//                totalTasksDaily?.let { modelView.totalTasksDaily = it }
//                totalTasksDailyCompleted?.let { modelView.totalTasksDailyCompleted = it }
//                tasksDailyMainResumeModelView.value = modelView
//            }
//        }
//    }
//
//    fun completeTask(task: TaskItemModelView, completed: Boolean) {
//        Timber.d("completeTask.task: $task | completed: $completed")
//        task.completed = completed
//        if (completed) {
//            task.completedDate = Date().dateNow()
//        } else {
//            task.completedDate = null
//        }
//        updateTask(task)
//    }
//
//    fun openTask(task: TaskItemViewModel) {
//        Timber.d("openTask.task: $task")
//    }
//
//    override fun onCompleteChanged(task: TaskItemModelView) {
//        Timber.d("onCompleteChanged.task: $task")
//        completeTask(task, !task.completed)
//    }
//
//    override fun onTaskClicked(task: TaskItemModelView) {
//        Timber.d("onTaskClicked.task: $task")
//    }
//
//    private fun mapTaskEntityToTaskItemModelView(tasksEntities: List<TaskEntity>): List<TaskItemModelView> {
//        return tasksEntities.mapTo(mutableListOf()) {toTaskEntityToTaskItemModelView(it)}
//    }
//
//    private fun toTaskEntityToTaskItemModelView(taskEntity: TaskEntity): TaskItemModelView {
//        return TaskItemModelView(
//            id = taskEntity.id,
//            titulo = taskEntity.title,
//            completed = taskEntity.isCompleted
//        )
//    }

}