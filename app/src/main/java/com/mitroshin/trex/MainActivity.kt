package com.mitroshin.trex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.mitroshin.trex.network.api.CompanyApi
import com.mitroshin.trex.network.api.FlightApi
import com.mitroshin.trex.network.api.HotelApi
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    @Inject lateinit var companyApi: CompanyApi
    @Inject lateinit var flightApi: FlightApi
    @Inject lateinit var hotelApi: HotelApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        companyApi.fetchCompanyList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i("TAG", "Data : $it")
                },
                {
                    Log.e("TAG", "Throwable : $it")
                }
            )

        hotelApi.fetchHotelList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i("TAG", "Data : $it")
                },
                {
                    Log.e("TAG", "Throwable : $it")
                }
            )

        flightApi.fetchFlightList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i("TAG", "Data : $it")
                },
                {
                    Log.e("TAG", "Throwable : $it")
                }
            )
    }
}
