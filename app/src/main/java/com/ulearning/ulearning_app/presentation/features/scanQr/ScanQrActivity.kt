package com.ulearning.ulearning_app.presentation.features.scanQr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityScanQrBinding
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.validateCert.ValidateCertActivity
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class ScanQrActivity :
    BaseActivityWithViewModel<ActivityScanQrBinding, ScanQrViewModel>(),
    ScanQrViewState {
    override val binding: ActivityScanQrBinding by dataBinding(ActivityScanQrBinding::inflate)

    override val viewModel: ScanQrViewModel by viewModels()

    override val dataBindingViewModel = BR.scanQrViewModel

    private lateinit var cameraSource: CameraSource

    private lateinit var detector: BarcodeDetector

    private val REQUEST_CAMERA_PERMISSION = 201

    val PERMISSION_CAMERA = 4567

    var surfaceView: SurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ScanQrReducer.instance(viewState = this)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        surfaceView = binding.cameraSurfaceView

        if (checkPermissionsCamera()) {
            setupControls()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getCameraPermission()
            }
        }

        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.apply {
            lifecycleScopeCreate(activity = this@ScanQrActivity, method = {
                state.collect { state ->
                    ScanQrReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@ScanQrActivity, method = {
                effect.collect { effect ->
                    ScanQrReducer.selectEffect(effect)
                }
            })
        }
    }

    private fun setupControls() {
        detector =
            BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build()

        cameraSource =
            detector.let {
                CameraSource.Builder(this, it)
                    .setRequestedPreviewSize(1920, 1080)
                    .setAutoFocusEnabled(true) // you should add this feature
                    .build()
            }

        surfaceView?.holder?.addCallback(surfaceCallBack)

        detector.setProcessor(processor)
    }

    private val processor =
        object : Detector.Processor<Barcode> {
            override fun release() {
            }

            override fun receiveDetections(detections: Detections<Barcode>) {
                runOnUiThread {
                    val barcodes = detections.detectedItems
                    if (barcodes.isNotEmpty()) {
                        val urlWeb: String? = barcodes.valueAt(0).displayValue
                        val paramName = Uri.parse(urlWeb).getQueryParameter("name")
                        if (!paramName.isNullOrEmpty()) {
                            goToDetailCert(paramName)
                        } else {
                            Handler().postDelayed({
                                binding.txtErrorScan.visibility = View.VISIBLE
                            }, 3000)
                        }
                    }
                }
            }
        }

    private fun goToDetailCert(paramName: String) {
        startActivity(
            Intent(this, ValidateCertActivity::class.java).apply {
                putExtra(Config.PARAM_NAME_PUT, paramName)
            },
        )
        finish()
    }

    private val surfaceCallBack =
        object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            this@ScanQrActivity,
                            Manifest.permission.CAMERA,
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource.start(holder)
                    } else {
                        ActivityCompat.requestPermissions(
                            this@ScanQrActivity,
                            arrayOf(Manifest.permission.CAMERA),
                            REQUEST_CAMERA_PERMISSION,
                        )
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int,
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupControls()
                } else if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            Log.d("getCameraPermission", "Never ask again PERMISSION_DENIED")
                        }
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        cameraSource.release()
    }

    override fun onResume() {
        super.onResume()
        setupControls()
    }

    override fun messageFailure(failure: Failure) {
    }

    override fun loading() {
        showLoadingDialog()
    }

    private fun checkPermissionsCamera(): Boolean {
        val permissionState = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getCameraPermission() {
        val permissionArrays = arrayOf(Manifest.permission.CAMERA)
        if (checkPermissionsCamera()) {
            Log.d("getCameraPermission", "Permiso otorgado  PERMISSION_CAMERA")
        } else {
            requestPermissions(permissionArrays, PERMISSION_CAMERA)
        }
    }
}
