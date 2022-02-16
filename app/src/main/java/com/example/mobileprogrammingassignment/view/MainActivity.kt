package com.example.mobileprogrammingassignment.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.mobileprogrammingassignment.viewmodel.UsersViewModel
import javax.inject.Inject
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileprogrammingassignment.database.entity.UserDataEt
import com.example.mobileprogrammingassignment.databinding.ActivityMainBinding
import com.example.mobileprogrammingassignment.mappers.Mapper
import com.example.mobileprogrammingassignment.model.UsersResponse
import com.example.mobileprogrammingassignment.repo.UsersRepo
import com.example.mobileprogrammingassignment.utils.*
import com.example.mobileprogrammingassignment.viewmodel.UsersVMFactory
import com.pinkdot.app.presentation.views.RetailUserVendorPostAd.adapter.UsersDetailViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mobileprogrammingassignment.database.DatabaseBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var job: Job
    private lateinit var usersResponse: UsersResponse

    /*Dependecy Injection*/
    @Inject
    lateinit var repo: UsersRepo
    @Inject
    lateinit var databaseBuilder: DatabaseBuilder
    private lateinit var viewModel: UsersViewModel
    lateinit var showProgressInt: ProgressDialog
    private lateinit var binding: ActivityMainBinding
    private lateinit var usersEntityList: ArrayList<UserDataEt>
    private lateinit var dataList: ArrayList<UsersResponse.Data>

    @Inject
    lateinit var networkHelper: NetworkHelper
    var isApiHit:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, UsersVMFactory(repo,databaseBuilder))[UsersViewModel::class.java]
        showProgressInt = ProgressDialog(this)
        job=GlobalScope.launch(Dispatchers.Main){
            getDataFromDatabase()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    /*Used Flow To Get The Users From Local Storage*/
    suspend fun getDataFromDatabase() {
        viewModel.getUsersFromDB().collect {
            setDbUsersInList(it)
        }
        viewModel.getUsersFromDB()
    }

    /*Used LiveData To Get The Users From Local Storage*/
    private fun setObservers() {
        viewModel.getUsersData().observe(this, EventObserver {
            handleApiCallback(it)
        })
        viewModel.getUsers()
        isApiHit=true
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                showProgressInt.hideProgress()
                when (apiResponse.apiConstant) {
                    ApiConstant.GET_USERS -> {
                            dataList = ArrayList()
                            usersResponse = apiResponse.data as UsersResponse
                            dataList = usersResponse.data
                            setAdapter()
                    }
                }
            }

            Status.LOADING -> {
                showProgressInt.showProgress()
            }

            Status.ERROR -> {
                showProgressInt.hideProgress()
                showToast(this, apiResponse.message!!.plus("Error occured..."))
            }

            Status.RESOURCE -> {
                showProgressInt.hideProgress()
                showToast(this, getString(apiResponse.resourceId!!))
            }
        }
    }

    private fun setDbUsersInList(list: List<UserDataEt>) {
        usersEntityList = list as ArrayList<UserDataEt>
        if (usersEntityList.isNullOrEmpty()){
            if (networkHelper.isNetworkConnected())
                setObservers()
            else
                showToast(this,"Please connect to internet so we can sync users for offline mode")
        }
        setDataToAdapter()
    }

    private fun insertInDb(usersEntityList: List<UserDataEt>) {
     viewModel.insertUsersInDB(usersEntityList)
    }

    fun showToast(context: Context, msg: String) {
        val toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun setAdapter() {
        usersEntityList = ArrayList()
        for (item in dataList) {
            usersEntityList.add(Mapper.convertUserDataModelToUserEntity(item))
        }
        insertInDb(usersEntityList)
        setDataToAdapter()
    }

    private fun setDataToAdapter() {
        binding.rvUsers.apply {
            adapter = UsersDetailViewAdapter(usersEntityList)
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(
                this@MainActivity,
                (layoutManager as LinearLayoutManager).getOrientation()
            ))
        }
    }
}