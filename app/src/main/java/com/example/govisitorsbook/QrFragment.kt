package com.example.govisitorsbook

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_qr.view.*


class QrFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_qr, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lm = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        view.btn_test.setOnClickListener {
            val isGPSEnabled: Boolean = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled: Boolean = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            var latitude: Double? = null
            var longtitude: Double? = null
            var distance: Double? = null
            //매니페스트에 권한이 추가되어 있다해도 여기서 다시 한번 확인해야함
            if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(
                    requireContext().applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    0
                )
            } else {
                when { //프로바이더 제공자 활성화 여부 체크
                    isNetworkEnabled -> {
                        val location =
                            lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) //인터넷기반으로 위치를 찾음

                        longtitude = location?.longitude!!
                        latitude = location.latitude

                    }
                    isGPSEnabled -> {
                        val location =
                            lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) //GPS 기반으로 위치를 찾음

                        longtitude = location?.longitude!!
                        latitude = location.latitude
                    }
                    else -> {

                    }
                }

            }


            val locationA = Location("point A")

            locationA.latitude = latitude!!
            locationA.longitude = longtitude!!

            val locationB = Location("point B")

            locationB.latitude = 37.45075
            locationB.longitude = 127.12886

            distance = locationA.distanceTo(locationB).toDouble()



            view.lat.text = latitude.toString()
            view.lon.text = longtitude.toString()
            view.distance.text = distance.toString()


        }
        lm.removeUpdates(gpsLocationListener)
    }

    //위에 *몇초 간격과 몇미터를 이동했을시에 호출되는 부분* 에 필요한 정보
    //주기적으로 위치 업데이트 안할거면 사용하지 않음
    private val gpsLocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val provider: String = location.provider
            val longitude: Double = location.longitude
            val latitude: Double = location.latitude
            val altitude: Double = location.altitude
        }

        //아래 3개함수는 형식상 필수부분
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}