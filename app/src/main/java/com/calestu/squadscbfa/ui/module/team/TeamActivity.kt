//package com.calestu.squadscbfa.ui.module.team
//
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.calestu.squadscbfa.R
//import com.calestu.squadscbfa.databinding.ActivityTeamBinding
//import com.calestu.squadscbfa.ui.module.team.adapter.TeamsAdapter
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import timber.log.Timber
//
//class TeamActivity : BaseViewModelActivity<ActivityTeamBinding, TeamViewModel>(), TeamNavigator {
//
//    private val firebaseDatabase = FirebaseDatabase.getInstance()
//
//    private val database : DatabaseReference by lazy {
//        firebaseDatabase.reference
//    }
//
//    private val teamsdapter = TeamsAdapter()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding.viewmodel = viewModel
//
//        binding.entriesListRecyclerView.layoutManager = LinearLayoutManager(this@TeamActivity)
//        binding.entriesListRecyclerView.adapter = teamsdapter
//
//        subscribeUi()
//    }
//
//    private fun subscribeUi() {
//        viewModel.viewStateLiveData.watch(this@TeamActivity) {
//            teamsdapter.submitList(it)
//        }
////        val list = arrayListOf<Team>(
////            Team("SC Corinthians", "Corinthians", "SCP"),
////            Team("12", "asda", "SC23424P"),
////            Team("34", "assghnsdfda", "gngn"),
////            Team("45", "23ds", "SC2gngsf3424P"),
////            Team("56", "asdvbbcba", "SC2sfdsfs3424P")
////        )
////
////        database.child("teams").setValue(list)
//
////        database.setValue(Team("SC Corinthians", "Corinthians", "SCP"))
////        database.setValue(Team("SCASD", "adsad", "SadsadCP"))
////        database.setValue(Team("SCAasdSD", "addfdssad", "SadbvsadCP"))
////        database.setValue(Team("SCAdsfSD", "adfdgdassad", "SadqwesadCP"))
////        database.setValue(Team("SCAqeqSD", "adfdfaqqgsad", "SadresadCP"))
////        val userId = database.push().key
////        userId?.let {
////        }
//    }
//
//    override fun showTeamDetail() {
//        Timber.d("showTeamDetail: ")
//    }
//
//    override fun canBack() {
//        Timber.d("canBack: ")
//    }
//
//    override fun getLayout(): Int = R.layout.activity_team
//
//}