package com.calestu.squadscbfa.ui.module.team

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calestu.squadscbfa.data.source.Team
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber
import javax.inject.Inject

class TeamViewModel @Inject constructor(

) : BaseViewModel() {

    private val _viewStateLiveData = MutableLiveData<List<Team>>()

    val viewStateLiveData: LiveData<List<Team>> = _viewStateLiveData

    init {
        Timber.d("init")

        FirebaseDatabase.getInstance().reference.child("teams")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val list = ArrayList<Team>()
                        dataSnapshot.children.forEach {
                            Timber.d("children: ${it.value}")
                            it.getValue(Team::class.java)?.let { team->
                                list.add(team)
                            }
                        }
                        if (!list.isNullOrEmpty()) {
                            _viewStateLiveData.value = list
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })

    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        Timber.d("onFirsTimeUiCreate")
    }

}