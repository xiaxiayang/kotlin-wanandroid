package com.yx.wanandroidkt.ui.activity

import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yx.wanandroidkt.R
import com.yx.wanandroidkt.R.layout.activity_main
import com.yx.wanandroidkt.base.BaseActivity
import com.yx.wanandroidkt.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val HOME_INDEX = 0
    private val SQUARE_INDEX = 1
    private val ACCOUNT_INDEX = 2
    private val SYSTEM_INDEX = 3
    private val MINE_INDEX = 4

    private var homeFragment: HomeFragment? = null
    private var squareFragment: SquareFragment? = null
    private var accountFragment: OfficialAccountsFragment? = null
    private var systemFragment: SystemFragment? = null
    private var mineFragment: MineFragment? = null

    private var selectedIndex = 0

    override fun getLayoutId(): Int = activity_main


    override fun initView() {

        selectedIndex = HOME_INDEX
        showFragment(selectedIndex)

        bottom_navigation_view.setOnNavigationItemSelectedListener(navigationListener!!)
    }


    override fun requestData() {
    }

    override fun registerListener() {
    }

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        return@OnNavigationItemSelectedListener when (it.itemId){
            R.id.nav_home -> {
                showFragment(HOME_INDEX)
                true
            }
            R.id.nav_square -> {
                showFragment(SQUARE_INDEX)
                true
            }
            R.id.nav_account -> {
                showFragment(ACCOUNT_INDEX)
                true
            }
            R.id.nav_system -> {
                showFragment(SYSTEM_INDEX)
                true
            }
            R.id.nav_mine -> {
                showFragment(MINE_INDEX)
                true
            }
            else -> {
                false
            }

        }

    }

    private fun showFragment(index: Int){

        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        hideAllFragment(transaction)
        selectedIndex = index

        when(index){
            HOME_INDEX ->{
                if (homeFragment == null){
                    homeFragment = HomeFragment.getInstance()
                    transaction.add(R.id.container,homeFragment!!,"homeFragment")
                }else{
                    transaction.show(homeFragment!!)
                }
            }
            SQUARE_INDEX -> {
                if (squareFragment == null){
                    squareFragment = SquareFragment.getInstance()
                    transaction.add(R.id.container,squareFragment!!,"squareFragment")
                }else{
                    transaction.show(squareFragment!!)
                }
            }
            ACCOUNT_INDEX -> {
                if (accountFragment == null){
                    accountFragment = OfficialAccountsFragment.getInstance()
                    transaction.add(R.id.container,accountFragment!!,"accountFragment")
                }else{
                    transaction.show(accountFragment!!)
                }
            }
            SYSTEM_INDEX -> {
                if (systemFragment == null){
                    systemFragment = SystemFragment.getInstance()
                    transaction.add(R.id.container,systemFragment!!,"systemFragment")
                }else{
                    transaction.show(systemFragment!!)
                }
            }
            MINE_INDEX -> {
                if (mineFragment == null){
                    mineFragment = MineFragment.getInstance()
                    transaction.add(R.id.container,mineFragment!!,"mineFragment")
                }else{
                    transaction.show(mineFragment!!)
                }
            }
        }
        transaction.commit()

    }

    private fun hideAllFragment(transaction: FragmentTransaction){
        homeFragment?.run {transaction.hide(homeFragment!!)}
        squareFragment?.run {transaction.hide(squareFragment!!)}
        accountFragment?.run {transaction.hide(accountFragment!!)}
        systemFragment?.run {transaction.hide(systemFragment!!)}
        mineFragment?.run {transaction.hide(mineFragment!!)}



    }
}

